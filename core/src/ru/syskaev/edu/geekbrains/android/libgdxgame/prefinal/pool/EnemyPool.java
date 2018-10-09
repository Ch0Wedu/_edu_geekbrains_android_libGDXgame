package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.pool;


import com.badlogic.gdx.audio.Sound;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.SpritesPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Aid;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Enemy;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private AidPool aidPool;
    private Sound shootSound;
    private Sound damageSound;
    private MainShip mainShip;
    private ExplosionPool explosionPool;

    public EnemyPool(BulletPool bulletPool, AidPool aidPool, ExplosionPool explosionPool, Sound shootSound, Sound damageSound, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.aidPool = aidPool;
        this.shootSound = shootSound;
        this.damageSound = damageSound;
        this.mainShip = mainShip;
        this.explosionPool = explosionPool;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, aidPool, explosionPool, shootSound, damageSound, mainShip);
    }
}
