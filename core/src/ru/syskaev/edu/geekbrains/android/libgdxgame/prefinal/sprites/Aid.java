package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.Sprite;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.math.Rect;

public class Aid extends Sprite {

    private static final float height = 0.06f;

    private Rect worldBounds;

    private Vector2 v = new Vector2(0, -0.5f);

    public Aid() {
        regions = new TextureRegion[1];
    }

    public void set(
            TextureRegion region,
            Vector2 pos0,
            Rect worldBounds
    ) {
        this.regions[0] = region;
        this.pos.set(pos0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

}
