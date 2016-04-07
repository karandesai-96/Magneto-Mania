package com.sdsmdg.kd.magnetomania;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.sdsmdg.kd.screens.GameScreen;


public class Main extends Game {

    public static float screenWidth;
    public static float screenHeight;

	@Override
	public void create () {
        Gdx.app.log("MagnetoManiaMain", "create");
        setScreen(new GameScreen());
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }
}
