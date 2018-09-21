package ru.syskaev.edu.geekbrains.android.libgdxgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ExitSprite extends Sprite {

    public ExitSprite(TextureRegion region) {
        super(region);
        Texture tempImg = regions[0].getTexture();
        setHeightProportion(tempImg.getHeight());
        int defaultScreenWidth = Gdx.graphics.getWidth();
        int defaultScreenHeight = Gdx.graphics.getHeight();
        setLeft(defaultScreenWidth - tempImg.getWidth());
        setTop(defaultScreenHeight);
    }

    public void checkTouch(Vector2 touchPosition) {
        if(isMe(touchPosition))
            Gdx.app.exit();
    }

}
