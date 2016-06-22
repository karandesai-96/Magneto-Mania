package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class Laser extends GameObject {
    private RandomXS128 random;
    public final Vector2 center = new Vector2(Main.screen.x, Main.screen.y);

    public Laser() {
        this.random = new RandomXS128();

        this.x = Main.screen.x;
        this.y = Main.screen.y;

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
     * @param orientation To decide the corner for end point.
     */
    public void init(int orientation) {
        activate();

        if (orientation == 0) {
            this.set(2 * center.x, 0);    // Top Right
        }
        else if (orientation == 1) {
            this.set(0, 0);    // Top Left
        }
        else if (orientation == 2) {
            this.set(0, 2 * center.y);
        }
        else if (orientation == 3) {
            this.set(2 * center.x, 2 * center.y);
        }
    }

    public void rotate(int orientation) {
        if (orientation == 0) {
            this.x -= Main.screen.x / 30.0;
        }
        else if (orientation == 1) {
            this.y += Main.screen.y / 30.0;
        }
        else if (orientation == 2) {
            this.x += Main.screen.x / 30.0;
        }
        else if (orientation == 3) {
            this.y -= Main.screen.y / 30.0;
        }
    }

    public void reset (Magnus magnus) {
        deactivate();
    }
}
