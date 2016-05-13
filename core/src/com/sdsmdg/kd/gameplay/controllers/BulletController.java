package com.sdsmdg.kd.gameplay.controllers;

import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.magnetomania.Main;

public class BulletController {
    private Bullet bullet;

    public BulletController(Bullet bullet){
        this.bullet = bullet;
    }

    public void control(Magnus magnus) {
        if (bullet.active &&
                (bullet.x >= Main.screen.x + 2 * bullet.radius || bullet.x <= -2 * bullet.radius ||
                 bullet.y >= Main.screen.y + 2 * bullet.radius || bullet.y <= -2 * bullet.radius)) {
            bullet.resetBullet(magnus);
        }

        if (bullet.active) {
            bullet.shootBullet();
        }
    }
}
