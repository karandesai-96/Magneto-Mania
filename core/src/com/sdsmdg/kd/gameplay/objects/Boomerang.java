package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 */
public class Boomerang extends GameObject{
    public float acceleration;
    public Vector2 accelerationComponent;
    private Vector2 inititalTouch;
    private RandomXS128 random;

    public Boomerang () {
        // Setting the radius such that the boomerang is (1/100)th the size of screen.
        this.radius = (int) Math.sqrt(Main.screenArea/(100* MathUtils.PI));

        // The boomerang should be out of screen when the game starts, hence its
        // coordinates are set a little outside the screen.
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        this.velocity = 0;
        this.acceleration = 0;

        // Boomerang is inactive when game starts.
        this.active = false;

        this.accelerationComponent = new Vector2(0,0);
        this.inititalTouch = new Vector2(0,0);
        this.random = new RandomXS128();
    }

    public void calcAccelerationComponent (Vector2 destination) {
        float distance = dst(destination);

        // acceleration times cos(theta)
        this.accelerationComponent.x = acceleration * (destination.x - this.x) / distance;

        // acceleration times sin(theta)
        this.accelerationComponent.y = acceleration * (destination.y - this.y) / distance;
    }

    public void init (Magnus magnus) {
        activate();
        this.x = magnus.x;
        this.y = magnus.y;

        this.velocity = 15 + random.nextInt(10);
        this.acceleration = random.nextFloat();

        this.inititalTouch.set(InputHandler.touch.x,InputHandler.touch.y);

        calcVelocityComponent(inititalTouch);
        calcAccelerationComponent(inititalTouch);
        Gdx.app.log("Boomerang","Velocity and Acceleration set");
    }

    public void reset () {
        deactivate();
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        this.velocity = 0;
        this.acceleration = 0;

        this.accelerationComponent.set(0,0);
        this.inititalTouch.set(0,0);
    }
}
