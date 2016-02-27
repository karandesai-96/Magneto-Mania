package com.sdsmdg.kd.magnetomania;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.sdsmdg.kd.screens.GameScreen;


public class MagnetoManiaMain extends Game {
	SpriteBatch batch;
    Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        Gdx.app.log("MagnetoManiaMain", "create");
        setScreen(new GameScreen());
    }
}
