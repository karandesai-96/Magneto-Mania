package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class Boomerang extends GameObject{
    public float acceleration;
    private Vector2 initialTouch;
    public float initialVelocity;
    private RandomXS128 random;

    public Boomerang () {
        // Setting the radius such that the boomerang is (1/100)th the size of screen.
        this.radius = (int) Math.sqrt(Main.screenArea / (100 * MathUtils.PI));

        // The boomerang should be out of screen when the game starts, hence its
        // coordinates are set a little outside the screen.
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        this.velocity = 0;
        this.initialVelocity = 0;
        this.acceleration = 0;

        // Boomerang is inactive when game starts.
        this.active = false;

        this.initialTouch = new Vector2(0, 0);
        this.random = new RandomXS128();
    }


    public void init (Magnus magnus) {
        activate();
        this.x = magnus.x;
        this.y = magnus.y;

        this.velocity = 30 + random.nextInt(15);
        this.acceleration = 0.35f + random.nextFloat() / 5;

        this.initialTouch.set(InputHandler.touch.x, InputHandler.touch.y);
        this.initialVelocity = this.velocity;

        calcVelocityComponent(initialTouch);
    }

    public void reset () {
        deactivate();
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        this.velocity = 0;
        this.acceleration = 0;
    }

    public void shootBoomerang (Magnus magnus, float delta) {
        mulAdd(this.velocityComponent, delta);
        velocity -= acceleration * delta;
        calcVelocityComponent(new Vector2(magnus.x, magnus.y), initialTouch);
    }
}
