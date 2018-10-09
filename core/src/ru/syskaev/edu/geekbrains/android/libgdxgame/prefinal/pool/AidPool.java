package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.pool;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.base.SpritesPool;
import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.sprites.Aid;

public class AidPool extends SpritesPool<Aid> {

    @Override
    protected Aid newObject() {
        return new Aid();
    }

}
