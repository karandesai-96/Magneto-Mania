package com.sdsmdg.kd.gameplay.controllers;

import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * Created by Haresh on 10-05-2016.
 */
public class BulletController {
    private Bullet bullet;
    private Magnus magnus;

    public BulletController(Bullet bullet, Magnus magnus){
        this.bullet = bullet;
        this.magnus = magnus;
    }

    public void control(){
        if (bullet.active && (bullet.x >= Main.screen.x - 5 || bullet.x <= 5 || bullet.y >= Main.screen.y - 5 || bullet.y <= 5)) {

            // For preventing glitchy movement at the boundary.
            if (bullet.x > Main.screen.x) {
                bullet.x = Main.screen.x;
            }
            if (bullet.y > Main.screen.y) {
                bullet.y = Main.screen.y;
            }

            if (bullet.x < 0) {
                bullet.x = 0;
            }
            if (bullet.y < 0) {
                bullet.y = 0;
            }
        }

        if (bullet.active){
            bullet.initBullets(magnus);
            bullet.shootBullet();
        }

        if (!bullet.active){
            bullet.reset(magnus);
            bullet.setDirection();
        }
    }
}
