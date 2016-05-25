package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Boomerang;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.magnetomania.Main;

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
            if (magnus.x==0) {
                if (boomerang.x < -5) {
                    boomerang.deactivate();
                    Gdx.app.log("BoomerangController","Boomerang deactivated.");
                }
            }
            else if (magnus.x== Main.screen.x) {
                if (boomerang.x > (Main.screen.x + 5)) {
                    boomerang.deactivate();
                    Gdx.app.log("BoomerangController","Boomerang deactivated.");
                }
            }
            else if (magnus.y==0) {
                if (boomerang.y < -5) {
                    boomerang.deactivate();
                    Gdx.app.log("BoomerangController","Boomerang deactivated.");
                }
            }
            else if (magnus.x==Main.screen.y) {
                if (boomerang.x > (Main.screen.y + 5)) {
                    boomerang.deactivate();
                    Gdx.app.log("BoomerangController","Boomerang deactivated.");
                }
            }
            boomerang.shootBoomerang(magnus);
        }
        else{
            boomerang.reset();
            GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
        }
    }
}
