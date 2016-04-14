package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sdsmdg.kd.magnetomania.Main;


public class GameRenderer {
    private GameWorld gameWorld;
    public static OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;


    public GameRenderer (GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Main.screen.x, Main.screen.y);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }


    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Begin drawing filled shape
        shapeRenderer.begin(ShapeType.Filled);

        //Set up (Red,Green,Blue,Alpha)
        shapeRenderer.setColor(255 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);

        //Draws circle from GameWorld
        shapeRenderer.circle(gameWorld.magnus.x, gameWorld.magnus.y, gameWorld.magnus.radius);

        //Tells the shape renderer to finish rendering. This must be done every time.
        shapeRenderer.end();
    }
}
