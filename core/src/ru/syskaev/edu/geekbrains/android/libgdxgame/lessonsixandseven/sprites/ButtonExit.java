package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.ScaledTouchUpButton;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("btExit"), actionListener, 0.9f);
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
