package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Laser;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 */
public class LaserController {
    private Laser laser;

    public LaserController (Laser laser) {
        this.laser = laser;
    }

    public void control (Magnus magnus) {
        if (laser.active){
            if (laser.numberOfTurns>0) {
                if (!(magnus.x > ((Main.screen.x/2)-7) || magnus.y > ((Main.screen.y/2)-7)
                        || magnus.x < ((Main.screen.x/2)+7) || magnus.y < ((Main.screen.y/2)+7))) {
                    Gdx.app.log("LaserController","Magnus moving for laser");
                    laser.moveMagnusToCenter(magnus);
                }
                else {
                    Gdx.app.log("LaserController","Laser rotating");
                    magnus.x = Main.screen.x/2;
                    magnus.y = Main.screen.y/2;
                    laser.rotateLaser();
                }
            }
            else {
                Gdx.app.log("LaserController","Laser reset now, Magnus moving to sides");
                laser.fixMagnusPathToFinger(magnus);
                laser.reset(magnus);
            }
        }
        else{
            if (magnus.x >= Main.screen.x + 5 || magnus.x <= -5 ||
                    magnus.y >= Main.screen.y + 5 || magnus.y <= -5) {

                // For preventing glitchy movement at the boundary.
                if (magnus.x > Main.screen.x + 5) {
                    magnus.x = Main.screen.x;
                }
                if (magnus.y > Main.screen.y + 5) {
                    magnus.y = Main.screen.y;
                }

                if (magnus.x < -5) {
                    magnus.x = 0;
                }
                if (magnus.y < -5) {
                    magnus.y = 0;
                }
                Gdx.app.log("LaserController","Game state changes");
                GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
            }
            else {
                Gdx.app.log("LaserController","Laser now inactive, Magnus moving to sides");
                laser.moveMagnusToBoundary(magnus);
            }
        }
    }
}
