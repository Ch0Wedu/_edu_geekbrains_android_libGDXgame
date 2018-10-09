package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.base.Font;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.pool.AidPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.Base2DScreen;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.math.Rect;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.pool.BulletPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.pool.EnemyPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.pool.ExplosionPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Aid;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Background;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Bullet;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.ButtonReplay;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Enemy;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Life;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.MainShip;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.MessageGameOver;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Star;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.utils.EnemiesEmitter;


public class GameScreen extends Base2DScreen implements ActionListener {

    private static final int STAR_COUNT = 64;

    private enum State { PLAYING, GAME_OVER , SHADOW_P, SHADOW_N}

    Background background;
    Texture bg;
    TextureAtlas atlas, altAtlas;
    Texture voidImg;

    Star[] star;
    Life[] life;

    MainShip mainShip;

    BulletPool bulletPool;
    AidPool aidPool;

    Sound laserSound;
    Sound bulletSound;
    Sound explosionSound;
    Sound damageSound;
    Sound bonusSound;

    EnemyPool enemyPool;
    EnemiesEmitter enemiesEmitter;

    ExplosionPool explosionPool;

    State state;

    MessageGameOver messageGameOver;
    ButtonReplay btnReplay;

    Font font;
    StringBuilder sbScore = new StringBuilder();
    int score;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        shadow_value = 1;
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        damageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/damage.wav"));
        bonusSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bonus.wav"));
        bg = new Texture("bg.png");
        voidImg = new Texture("void.png");
        background = new Background(new TextureRegion(bg));
        font = new Font("font/sabaticaFont.fnt", "font/sabaticaFont.png");
        font.setFontSize(0.03f);
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        altAtlas = new TextureAtlas("textures/altAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        aidPool = new AidPool();

        explosionPool = new ExplosionPool(atlas, altAtlas, explosionSound);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, laserSound, damageSound);
        enemyPool = new EnemyPool(bulletPool, aidPool, explosionPool, bulletSound, damageSound, mainShip);
        enemiesEmitter = new EnemiesEmitter(enemyPool, atlas, altAtlas, worldBounds);
        messageGameOver = new MessageGameOver(atlas);
        life = new Life[mainShip.getHp()];
        for (int i = 0; i < life.length; i++) {
            life[i] = new Life(altAtlas, i);
        }
        btnReplay = new ButtonReplay(atlas, this);
        startNewGame();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    public void update(float delta) {
        if(state == State.SHADOW_P) {
            shadow_value += shadow_delta;
            if(shadow_value > 1) {
                shadow_value = 1;
                startNewGame();
            }
            return;
        }
        if(state == State.SHADOW_N) {
            shadow_value -= shadow_delta;
            if(shadow_value < 0) {
                shadow_value = 0;
                state = State.PLAYING;
            }
            return;
        }
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
        explosionPool.updateActiveObjects(delta);
        bulletPool.updateActiveObjects(delta);
        aidPool.updateActiveObjects(delta);
        if (mainShip.isDestroyed()) {
            state = State.GAME_OVER;
        }
        switch (state) {
            case PLAYING:
                mainShip.update(delta);
                enemyPool.updateActiveObjects(delta);
                enemiesEmitter.generateEnemies(delta);
                break;
            case GAME_OVER:
                break;
        }
    }

    public void checkCollisions() {
        // Столкновение кораблей
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            float minDist = enemy.getHalfWidth() + mainShip.getHalfWidth();
            if (enemy.pos.dst2(mainShip.pos) < minDist * minDist) {
                enemy.destroy();
                enemy.boom();
                mainShip.damage(10 * enemy.getBulletDamage());
                return;
            }
        }

        // Попадание пули во вражеский корабль
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroyed()) {
                continue;
            }
            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() != mainShip || bullet.isDestroyed()) {
                    continue;
                }
                if (enemy.isCollision(bullet)) {
                    bullet.destroy();
                    score += enemy.damage(bullet.getDamage());
                }
            }
        }

        // Попадание пули в игровой корабль
        for (Bullet bullet : bulletList) {
            if (bullet.getOwner() == mainShip || bullet.isDestroyed()) {
                continue;
            }
            if (mainShip.isCollision(bullet)) {
                mainShip.damage(bullet.getDamage());
                bullet.destroy();
            }
        }

        // Bonus
        List<Aid> aidList = aidPool.getActiveObjects();
        for (Aid aid : aidList) {
            if (mainShip.isCollision(aid)) {
                mainShip.incrementHP();
                aid.destroy();
                bonusSound.play();
            }
        }

    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        aidPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
    }

    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        for (int i = 0; i < mainShip.getHp(); i++) {
            life[i].draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveObjects(batch);
        aidPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        if (state == State.GAME_OVER) {
            messageGameOver.draw(batch);
            btnReplay.draw(batch);
        }
        printInfo();
        batch.setColor(shadow_value, shadow_value, shadow_value, shadow_value);
        batch.draw(voidImg, worldBounds.getLeft(), worldBounds.getBottom(), worldBounds.getWidth(), worldBounds.getHeight());
        batch.setColor(1, 1, 1, 1);
        batch.end();
    }

    private void printInfo() {
        sbScore.setLength(0);
        sbScore.append("Score: ");
        sbScore.append(String.format("%5d", score));
        font.draw(batch, sbScore, worldBounds.getRight(), worldBounds.getTop() - 0.01f, Align.right);
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        for (int i = 0; i < mainShip.getHp(); i++) {
            life[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        aidPool.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        bulletSound.dispose();
        explosionSound.dispose();
        laserSound.dispose();
        damageSound.dispose();
        bonusSound.dispose();
        font.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyDown(keycode);
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyUp(keycode);
        }
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (state == State.PLAYING) {
            mainShip.touchDown(touch, pointer);
        } else
            btnReplay.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (state == State.PLAYING) {
            mainShip.touchUp(touch, pointer);
        } else
            btnReplay.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    private void startNewGame() {
        mainShip.startNewGame();
        score = 0;
        bulletPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
        state = State.SHADOW_N;
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == btnReplay) {
            state = State.SHADOW_P;
        }
    }

}
