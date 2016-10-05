package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Rocket;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;

public class RocketController {
    private Rocket rocket;

    public RocketController(Rocket rocket) {
        this.rocket = rocket;
    }

    public void control(float delta) {
        if (rocket.active && rocket.activeTime > 0) {
            rocket.follow(delta);

            // Rocket's activeTime decreases on every game update.
            rocket.activeTime--;
        } else {
            rocket.reset();
            GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
        }
    }

    public boolean check() {
        if (rocket.dst(InputHandler.touch.x, InputHandler.touch.y) < rocket.radius) {
            Gdx.app.log("GameOver", "Collision with Rocket!");
            return true;
        }
        return false;
    }
}
