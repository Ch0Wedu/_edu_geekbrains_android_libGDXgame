package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive;

import com.badlogic.gdx.Game;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.screen.MenuScreen;


public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
