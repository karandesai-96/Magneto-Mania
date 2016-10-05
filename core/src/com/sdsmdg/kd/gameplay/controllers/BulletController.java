package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

public class BulletController {
    private Bullet[][] bullet;

    public BulletController(Bullet[][] bullet) {
        this.bullet = bullet;
    }

    public void control(Magnus magnus, float delta, int spanOfBullets, int depthOfBullets) {
        for (int i = 0; i < spanOfBullets; i++) {
            for (int j = 0; j < depthOfBullets; j++) {
                if (bullet[spanOfBullets / 2][depthOfBullets - 1].active && (bullet[spanOfBullets / 2][depthOfBullets - 1].dst(magnus) > Main.d)) {
                    bullet[i][j].reset();
                    GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
                }

                if (bullet[i][j].active) {
                    bullet[i][j].shoot(magnus, delta);
                }
            }
        }
    }

    public boolean check(int spanOfBullets, int depthOfBullets) {
        for (int i = 0; i < spanOfBullets; i++) {
            for (int j = 0; j < depthOfBullets; j++) {
                if (bullet[i][j].dst(InputHandler.touch.x, InputHandler.touch.y) < bullet[i][j].radius + 4) {
                    Gdx.app.log("GameOver", "Collision with Bullet!");
                    return true;
                }
            }
        }
        return false;
    }
}
