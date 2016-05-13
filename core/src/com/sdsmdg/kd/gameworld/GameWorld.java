package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.RandomXS128;
import com.sdsmdg.kd.gameplay.controllers.BulletController;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.screens.GameScreen;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameplay.controllers.MagnusController;


public class GameWorld {
    public enum GameState {
        // When anything except Magnus is currently on Screen.
        WEAPON_ACTIVE,
        // When magnus itself is active.
        MAGNUS_ACTIVE,
        // Magnus was active till now, next a weapon would be active.
        NEXT_WEAPON,
        // Weapon was active till now, next magnus would be active.
        NEXT_MAGNUS
    };

    public static GameState gameState;

    /**
     * Different values of currentWeapon integer correspond to:
     *      0: Magnus
     *      1: Bullets
     */
    public static int currentWeapon;

    public Magnus magnus;
    public Bullet bullet;
    public MagnusController magnusController;
    public BulletController bulletController;


    public GameWorld() {
        gameState = GameState.NEXT_MAGNUS;
        currentWeapon = 0;
        magnus = new Magnus();
        bullet = new Bullet();
        magnusController = new MagnusController(magnus);
        bulletController = new BulletController(bullet);

        //Sets the initial firing direction for the Magnus, as it is the first weapon to be fired.
        magnus.prepareForAttack();
    }

    public void update(float delta) {
        Gdx.app.log("GameWorld", "Update called");
        if (GameScreen.isTouched) {
            if (currentWeapon == 1) {
                Gdx.app.log("GameWorld","BulletController called");
                bulletController.control(magnus);
            }
            else {
                Gdx.app.log("GameWorld", "MagnusController called");
                magnusController.control();
            }

            if (gameState == GameState.NEXT_WEAPON || gameState == GameState.NEXT_MAGNUS) {
                if (gameState == GameState.NEXT_WEAPON) {
                    selectWeapon();
                    gameState = GameState.WEAPON_ACTIVE;
                }
                else {
                    currentWeapon = 0;
                    gameState = GameState.MAGNUS_ACTIVE;
                }
            }
        }
    }

    public void selectWeapon() {
        currentWeapon = 1;
        bullet.initBullets(magnus);
    }
}
