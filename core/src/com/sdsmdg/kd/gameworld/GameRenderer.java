package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sdsmdg.kd.gameplay.objects.HeatWave;
import com.sdsmdg.kd.helpers.ArcDrawer;
import com.sdsmdg.kd.magnetomania.Main;


public class GameRenderer {
    private GameWorld gameWorld;
    public static OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private ArcDrawer arcDrawer;
    private BitmapFont bitmapFont;
    private SpriteBatch batch;
    private float margin;


    public GameRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, Main.screen.x, Main.screen.y);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        bitmapFont = new BitmapFont();
        batch = new SpriteBatch();
        bitmapFont.setColor(0.1f, 0.1f, 0.1f, 1.0f);
        margin = Main.screen.x / 20;
        arcDrawer = new ArcDrawer();
        arcDrawer.setProjectionMatrix(cam.combined);
    }


    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (gameWorld.laser.active) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            Gdx.gl.glLineWidth(30);
            shapeRenderer.setColor(new Color(0xaa23f4ff));
            for (int i = 0; i < 4; i++) {
                shapeRenderer.line(Main.screenCenter, gameWorld.laser.endPoints[i]);
            }
            shapeRenderer.end();
        }

        if (gameWorld.rocket.active) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(0x4caf50ff));
            shapeRenderer.circle(gameWorld.rocket.x, gameWorld.rocket.y, gameWorld.rocket.radius);
            shapeRenderer.end();
        }

        for (int i = 0; i < gameWorld.spanOfBullets; i++) {
            for (int j = 0; j < gameWorld.depthOfBullets; j++) {
                if (gameWorld.bullet[i][j].active) {
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(new Color(0x03a9f4ff));
                    shapeRenderer.circle(gameWorld.bullet[i][j].x, gameWorld.bullet[i][j].y, gameWorld.bullet[i][j].radius);
                    shapeRenderer.end();
                }
            }
        }

        if (gameWorld.boomerang.active) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(0x8dfe7aff));
            shapeRenderer.circle(gameWorld.boomerang.x, gameWorld.boomerang.y, gameWorld.boomerang.radius);
            shapeRenderer.end();
        }

        for (HeatWave heatwave : gameWorld.heatwaves) {
            if (heatwave.active) {
                for (int i = 0; i < 6; i++) {
                    arcDrawer.begin(ArcDrawer.ShapeType.Line);
                    Gdx.gl.glLineWidth(64);
                    arcDrawer.setColor(new Color(0xffca28ff));
                    arcDrawer.arc(heatwave.x, heatwave.y, heatwave.radius, 30 * (i + (i % 2)), 30, 50);
                    arcDrawer.end();
                }
            }
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0xf44336ff));
        shapeRenderer.circle(gameWorld.magnus.x, gameWorld.magnus.y, gameWorld.magnus.radius);
        shapeRenderer.end();

        batch.begin();
        bitmapFont.draw(batch, "Score :" + gameWorld.gameScoreToDisplay, margin, Main.screen.y - margin);
        batch.end();
    }
}
