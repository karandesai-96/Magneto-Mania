package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

public class MagnusController {
    private Magnus magnus;

    public MagnusController(Magnus magnus) {
        this.magnus = magnus;
    }


    public void control () {
        if (magnus.active && (magnus.x >= Main.screen.x + 5 || magnus.x <= -5 ||
                              magnus.y >= Main.screen.y + 5 || magnus.y <= -5)) {

            // For preventing glitchy movement at the boundary.
            if (magnus.x > Main.screen.x+5) {
                magnus.x = Main.screen.x;
            }
            if (magnus.y > Main.screen.y+5) {
                magnus.y = Main.screen.y;
            }

            if (magnus.x < -5) {
                magnus.x = 0;
            }
            if (magnus.y < -5) {
                magnus.y = 0;
            }

            magnus.prepareForSleep();

            //after the Magnus reaches one of the walls, next a weapon is selected and fired.
            GameWorld.gameState = GameWorld.GameState.NEXT_WEAPON;
        }

        if (!magnus.active) {
            magnus.sleep();

            if (magnus.active) {
                magnus.prepareForAttack();
                magnus.attack();
            }
        }
        else {
            magnus.attack();
        }
    }

    public boolean check() {
        if (magnus.dst(InputHandler.touch.x, InputHandler.touch.y) < magnus.radius) {
            Gdx.app.log("GameOver", "Collision with Magnus!");
            return true;
        }
        return false;
    }
}
