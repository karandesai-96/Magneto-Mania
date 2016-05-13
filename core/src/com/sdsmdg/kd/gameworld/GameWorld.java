package com.sdsmdg.kd.gameworld;

import com.sdsmdg.kd.gameplay.controllers.BulletController;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.screens.GameScreen;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameplay.controllers.MagnusController;


public class GameWorld {
    public enum GameState {
        /** When anything except Magnus is currently on Screen */
        WEAPON_ACTIVE,
        /** When magnus itself is active. */
        MAGNUS_ACTIVE,
        /** Magnus was active till now, next a weapon would be active. */
        NEXT_WEAPON,
        /** Weapon was active till now, next magnus would be active. */
        NEXT_MAGNUS
    };

    public static GameState gameState;
    public Magnus magnus;
    public Bullet bullet;
    public MagnusController magnusController;
    public BulletController bulletController;


    public GameWorld() {
        gameState = GameState.NEXT_MAGNUS;
        magnus = new Magnus();
        bullet = new Bullet();
        magnusController = new MagnusController(magnus);
        bulletController = new BulletController(bullet);
    }

    public void update(float delta) {
        if (GameScreen.isTouched) {
            magnusController.control();
            bulletController.control(magnus);
        }
    }
}
