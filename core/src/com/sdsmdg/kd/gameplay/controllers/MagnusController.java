package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.math.RandomXS128;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.magnetomania.Main;

public class MagnusController {
    private Magnus magnus;
    private RandomXS128 randomXS128;
    public static int weaponSelector;

    public MagnusController(Magnus magnus) {
        this.magnus = magnus;
        this.randomXS128 = new RandomXS128();
    }


    public void control () {
        if (magnus.active && (magnus.x >= Main.screen.x - 5 || magnus.x <= 5 || magnus.y >= Main.screen.y - 5 || magnus.y <= 5)) {

            // For preventing glitchy movement at the boundary.
            if (magnus.x > Main.screen.x) {
                magnus.x = Main.screen.x;
            }
            if (magnus.y > Main.screen.y) {
                magnus.y = Main.screen.y;
            }

            if (magnus.x < 0) {
                magnus.x = 0;
            }
            if (magnus.y < 0) {
                magnus.y = 0;
            }

            magnus.prepareForSleep();
        }

        if (!magnus.active) {
            magnus.sleep();
            if (magnus.sleepTime == 1) {
                weaponSelector = randomXS128.nextInt();
            }

            if (magnus.active && weaponSelector%2==0) {
                magnus.prepareForAttack();
                magnus.attack();
            }
        }
        else {
            magnus.attack();
        }
    }
}
