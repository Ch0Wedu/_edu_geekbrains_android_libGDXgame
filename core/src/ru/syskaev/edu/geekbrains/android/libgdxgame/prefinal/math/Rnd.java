package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.math;

import java.util.Random;

/**
 * Генератор случайных чисел
 */
public class Rnd {
    private static final Random random = new Random();

    public static Random getRandom() {
        return random;
    }

    /**
     * Сгенерировать случайное число
     * @param min минимальное значение случайного числа
     * @param max максимальное значение случайного числа
     * @return результат
     */
    public static float nextFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }
}
