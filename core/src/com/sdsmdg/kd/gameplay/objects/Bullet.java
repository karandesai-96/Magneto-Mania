package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.MathUtils;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class Bullet extends GameObject {
    public double r;
    public double sine;
    public double cosine;

    public Bullet() {
        // Setting the radius such that the bullet is (1/300)th the size of screen.
        this.radius = (int) Math.sqrt(Main.screenArea / (300 * MathUtils.PI));

        // The bullet should be out of screen when the game starts, hence its
        // coordinates are set a little outside the screen.
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        this.velocity = 0;

        this.r = 0.0;
        this.sine = 0.0;
        this.cosine = 0.0;

        // Bullet is inactive when game starts.
        this.active = false;
    }

    /**
     * This method initializes bullets when time to shoot them arrives.
     * The bullets' center is set as Magnus' center and the direction to
     * shoot them is determined, by finger's position.
     *
     * @param r      For defining the radius.
     * @param magnus For using coordinates of its center
     */
    public void init(Magnus magnus, double r, float theta) {
        activate();
        this.velocity = 25;
        this.velocity *= Main.scaleFactor;
        this.r = r;
        this.sine = MathUtils.sin(theta);
        this.cosine = MathUtils.cos(theta);
        this.x = (float) (magnus.x + r * cosine);
        this.y = (float) (magnus.y + r * sine);
        if (r < 0) {
            this.x = magnus.x;
            this.y = magnus.y;
        }
    }

    /**
     * This method adds the velocity components to current position of bullet,
     * hence making it move in a specific direction.
     */
    public void shoot(Magnus magnus, float delta) {
        r += this.velocity * delta;
        this.x = (float) (magnus.x + r * this.cosine);
        this.y = (float) (magnus.y + r * this.sine);

        if (r < 0) {
            this.x = magnus.x;
            this.y = magnus.y;
        }
    }

    /**
     * This method resets the bullets when they escape the screen while moving.
     * The bullets are deactivated and their center is set outside the screen again.
     * shoot them is determined, by finger's position.
     */
    public void reset() {
        deactivate();

        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);
        this.velocity = 0;
    }
}
