package com.hotpotato.game.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import screens.HPotato;

public class DesktopLauncher {
	public static void main (String[] arg) {
		/*
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new MyGdxGame(), config);
		
		*/
		
		
		Game myGame = new HPotato();
		LwjglApplication launcher = new LwjglApplication (myGame, "Hot Potato",800, 480);
	
	}
}
