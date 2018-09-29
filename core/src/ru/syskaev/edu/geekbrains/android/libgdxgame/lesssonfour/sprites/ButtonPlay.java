package ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.base.ActionListener;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.base.ScaledSprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.math.Rect;


public class ButtonPlay extends ScaledSprite {
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
