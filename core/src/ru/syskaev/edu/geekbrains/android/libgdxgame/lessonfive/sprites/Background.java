package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.base.Sprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.math.Rect;


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
