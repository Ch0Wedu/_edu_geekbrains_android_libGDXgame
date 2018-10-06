package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.pool;


import com.badlogic.gdx.audio.Sound;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.base.SpritesPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.Enemy;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonsixandseven.sprites.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private Sound shootSound;
    private Sound damageSound;
    private MainShip mainShip;
    private ExplosionPool explosionPool;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, Sound damageSound, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.shootSound = shootSound;
        this.damageSound = damageSound;
        this.mainShip = mainShip;
        this.explosionPool = explosionPool;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, shootSound, damageSound, mainShip);
    }
}
