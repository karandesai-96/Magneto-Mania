package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sdsmdg.kd.magnetomania.Main;


public class GameRenderer {
    private GameWorld gameWorld;
    public static OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private BitmapFont bitmapFont;
    private SpriteBatch batch;


    public GameRenderer (GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Main.screen.x, Main.screen.y);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        bitmapFont = new BitmapFont();
        batch = new SpriteBatch();
    }


    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Line);
        Gdx.gl.glLineWidth(30);
        shapeRenderer.setColor(new Color(0xaa23f4ff));
        shapeRenderer.line(gameWorld.laser.endPoints[0].x,gameWorld.laser.endPoints[0].y,
                gameWorld.laser.endPoints[1].x,gameWorld.laser.endPoints[1].y);
        shapeRenderer.line(gameWorld.laser.endPoints[0].x,gameWorld.laser.endPoints[0].y,
                gameWorld.laser.endPoints[2].x,gameWorld.laser.endPoints[2].y);
        shapeRenderer.line(gameWorld.laser.endPoints[0].x,gameWorld.laser.endPoints[0].y,
                gameWorld.laser.endPoints[3].x,gameWorld.laser.endPoints[3].y);
        shapeRenderer.line(gameWorld.laser.endPoints[0].x,gameWorld.laser.endPoints[0].y,
                gameWorld.laser.endPoints[4].x,gameWorld.laser.endPoints[4].y);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(0x4caf50ff));
        shapeRenderer.circle(gameWorld.rocket.x, gameWorld.rocket.y, gameWorld.rocket.radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(0x03a9f4ff));
        shapeRenderer.circle(gameWorld.bullet.x, gameWorld.bullet.y, gameWorld.bullet.radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(0x8dfe7aff));
        shapeRenderer.circle(gameWorld.boomerang.x, gameWorld.boomerang.y, gameWorld.boomerang.radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeType.Line);
        Gdx.gl.glLineWidth(32);
        shapeRenderer.setColor(new Color(0xffca28ff));
        shapeRenderer.circle(gameWorld.heatwave.x, gameWorld.heatwave.y, gameWorld.heatwave.radius);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(0xf44336ff));
        shapeRenderer.circle(gameWorld.magnus.x, gameWorld.magnus.y, gameWorld.magnus.radius);
        shapeRenderer.end();

        batch.begin();
        bitmapFont.setColor(new Color(0x111111ff));
        bitmapFont.draw(batch, gameWorld.gameScoreToDisplay, 10, 10);
        batch.end();
    }
}
