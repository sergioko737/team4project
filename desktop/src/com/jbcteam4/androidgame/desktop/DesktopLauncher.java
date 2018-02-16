package com.jbcteam4.androidgame.desktop;

		import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
		import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
		import com.jbcteam4.androidgame.FlappyStarter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Flappy Riga";
		config.useGL30 = true;
		config.height = 800;
		config.width = 480;

		new LwjglApplication(new FlappyStarter(), config);
	}
}
