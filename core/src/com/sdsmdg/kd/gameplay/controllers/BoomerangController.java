package com.sdsmdg.kd.gameplay.controllers;

import com.sdsmdg.kd.gameplay.objects.Boomerang;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;

/**
 * @author Haresh Khanna
 */
public class BoomerangController {
    private Boomerang boomerang;

    public BoomerangController (Boomerang boomerang) {
        this.boomerang = boomerang;
    }

    public void control (Magnus magnus) {
        if (boomerang.active) {
            boomerang.shootBoomerang(magnus);
        }
        else{
            boomerang.reset();
            GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
        }
    }
}
