package com.sdsmdg.kd.magnetomania;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.screens.GameScreen;


public class Main extends Game {

    public static Vector2 screen = new Vector2(0, 0);
    public static float screenArea;
    public static Vector2 screenCenter = new Vector2(0,0);
    public static double scaleFactor;
    public static double d;

	@Override
	public void create () {
        Gdx.app.log("MagnetoManiaMain", "create");
        screen.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        screenArea = screen.x * screen.y;
        screenCenter.set(screen.x/2,screen.y/2);
        scaleFactor = 60;
        d = screen.dst(0,0);
        setScreen(new GameScreen());
    }
}
