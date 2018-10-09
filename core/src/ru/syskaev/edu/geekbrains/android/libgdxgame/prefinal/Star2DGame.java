package ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.screen.MenuScreen;


public class Star2DGame extends Game {

    Music music;

    @Override
    public void create() {
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        music.dispose();
        super.dispose();
    }
}
