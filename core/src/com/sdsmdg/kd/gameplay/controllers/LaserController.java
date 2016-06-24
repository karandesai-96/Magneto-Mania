package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameplay.objects.Laser;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class LaserController {
    private Laser laser;

    public LaserController (Laser laser) {
        this.laser = laser;
    }

    public void control (Magnus magnus) {
        if (laser.active) {
            if (laser.numberOfSwipes > 0) {
                if (magnus.dst(Main.screenCenter) >= 10) {
                    magnus.calcVelocityComponent(Main.screenCenter);
                    magnus.add(magnus.velocityComponent);

                    if (magnus.dst(Main.screenCenter) < 10) {
                        magnus.set(Main.screenCenter);
                    }
                }
                else {
                    laser.rotate();

                    // All the arms swipe at once, so checking only one.
                    if (laser.endPoints[0].x < 1) {
                        laser.numberOfSwipes--;
                        laser.init(laser.numberOfSwipes);
                    }
                }
            }
            else {
                magnus.calcVelocityComponent(new Vector2(InputHandler.touch.x, InputHandler.touch.y));
                laser.reset();
            }
        }
        else {
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
                GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
            }
            else {
                magnus.add(magnus.velocityComponent);
            }
        }
    }
}
