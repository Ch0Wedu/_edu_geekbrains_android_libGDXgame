package ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.base.ScaledSprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.math.Rect;

public class Ship extends ScaledSprite implements InputProcessor {

    private static final float VELOCITY_VALUE = 0.7f;

    Rect worldBounds;
    Vector2 velocityDirection = new Vector2();
    Vector2 velocity = new Vector2();
    Rect prevRect = new Rect();

    public Ship(TextureAtlas atlas, ActionListener actionListener, Rect worldBounds) {
        super(atlas.findRegion("main_ship"), actionListener, 0.9f);
        setHeightProportion(0.15f);
        this.worldBounds = worldBounds;
        TextureRegion region = regions[0];
        region.setRegionWidth(region.getRegionWidth() / 2);
        setWidth(getWidth() /2);
        pos.set(0, -0.3f);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(Rect worldBounds) {
    }

    @Override
    public void update(float delta) {
        prevRect.set(this);
        pos.mulAdd(velocity, delta);
        if(!worldBounds.isInside(this))
            set(prevRect);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                velocityDirection.add( -1f, 0);
                break;
            case Input.Keys.RIGHT:
                velocityDirection.add(1f, 0);
                break;
            case Input.Keys.UP:
                velocityDirection.add(0, 1f);
                break;
            case Input.Keys.DOWN:
                velocityDirection.add(0, -1f);
                break;
        }
        velocity = velocityDirection.cpy().nor().scl(VELOCITY_VALUE);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                velocityDirection.add(1f, 0);
                break;
            case Input.Keys.RIGHT:
                velocityDirection.add(-1f, 0);
                break;
            case Input.Keys.UP:
                velocityDirection.add(0, -1f);
                break;
            case Input.Keys.DOWN:
                velocityDirection.add(0, 1f);
                break;
        }
        velocity = velocityDirection.cpy().nor().scl(VELOCITY_VALUE);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
