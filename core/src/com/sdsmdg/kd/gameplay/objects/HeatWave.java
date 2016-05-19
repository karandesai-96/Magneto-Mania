package com.sdsmdg.kd.gameplay.objects;


/**
 * @author Karan Desai
 */
public class HeatWave extends GameObject {

    public HeatWave() {
        // Setting the radius 0 as wave propagates radially outwards.
        this.radius = 0;

        // The wave should be just a point in outside the screen when game starts.
        this.x = -10;
        this.y = -10;

        this.velocity = 20;

        // Heatwave is inactive when game starts.
        this.active = false;
    }

    public void init(Magnus magnus) {
        /**
         * This method initializes the wave when time to release it arrives.
         * The wave's center is set as Magnus' center.
         *
         * @param magnus For using the coordinates of its center.
         */
        activate();
        this.x = magnus.x;
        this.y = magnus.y;
        this.radius = 0;
    }

    public void expand() {
        /**
         * This method adds the velocity to radius of heatwave, hence expanding it.
         */
        this.radius += this.velocity;
    }

    public void reset() {
        /**
         * This method resets the wave when it becomes large enough to cover the screen.
         * The wave is deactivated and its radius and center set to zero.
         * shoot them is determined, by finger's position.
         *
         * @param magnus For using the coordinates of its center.
         */
        deactivate();

        this.x = -10;
        this.y = -10;
        this.radius = 0;
    }
}
