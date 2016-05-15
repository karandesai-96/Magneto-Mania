package com.sdsmdg.kd.gameplay.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;


public class Rocket extends GameObject {
    private RandomXS128 random;
    public int activeTime;


    public Rocket() {
        // Setting the radius such that the rocket is (1/18)th the size of screen.
        this.radius = (int) Math.sqrt(Main.screenArea / (18 * MathUtils.PI));

        // The rocket should be out of screen when the game starts, hence its
        // coordinates are set a little outside the screen.
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        // Velocity of Rocket can be anything between 10 and 24 units.
        this.velocity = random.nextInt(15) + 10;

        this.random = new RandomXS128();

        // The variable 'activeTime' is time till which the rocket follows finger.
        this.activeTime = 0;

        // Rocket is inactive when game starts
        this.active = false;
    }
}
