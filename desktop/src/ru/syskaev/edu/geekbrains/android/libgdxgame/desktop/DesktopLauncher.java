package ru.syskaev.edu.geekbrains.android.libgdxgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.syskaev.edu.geekbrains.android.libgdxgame.GameEntry;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameEntry(), config);
	}
}
