package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class GameRenderer {

    /** CLASS MEMBERS *******************************************************/
    private GameWorld gameWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private float screenWidth = Gdx.graphics.getWidth();
    private float screenHeight = Gdx.graphics.getHeight();
    /**-------------------------------------------------------------------**/

    /** CONSTRUCTOR *******************************************************/
    public GameRenderer (GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth, screenHeight);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }
    /**-------------------------------------------------------------------**/

    /** RENDERING METHOD *******************************************************/
    public void render() {
        Gdx.app.log("GameRenderer", "render");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Begin drawing filled shape
        shapeRenderer.begin(ShapeType.Filled);

        //Set up (Red,Green,Blue,Alpha)
        shapeRenderer.setColor(255 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);

        //Draws circle from GameWorld
        shapeRenderer.circle((Gdx.graphics.getWidth()) / 2, (Gdx.graphics.getHeight()) / 2, gameWorld.magnus.magnusRadius);

        //Tells the shape renderer to finish rendering. This must be done every time.
        shapeRenderer.end();

    }
    /**-------------------------------------------------------------------**/
}
