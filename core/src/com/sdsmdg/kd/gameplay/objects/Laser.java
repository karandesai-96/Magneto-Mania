package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class Laser extends GameObject {
    public Vector2[] endPoints = new Vector2[4];
    public int numberOfSwipes;

    public Laser() {
        for (int i = 0; i < 4; i++) {
            this.endPoints[i] = new Vector2(Main.screen);
        }
        //the weapon is Initially deactivated.
        this.active = false;
        this.numberOfSwipes = 8;
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

    public void rotate() {
        this.endPoints[0].x -= Main.screen.x / 30.0;
        this.endPoints[1].y += Main.screen.y / 30.0;
        this.endPoints[2].x += Main.screen.x / 30.0;
        this.endPoints[3].y -= Main.screen.y / 30.0;
    }

    public void reset () {
        deactivate();
    }
}
