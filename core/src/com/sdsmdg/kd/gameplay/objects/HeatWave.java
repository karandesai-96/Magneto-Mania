package com.sdsmdg.kd.gameplay.objects;


import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Karan Desai
 * @author Haresh Khanna
 */
public class HeatWave extends GameObject {


    public int heatWaveNo;
    public HeatWave(int number) {
        // Setting the radius 0 as wave propagates radially outwards.
        this.radius = 0;

        // The wave should be just a point in outside the screen when game starts.
        this.x = -10;
        this.y = -10;

        this.velocity = 5;
        this.velocity *= Main.scaleFactor;

        // This works as a unique identity of every heatWave and helps in rendering all heatwaves at
        // different radii. The greater the heatWave number, sooner it strikes the finger.
        this.heatWaveNo = number + 1;

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
        this.radius = 0;
    }


    /**
     * This method adds the velocity to radius of heatwave, hence expanding it. Radius expansion
     * takes place at various rates depending on the unique heatWaveNo of each heatwave.
     */
    public void expand(float delta) {
        this.radius += 0.6 * (this.heatWaveNo + 1) * this.velocity * delta;
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
        this.radius = 0;
    }
}
