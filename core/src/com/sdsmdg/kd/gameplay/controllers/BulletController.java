package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

public class BulletController {
    private Bullet[] bullet;

    public BulletController(Bullet[] bullet) {
        this.bullet = bullet;
    }

    public void control(Magnus magnus, float delta, int spanOfBullets) {
        for (int i = 0; i < spanOfBullets; i++) {
            if (bullet[spanOfBullets/2].active && (bullet[spanOfBullets/2].dst(magnus) > Main.d)) {
                bullet[i].reset();
                GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
            }

            if (bullet[i].active) {
                bullet[i].shoot(magnus, delta);
            }
        }
    }

    public boolean check(int spanOfBullets) {
        for (int i = 0; i < spanOfBullets; i++) {
            if (bullet[i].dst(InputHandler.touch.x, InputHandler.touch.y) < bullet[i].radius + 4) {
                Gdx.app.log("GameOver", "Collision with Bullet!");
                return true;
            }
        }
        return false;
    }
}
