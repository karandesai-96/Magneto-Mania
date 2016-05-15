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


    public void initRocket(Magnus magnus) {
        /**
         * This method initializes rocket when time arrives. The rocket's center
         * is set as Magnus' center and the direction to shoot it is determined
         * by finger's position.
         *
         * @param magnus For using the coordinates of its center.
         */
        activate();
        this.x = magnus.x;
        this.y = magnus.y;

        calcVelocityComponent(new Vector2(InputHandler.touch.x,InputHandler.touch.y));
        Gdx.app.log("Rocket initialized.","");
    }

    public void follow() {
        /**
         * This method gets finger position, calculates the velocity components of
         * the vector joining rocket's center and finger's position coordinates and
         * adds the velocity components to current position of rocket, hence making
         * it look like following the finger, as it happens in every game update.
         */
        add(velocityComponent);
        calcVelocityComponent(new Vector2(InputHandler.touch.x, InputHandler.touch.y));
        Gdx.app.log("Rocket following, components:", " " + velocityComponent.x + " " + velocityComponent.y);
    }


    public void reset(Magnus magnus) {
        /**
         * This method resets the rocket when its activeTime becomes zero.
         * The rocket is deactivated, its center is set outside the screen again.
         *
         * @param magnus For using the coordinates of its center.
         */
        deactivate();

        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);
        Gdx.app.log("Rocket has been reset.", "");
    }
}
