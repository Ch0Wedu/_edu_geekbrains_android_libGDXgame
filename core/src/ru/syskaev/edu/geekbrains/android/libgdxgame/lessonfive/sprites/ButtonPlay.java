package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.base.ScaledTouchUpButton;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.math.Rect;


public class ButtonPlay extends ScaledTouchUpButton {
    public ButtonPlay(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("btPlay"), actionListener, 0.9f);
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
