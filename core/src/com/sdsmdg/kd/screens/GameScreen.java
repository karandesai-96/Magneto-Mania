package com.sdsmdg.kd.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.sdsmdg.kd.gameworld.GameRenderer;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;


public class GameScreen implements Screen {
    public static boolean isTouched;
    private GameWorld gameWorld;
    private GameRenderer gameRenderer;


    public GameScreen() {
        isTouched = false;
        this.gameWorld = new GameWorld();
        this.gameRenderer = new GameRenderer(gameWorld);
        Gdx.input.setInputProcessor(new InputHandler());
        Gdx.app.log("GameScreen", "Attached");
    }


    @Override
    public void show() {
        Gdx.app.log("GameScreen", "Show called");
    }


    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        gameRenderer.render();
    }


    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }


    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }


    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }


    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }


    @Override
    public void dispose() {}
}
