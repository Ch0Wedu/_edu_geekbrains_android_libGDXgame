package ru.syskaev.edu.geekbrains.android.libgdxgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.syskaev.edu.geekbrains.android.libgdxgame.prefinal.Star2DGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 3f/4f;
		config.resizable = false;
		config.width = 500;
		config.height = (int) (config.width / aspect);
		new LwjglApplication(new Star2DGame(), config);
	}
}
