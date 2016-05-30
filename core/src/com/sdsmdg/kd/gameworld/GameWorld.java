package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.sdsmdg.kd.screens.GameScreen;

import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.gameplay.objects.HeatWave;
import com.sdsmdg.kd.gameplay.objects.Rocket;
import com.sdsmdg.kd.gameplay.objects.Laser;
import com.sdsmdg.kd.gameplay.objects.Boomerang;

import com.sdsmdg.kd.gameplay.controllers.MagnusController;
import com.sdsmdg.kd.gameplay.controllers.BulletController;
import com.sdsmdg.kd.gameplay.controllers.HeatWaveController;
import com.sdsmdg.kd.gameplay.controllers.RocketController;
import com.sdsmdg.kd.gameplay.controllers.LaserController;
import com.sdsmdg.kd.gameplay.controllers.BoomerangController;


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
     *      3: Rocket
     *      4: Laser
     */
    public static int currentWeapon;
    public RandomXS128 random;
    public float gameScore;
    public int multiplier;
    public String gameScoreToDisplay;

    public Magnus magnus;
    public Bullet bullet;
    public HeatWave heatwave;
    public Rocket rocket;
    public Laser laser;
    public Boomerang boomerang;

    public MagnusController magnusController;
    public BulletController bulletController;
    public HeatWaveController heatwaveController;
    public RocketController rocketController;
    public LaserController laserController;
    public BoomerangController boomerangController;


    public GameWorld() {
        gameState = GameState.NEXT_MAGNUS;
        currentWeapon = 0;

        magnus = new Magnus();
        bullet = new Bullet();
        heatwave = new HeatWave();
        rocket = new Rocket();
        laser = new Laser();
        boomerang = new Boomerang();

        magnusController = new MagnusController(magnus);
        bulletController = new BulletController(bullet);
        heatwaveController = new HeatWaveController(heatwave);
        rocketController = new RocketController(rocket);
        laserController = new LaserController(laser);
        boomerangController = new BoomerangController(boomerang);

        this.random = new RandomXS128();
        this.gameScore = 0.0f;

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
            else if (currentWeapon == 4) {
                laserController.control(magnus);
            }
            else if (currentWeapon == 5) {
                boomerangController.control(magnus);
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

            if (gameScore == 0){
                gameScore = 1;
            }
            else{
                gameScore += multiplier * 0.001;
            }
            gameScoreToDisplay = String.valueOf(MathUtils.floor(gameScore));
        }
    }

    public void selectWeapon() {
        currentWeapon = random.nextInt(6);
        if (currentWeapon == 1) {
            // Bullets selected.
            Gdx.app.log("GameWorld","Bullet Initialised");
            bullet.init(magnus);
        }
        else if (currentWeapon == 2) {
            // Heatwave selected.
            Gdx.app.log("GameWorld","HeatWave Initialised");
            heatwave.init(magnus);
        }
        else if (currentWeapon == 3) {
            // Rocket selected.
            Gdx.app.log("GameWorld","Rocket Initialised");
            rocket.init(magnus);
        }
        else if (currentWeapon == 4) {
            //Laser selected.
            Gdx.app.log("GameWorld","Laser Initialised");
            laser.init(magnus);
        }
        else if (currentWeapon == 5) {
            //Boomerang Selected.
            Gdx.app.log("GameWorld","Boomerang Initialised");
            boomerang.init(magnus);
        }
    }
}
