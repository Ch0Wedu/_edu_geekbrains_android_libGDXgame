package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.Sprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.math.Rect;

public class Life extends Sprite {

    public static final float DX = 0.03f;

    private int count;

    public Life(TextureAtlas atlas, int count) {
        super(atlas.findRegion("life"));
        this.count = count;
        setHeightProportion(0.05f);
    }

    @Override
    public void resize(Rect worldBounds) {
        float posX = worldBounds.getLeft() + getHalfWidth() + DX * count;
        float posY = worldBounds.getTop() - getHalfHeight();
        pos.set(posX, posY);
    }

}
