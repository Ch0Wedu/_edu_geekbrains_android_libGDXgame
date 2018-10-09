package ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.pool;


import com.badlogic.gdx.audio.Sound;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.base.SpritesPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.math.Rect;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.sprites.Enemy;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.sprites.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private Sound shootSound;
    private MainShip mainShip;
    private ExplosionPool explosionPool;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.shootSound = shootSound;
        this.mainShip = mainShip;
        this.explosionPool = explosionPool;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, shootSound, mainShip);
    }
}
