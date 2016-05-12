package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.math.RandomXS128;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * Created by Haresh on 10-05-2016.
 */
public class BulletController {
    private Bullet bullet;
    private Magnus magnus;
    private RandomXS128 randomXS128;

    public BulletController(Bullet bullet, Magnus magnus){
        this.bullet = bullet;
        this.magnus = magnus;
        this.randomXS128 = new RandomXS128();
    }

    public void control(){
        if (bullet.active && (bullet.x >= (Main.screen.x + (2*bullet.radius)) || bullet.x <= (0-(2*bullet.radius)) ||
                bullet.y >= (Main.screen.y + (2*bullet.radius)) || bullet.y <= (0-(2*bullet.radius)))) {
            // For preventing glitched movement at the boundary.
            bullet.initBullets(magnus);
            bullet.deactivate();
            MagnusController.weaponSelector = randomXS128.nextInt();

            /// if number of bullets shot is sufficient then bullet.active can be set to false here.
        }

        if (bullet.active){
            bullet.shootBullet();
        }

        if (!bullet.active){
            bullet.initBullets(magnus);
            bullet.setDirection();
        }
    }
}
