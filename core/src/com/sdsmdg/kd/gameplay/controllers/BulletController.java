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

    public void control(Magnus magnus, float delta) {
        for (int i = 0; i < 7; i++) {
            if (bullet[3].active && (bullet[3].dst(magnus) > Main.d)) {
                bullet[i].reset();
                GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
            }

            if (bullet[i].active) {
                bullet[i].shoot(magnus, delta);
            }
        }
    }

    public boolean check() {
        for (int i = 0; i < 7; i++) {
            if (bullet[i].dst(InputHandler.touch.x, InputHandler.touch.y) < bullet[i].radius + 4) {
                Gdx.app.log("GameOver", "Collision with Bullet!");
                return true;
            }
        }
        return false;
    }
}
