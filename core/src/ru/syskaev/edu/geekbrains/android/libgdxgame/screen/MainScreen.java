package ru.syskaev.edu.geekbrains.android.libgdxgame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.syskaev.edu.geekbrains.android.libgdxgame.utils.ExitSprite;


public class MainScreen extends BaseScreen {

    private static final float PACMAN_VELOCITY = 1.5f;
    private static final float KEYBOARD_DELTA = 5.0f;

    private SpriteBatch batch;
    private int defaultScreenWidth;
    private int defaultScreenHeight;

    private Texture pacmanImg;
    private Vector2 pacmanPosition;
    private Vector2 pacmanDestination;

    private Texture exitImg;
    private ExitSprite exitSprite;


    public MainScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        defaultScreenWidth = Gdx.graphics.getWidth();
        defaultScreenHeight = Gdx.graphics.getHeight();

        pacmanImg = new Texture("pacman.png");
        pacmanPosition = new Vector2(defaultScreenWidth / 2,defaultScreenHeight / 2);
        pacmanDestination = pacmanPosition;

        exitImg = new Texture("exit.png");
        exitSprite = new ExitSprite(new TextureRegion(exitImg));
    }

    private void calcLogic() {
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
            keyPressed();

        Vector2 dVector = pacmanDestination.cpy().sub(pacmanPosition);
        double dVectorLength = dVector.len();
        if(dVectorLength < PACMAN_VELOCITY) pacmanPosition = pacmanDestination;
        else pacmanPosition.add(dVector.nor().scl(PACMAN_VELOCITY));
    }

    private void keyPressed() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            pacmanDestination = pacmanPosition.cpy().add(new Vector2(-KEYBOARD_DELTA, 0));
        else if(Gdx.input.isKeyPressed(Input.Keys.UP))
            pacmanDestination = pacmanPosition.cpy().add(new Vector2(0, KEYBOARD_DELTA));
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            pacmanDestination = pacmanPosition.cpy().add(new Vector2(KEYBOARD_DELTA, 0));
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            pacmanDestination = pacmanPosition.cpy().add(new Vector2(0, -KEYBOARD_DELTA));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        calcLogic();
        Gdx.gl.glClearColor(0, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(pacmanImg,
                pacmanPosition.x - pacmanImg.getWidth() / 2,
                pacmanPosition.y - pacmanImg.getHeight() / 2);
        exitSprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        pacmanImg.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touchPosition = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
        exitSprite.checkTouch(touchPosition);
        pacmanDestination = touchPosition;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        if(keycode == Input.Keys.ESCAPE)
            Gdx.app.exit();
        return false;
    }

}
