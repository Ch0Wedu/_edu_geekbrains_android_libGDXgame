package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.pool;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.SpritesPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.math.Rnd;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private final TextureRegion textureRegion;
    private final TextureRegion altTextureRegion;

    private Sound explosionSound;

    public ExplosionPool(TextureAtlas atlas, TextureAtlas altAtlas, Sound explosionSound) {
        this.textureRegion = atlas.findRegion("explosion");
        this.altTextureRegion = altAtlas.findRegion("altexplosion");
        this.explosionSound = explosionSound;
    }

    @Override
    protected Explosion newObject() {
        Float randomFloat = Rnd.nextFloat(0.0f, 1.0f);
        System.out.println("RF = "+ randomFloat);
        if(randomFloat < 0.5f)
            return new Explosion(textureRegion, 9, 9, 74, explosionSound);
        else
            return new Explosion(altTextureRegion, 8, 8, 64, explosionSound);
    }
}
