package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.Sprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.math.Rect;


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
