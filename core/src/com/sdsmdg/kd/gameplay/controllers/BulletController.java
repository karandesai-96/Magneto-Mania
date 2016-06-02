package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

public class BulletController {
    private Bullet bullet;

    public BulletController(Bullet bullet) {
        this.bullet = bullet;
    }

    public void control(Magnus magnus) {
        if (bullet.active &&
                (bullet.x >= Main.screen.x + 2 * bullet.radius || bullet.x <= -2 * bullet.radius ||
                 bullet.y >= Main.screen.y + 2 * bullet.radius || bullet.y <= -2 * bullet.radius)) {

            /* Currently, only one bullet is being shot, and when it
             * goes out of the screen, it is reset to its default position
             * and the gameState is changed.
             */

            if(bullet.bulletsFired == 7) {
                bullet.reset();
                GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
            }
            else {
                bullet.bulletsFired++;
                bullet.init(magnus);
                bullet.velocity += (bullet.bulletsFired);
            }
        }

        if (bullet.active) {
            bullet.shoot();
        }
    }

    public boolean check() {
        if (bullet.dst(InputHandler.touch.x, InputHandler.touch.y) < bullet.radius) {
            Gdx.app.log("GameOver","Collision with Bullet!");
            return true;
        }
        return false;
    }
}
