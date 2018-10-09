package ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.pool;


import ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.base.SpritesPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.sprites.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    protected void log() {
        System.out.println("Bullet active/free: " + activeObjects.size() + "/" + freeObjects.size());
    }
}
