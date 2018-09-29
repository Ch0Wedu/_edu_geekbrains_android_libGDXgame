package ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.base.Base2DScreen;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.math.Rect;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.sprites.Background;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.sprites.Ship;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.sprites.Star;


public class GameScreen extends Base2DScreen implements ActionListener {

    private static final int STAR_COUNT = 64;

    Background background;
    Texture bg;
    TextureAtlas atlas;


    Star[] star;
    Ship ship;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlas);
        }
        ship = new Ship(atlas, this, worldBounds);
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
        ship.update(delta);
    }

    public void checkCollisions() {

    }

    public void deleteAllDestroyed() {

    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0.4f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        ship.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public void actionPerformed(Object src) {
    }

}
