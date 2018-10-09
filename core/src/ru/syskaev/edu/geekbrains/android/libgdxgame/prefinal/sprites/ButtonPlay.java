package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.ScaledTouchUpButton;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.math.Rect;


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
