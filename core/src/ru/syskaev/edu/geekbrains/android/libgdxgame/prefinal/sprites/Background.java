package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.Sprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.math.Rect;


public class Background extends Sprite {
    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}
