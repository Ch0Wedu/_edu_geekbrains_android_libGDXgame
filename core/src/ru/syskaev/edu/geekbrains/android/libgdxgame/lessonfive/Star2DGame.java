package ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import ru.syskaev.edu.geekbrains.android.libgdxgame.lessonfive.screen.MenuScreen;


public class Star2DGame extends Game {

    Music backgroundMusic;

    @Override
    public void create() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Space Music  Alpha - Froese and Haslinger.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();
        super.dispose();
    }

}
