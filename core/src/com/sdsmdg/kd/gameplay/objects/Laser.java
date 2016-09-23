package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class Laser extends GameObject {
    public Vector2[] endPoints;
    public int numberOfSwipes;
    private double sweepTime;

    public Laser() {
        RandomXS128 random = new RandomXS128();

        this.endPoints = new Vector2[4];
        for (int i = 0; i < 4; i++) {
            this.endPoints[i] = new Vector2(Main.screen);
        }
        //the weapon is Initially deactivated.
        this.active = false;
        this.numberOfSwipes = 8;

        sweepTime = 25 + (random.nextInt() % 20);
        sweepTime /= Main.scaleFactor;
    }

    /**
     * Set the end point to one of the corners of screen, to start
     * sweeping in anticlockwise direction.
     */
    public void init(int numberOfSwipes) {
        activate();
        this.numberOfSwipes = numberOfSwipes;
        this.endPoints[0].set(2 * Main.screenCenter.x, 0);
        this.endPoints[1].set(0, 0);
        this.endPoints[2].set(0, 2 * Main.screenCenter.y);
        this.endPoints[3].set(2 * Main.screenCenter.x, 2 * Main.screenCenter.y);
    }

    public Vector2 rotate(float delta) {
        this.endPoints[0].x -= Main.screen.x * delta / sweepTime;
        this.endPoints[1].y += Main.screen.y * delta / sweepTime;
        this.endPoints[2].x += Main.screen.x * delta / sweepTime;
        this.endPoints[3].y -= Main.screen.y * delta / sweepTime;
        return null;
    }

    public void reset () {
        deactivate();
    }
}
