package ru.syskaev.edu.geekbrains.android.libgdxgame;

import com.badlogic.gdx.Game;

import ru.syskaev.edu.geekbrains.android.libgdxgame.screen.MainScreen;


public class GameEntry extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }

}
