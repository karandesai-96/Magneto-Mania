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
    public String gameScoreToDisplay;
    public boolean isGameOver;

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

        // Initially isGameOver boolean is set to false.
        this.isGameOver = false;

        /**
         * gameScore is the variable which is incremented with each
         * call of update function. gameScoreToDisplay is the String
         * which is rendered in GameRenderer and contains the floor
         * function of the gameScore variable. Upon every update, the
         * score as well as rate of score keeps on increasing. The rate
         * of increase of score becomes constant after a certain limit.
         */
        this.gameScore = 0.0f;
        this.gameScoreToDisplay = String.valueOf(MathUtils.floor(gameScore));

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

            /**
             *------------Scoring-------------*
             **/
            if (gameScore >= 10000f) {
                gameScore += 5.0f;
            }
            else {
                gameScore += 1.0f + (gameScore / 2500.0f);
            }
            gameScoreToDisplay = String.valueOf(MathUtils.floor(gameScore));

            /**
             *--------Game Over Handling---------*
             **/
            isGameOver = magnusController.check();

            if (isGameOver) {
                gameScore = 0;
                //This will stop the game rendering for the while and game
                //needs to be restarted in order to replay. This needs to be
                //replaced by changing to game-over screen.
                GameScreen.isTouched = false;
            }
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
            laser.init(8);
        }
        else if (currentWeapon == 5) {
            //Boomerang Selected.
            Gdx.app.log("GameWorld","Boomerang Initialised");
            boomerang.init(magnus);
        }
    }
}
