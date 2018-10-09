package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.math.Rect;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.math.Rnd;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.pool.EnemyPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Enemy;

public class EnemiesEmitter {

    private static float ENEMY_SMALL_HEIGHT = 0.1f;
    private static float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static int ENEMY_SMALL_BULLET_DAMAGE = 1;
    private static float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static int ENEMY_SMALL_HP = 1;

    private static float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static float ENEMY_MEDIUM_BULLET_VY = -0.25f;
    private static int ENEMY_MEDIUM_BULLET_DAMAGE = 2;
    private static float ENEMY_MEDIUM_RELOAD_INTERVAL = 4f;
    private static int ENEMY_MEDIUM_HP = 5;

    private static float ENEMY_BIG_HEIGHT = 0.2f;
    private static float ENEMY_BIG_BULLET_HEIGHT = 0.04f;
    private static float ENEMY_BIG_BULLET_VY = -0.3f;
    private static int ENEMY_BIG_BULLET_DAMAGE = 5;
    private static float ENEMY_BIG_RELOAD_INTERVAL = 3f;
    private static int ENEMY_BIG_HP = 10;

    private static float ENEMY_ALT_HEIGHT = 0.09f;
    private static float ENEMY_ALT_BULLET_HEIGHT = 0.015f;
    private static float ENEMY_ALT_BULLET_VY = -0.5f;
    private static int ENEMY_ALT_BULLET_DAMAGE = 3;
    private static float ENEMY_ALT_RELOAD_INTERVAL = 0.4f;
    private static int ENEMY_ALT_HP = 3;

    private final TextureRegion[] enemySmallRegion;
    private final TextureRegion[] enemyMediumRegion;
    private final TextureRegion[] enemyBigRegion;
    private final TextureRegion[] enemyAltRegion;

    private final Vector2 enemySmallV = new Vector2(0f, -0.2f);
    private final Vector2 enemyMediumV = new Vector2(0f, -0.03f);
    private final Vector2 enemyBigV = new Vector2(0f, -0.005f);
    private final Vector2 enemyAltV = new Vector2(-0.2f, -0.08f);

    private final EnemyPool enemyPool;

    private Rect worldBounds;

    private TextureRegion bulletRegion;
    private TextureRegion aidRegion;
    private TextureRegion altBulletRegion;

    private float generateInterval = 3.5f;
    private float generateTimer;

    public EnemiesEmitter(EnemyPool enemyPool, TextureAtlas atlas, TextureAtlas altAtlas, Rect worldBounds) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;
        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegion0, 1, 2, 2);
        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        this.enemyMediumRegion = Regions.split(textureRegion1, 1, 2, 2);
        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemyBigRegion = Regions.split(textureRegion2, 1, 2, 2);
        TextureRegion textureRegion3 = altAtlas.findRegion("altenemy");
        this.enemyAltRegion = Regions.split(textureRegion3, 1, 2, 2);

        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.aidRegion = altAtlas.findRegion("aid");
        this.altBulletRegion = atlas.findRegion("bulletMainShip");

    }


    public void generateEnemies(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            float type = (float) Math.random();
            if (type < 0.45f) {
                enemy.set(
                        "small",
                        enemySmallRegion,
                        enemySmallV,
                        bulletRegion,
                        aidRegion,
                        ENEMY_SMALL_BULLET_HEIGHT,
                        ENEMY_SMALL_BULLET_VY,
                        ENEMY_SMALL_BULLET_DAMAGE,
                        ENEMY_SMALL_RELOAD_INTERVAL,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP,
                        worldBounds
                );
            } else if (type < 0.60f) {
                enemy.set(
                        "medium",
                        enemyMediumRegion,
                        enemyMediumV,
                        bulletRegion,
                        aidRegion,
                        ENEMY_MEDIUM_BULLET_HEIGHT,
                        ENEMY_MEDIUM_BULLET_VY,
                        ENEMY_MEDIUM_BULLET_DAMAGE,
                        ENEMY_MEDIUM_RELOAD_INTERVAL,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP,
                        worldBounds
                );
            } else if (type < 0.85f) {
                enemy.set(
                        "alt",
                        enemyAltRegion,
                        enemyAltV,
                        altBulletRegion,
                        aidRegion,
                        ENEMY_ALT_BULLET_HEIGHT,
                        ENEMY_ALT_BULLET_VY,
                        ENEMY_ALT_BULLET_DAMAGE,
                        ENEMY_ALT_RELOAD_INTERVAL,
                        ENEMY_ALT_HEIGHT,
                        ENEMY_ALT_HP,
                        worldBounds
                );
            } else {
                enemy.set(
                        "big",
                        enemyBigRegion,
                        enemyBigV,
                        bulletRegion,
                        aidRegion,
                        ENEMY_BIG_BULLET_HEIGHT,
                        ENEMY_BIG_BULLET_VY,
                        ENEMY_BIG_BULLET_DAMAGE,
                        ENEMY_BIG_RELOAD_INTERVAL,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP,
                        worldBounds
                );
            }
            if(enemy.getType().equals("alt"))
                enemy.pos.x = worldBounds.getRight() + enemy.getHalfWidth();
            else
                enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }
    }
}
