package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Boomerang;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class BoomerangController {
    private Boomerang boomerang;

    public BoomerangController (Boomerang boomerang) {
        this.boomerang = boomerang;
    }

    public void control (Magnus magnus, float delta) {
        if (boomerang.active) {
            if (boomerang.velocity < -boomerang.initialVelocity) {
                    boomerang.deactivate();
                    Gdx.app.log("BoomerangController", "Boomerang deactivated.");
            }
            boomerang.shootBoomerang(magnus, delta);
        }
        else{
            boomerang.reset();
            GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
        }
    }

    public boolean check() {
        if (boomerang.dst(InputHandler.touch.x, InputHandler.touch.y) < boomerang.radius) {
            Gdx.app.log("GameOver","Collision with Boomerang!");
            return true;
        }
        return false;
    }
}
