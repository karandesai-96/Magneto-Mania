package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Boomerang;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class BoomerangController {
    private Boomerang boomerang;

    public BoomerangController (Boomerang boomerang) {
        this.boomerang = boomerang;
    }

    public void control (Magnus magnus) {
        if (boomerang.active) {
            if (boomerang.velocity < -boomerang.initialVelocity) {
                    boomerang.deactivate();
                    Gdx.app.log("BoomerangController", "Boomerang deactivated.");
            }
            boomerang.shootBoomerang(magnus);
        }
        else{
            boomerang.reset();
            GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
        }
    }
}