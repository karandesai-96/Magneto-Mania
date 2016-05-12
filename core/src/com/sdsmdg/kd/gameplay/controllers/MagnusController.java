package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.math.RandomXS128;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.magnetomania.Main;

public class MagnusController {
    private Magnus magnus;
    private Bullet bullet;
    private BulletController bulletController;
    private RandomXS128 randomXS128;
    private int weaponSelector;


    public MagnusController(Magnus magnus, Bullet bullet, BulletController bulletController) {
        this.magnus = magnus;
        this.bullet = bullet;
        this.bulletController = bulletController;
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
            weaponSelector = randomXS128.nextInt(2);
        }

        if (!magnus.active) {
            if (weaponSelector%2==0) {
                magnus.sleep();

                if (magnus.active) {
                    magnus.prepareForAttack();
                    magnus.attack();
                }
            }
            else if (weaponSelector%2==1) {
                bullet.activate();
                bullet.setDirection();
                bulletController.control();
            }
        }
        else {
            magnus.attack();
        }
    }
}
