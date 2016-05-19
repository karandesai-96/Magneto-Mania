package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.math.RandomXS128;
import com.sdsmdg.kd.gameplay.controllers.HeatWaveController;
import com.sdsmdg.kd.gameplay.objects.HeatWave;
import com.sdsmdg.kd.screens.GameScreen;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.Rocket;

import com.sdsmdg.kd.gameplay.controllers.MagnusController;
import com.sdsmdg.kd.gameplay.controllers.BulletController;
import com.sdsmdg.kd.gameplay.controllers.RocketController;


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
     *      2: Heatwave
     *      2: Rocket
     */
    public static int currentWeapon;
    public RandomXS128 random;
    public Magnus magnus;
    public Bullet bullet;
    public HeatWave heatwave;
    public Rocket rocket;
    public MagnusController magnusController;
    public BulletController bulletController;
    public HeatWaveController heatwaveController;
    public RocketController rocketController;


    public GameWorld() {
        gameState = GameState.NEXT_MAGNUS;
        currentWeapon = 0;
        magnus = new Magnus();
        bullet = new Bullet();
        heatwave = new HeatWave();
        rocket = new Rocket();
        magnusController = new MagnusController(magnus);
        bulletController = new BulletController(bullet);
        heatwaveController = new HeatWaveController(heatwave);
        rocketController = new RocketController(rocket);
        this.random = new RandomXS128();

        //Sets the initial firing direction for the Magnus, as it is the first weapon to be fired.
        magnus.prepareForAttack();
    }

    public void update(float delta) {
        if (GameScreen.isTouched) {
            if (currentWeapon == 1) {
                bulletController.control(magnus);
            }
            else if (currentWeapon == 2) {
                heatwaveController.control();
            }
            else if (currentWeapon == 3) {
                rocketController.control();
            }
            else {
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
        currentWeapon = random.nextInt(4);
        if (currentWeapon == 1) {
            // Bullets selected.
            bullet.init(magnus);
        }
        else if (currentWeapon == 2) {
            // Heatwave selected.
            heatwave.init(magnus);
        }
        else if (currentWeapon == 3) {
            // Rocket selected.
            rocket.init(magnus);
        }
    }
}
