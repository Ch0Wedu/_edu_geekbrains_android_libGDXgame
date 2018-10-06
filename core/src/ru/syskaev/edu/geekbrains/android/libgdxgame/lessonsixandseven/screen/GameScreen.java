package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.List;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.Base2DScreen;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.Sprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.math.Rect;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.pool.BulletPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.pool.EnemyPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.pool.ExplosionPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.Background;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.Bullet;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.ButtonReplay;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.Enemy;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.Explosion;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.Life;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.MainShip;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.MessageGameOver;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.Star;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.utils.EnemiesEmitter;


public class GameScreen extends Base2DScreen implements ActionListener {

    private static final int STAR_COUNT = 64;
    private static final int DEFAULT_HEALTH = 15;

    private enum State { PLAYING, GAME_OVER }

    Background background;
    Texture bg;
    TextureAtlas atlas, altAtlas;

    Star[] star;
    Life[] life;

    MainShip mainShip;

    BulletPool bulletPool;

    Sound laserSound;
    Sound bulletSound;
    Sound explosionSound;
    Sound damageSound;

    EnemyPool enemyPool;
    EnemiesEmitter enemiesEmitter;

    ExplosionPool explosionPool;

    State state;

    MessageGameOver messageGameOver;
    ButtonReplay btnReplay;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        damageSound = Gdx.audio.newSound(Gdx.files.internal("sounds/damage.wav"));
        bg = new Texture("bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        altAtlas = new TextureAtlas("textures/altAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, altAtlas, explosionSound);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, laserSound, damageSound);
        enemyPool = new EnemyPool(bulletPool, explosionPool, bulletSound, damageSound, mainShip);
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
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
        explosionPool.updateActiveObjects(delta);
        bulletPool.updateActiveObjects(delta);
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
                if (enemy.isBulletCollision(bullet)) {
                    bullet.destroy();
                    enemy.damage(bullet.getDamage());
                }
            }
        }

        // Попадание пули в игровой корабль
        for (Bullet bullet : bulletList) {
            if (bullet.getOwner() == mainShip || bullet.isDestroyed()) {
                continue;
            }
            if (mainShip.isBulletCollision(bullet)) {
                mainShip.damage(bullet.getDamage());
                bullet.destroy();
            }
        }
    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0.4f, 0.6f, 0);
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
        enemyPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        if (state == State.GAME_OVER) {
            messageGameOver.draw(batch);
            btnReplay.draw(batch);
        }
        batch.end();
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
        enemyPool.dispose();
        explosionPool.dispose();
        bulletSound.dispose();
        explosionSound.dispose();
        laserSound.dispose();
        damageSound.dispose();
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
        state = State.PLAYING;

        mainShip.startNewGame();

        bulletPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == btnReplay) {
            game.setScreen(new GameScreen(game));
        }
    }

}
