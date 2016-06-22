package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class Laser extends GameObject {
    public final Vector2 center = new Vector2(Main.screen.x, Main.screen.y);
    public Vector2[] endPoints = new Vector2[4];


    public Laser() {
        for (int i = 0; i < 4; i++) {
            this.endPoints[i].set(Main.screen);
        }
        //the weapon is Initially deactivated.
        this.active = false;
    }

    public void fixMagnusPathToFinger (Magnus magnus) {
        magnus.calcVelocityComponent(new Vector2(InputHandler.touch.x,InputHandler.touch.y));
    }

    public void moveMagnusToBoundary (Magnus magnus) {
        magnus.add(magnus.velocityComponent);
    }

    public void moveMagnusToCenter (Magnus magnus) {
        magnus.calcVelocityComponent(Main.screenCenter);
        magnus.add(magnus.velocityComponent);
    }

    /**
     * Set the end point to one of the corners of screen, to start
     * sweeping in anticlockwise direction.
     */
    public void init() {
        activate();
        this.endPoints[0].set(2 * center.x, 0);            // Top Right
        this.endPoints[1].set(0, 0);                       // Top Left
        this.endPoints[2].set(0, 2 * center.y);            // Bottom Left
        this.endPoints[3].set(2 * center.x, 2 * center.y); // Bottom Right
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
