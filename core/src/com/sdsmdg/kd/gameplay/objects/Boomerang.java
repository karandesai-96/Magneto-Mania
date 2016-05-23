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

        this.random = new RandomXS128();
    }

    public void calcAccelerationComponent (Vector2 destination) {
        float distance = dst(destination);

        // acceleration times cos(theta)
        accelerationComponent.x = acceleration * (destination.x - this.x) / distance;

        // acceleration times sin(theta)
        accelerationComponent.y = acceleration * (destination.y - this.y) / distance;
    }

    public void init (Magnus magnus) {
        activate();
        this.x = magnus.x;
        this.y = magnus.y;

        this.velocity = 15 + random.nextInt(10);
        this.acceleration = random.nextFloat();

        calcVelocityComponent(new Vector2(InputHandler.touch.x,InputHandler.touch.y));
        calcAccelerationComponent(new Vector2(InputHandler.touch.x,InputHandler.touch.y));
        Gdx.app.log("Boomerang","Velocity and Acceleration set");
    }
}
