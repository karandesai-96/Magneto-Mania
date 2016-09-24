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

    public void control(Magnus magnus, float delta) {
        if (bullet.active &&
                (bullet.x >= Main.screen.x + 2 * bullet.radius || bullet.x <= -2 * bullet.radius ||
                 bullet.y >= Main.screen.y + 2 * bullet.radius || bullet.y <= -2 * bullet.radius)) {

            /* Currently, only one bullet is being shot, and when it
             * goes out of the screen, it is reset to its default position
             * and the gameState is changed.
             */
            bullet.reset();
            GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
        }

        if (bullet.active) {
            bullet.shoot(magnus, delta);
        }
    }

    public boolean check() {
        if (bullet.dst(InputHandler.touch.x, InputHandler.touch.y) < bullet.radius + 4) {
            Gdx.app.log("GameOver","Collision with Bullet!");
            return true;
        }
        return false;
    }
}
