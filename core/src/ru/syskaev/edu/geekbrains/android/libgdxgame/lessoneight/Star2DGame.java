package ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight;

import com.badlogic.gdx.Game;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessoneight.screen.MenuScreen;


public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
