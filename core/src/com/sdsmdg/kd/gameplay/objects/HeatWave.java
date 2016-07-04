package com.sdsmdg.kd.gameplay.objects;


import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Karan Desai
 * @author Haresh Khanna
 */
public class HeatWave extends GameObject {
    public float[] radius;

    public HeatWave() {
        // Setting the radius 0 as wave propagates radially outwards.
        this.radius = new float[4];
        for (int i = 0; i < 5; i++) {
            this.radius[i] = 0;
        }

        // The wave should be just a point in outside the screen when game starts.
        this.x = -10;
        this.y = -10;

        this.velocity = 20;
        this.velocity *= Main.scaleFactor;

        // Heatwave is inactive when game starts.
        this.active = false;
    }

    /**
     * This method initializes the wave when time to release it arrives.
     * The wave's center is set as Magnus' center.
     *
     * @param magnus For using the coordinates of its center.
     */
    public void init(Magnus magnus) {
        activate();
        this.x = magnus.x;
        this.y = magnus.y;
        for (int i = 0; i < 5; i++) {
            this.radius[i] = 0;
        }
    }

    /**
     * This method adds the velocity to radius of heatwave, hence expanding it.
     */
    public void expand(float delta) {
        this.radius[0] += this.velocity * delta * 1.0;
        this.radius[1] += this.velocity * delta * 1.2;
        this.radius[2] += this.velocity * delta * 1.4;
        this.radius[3] += this.velocity * delta * 1.6;
        this.radius[4] += this.velocity * delta * 1.8;
    }

    /**
     * This method resets the wave when it becomes large enough to cover the screen.
     * The wave is deactivated and its radius and center set to zero.
     * shoot them is determined, by finger's position.
     */
    public void reset() {
        deactivate();
        this.x = -10;
        this.y = -10;
        for (int i = 0; i < 5; i++) {
            this.radius[i] = 0;
        }
    }
}
