package com.sdsmdg.kd.gameplay.controllers;

import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameplay.objects.Rocket;
import com.sdsmdg.kd.gameworld.GameWorld;

public class RocketController {
    private Rocket rocket;

    public RocketController(Rocket rocket) {
        this.rocket = rocket;
    }

    public void control() {
        if (rocket.active && rocket.activeTime > 0) {
            rocket.follow();

            // Rocket's activeTime decreases on every game update.
            rocket.activeTime--;
        }
        else {
            rocket.reset();
            GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
        }
    }
}
