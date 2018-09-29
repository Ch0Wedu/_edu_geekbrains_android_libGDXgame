package ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.base.Sprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lesssonfour.math.Rect;


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
