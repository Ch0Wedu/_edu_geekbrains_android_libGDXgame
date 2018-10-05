package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.ScaledTouchUpButton;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.math.Rect;


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
