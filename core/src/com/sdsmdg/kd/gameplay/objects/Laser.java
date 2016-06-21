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

//    public void reset (Magnus magnus) {
//        deactivate();
//        fixMagnusPathToFinger(magnus);
//        moveMagnusToBoundary(magnus);
//        numberOfTurns = 0;
//        turnTime = 0;
//        pointVelocity[0] = pointVelocity[1] = 0;
//        endPoints[0].x = endPoints[1].x = endPoints[2].x = endPoints[3].x = endPoints[4].x = -10;
//        endPoints[0].y = endPoints[1].y = endPoints[2].y = endPoints[3].y = endPoints[4].y = -10;
//        magnus.velocity = 0;
//    }
//
//    public void rotateLaser () {
//        if (endPoints[1].y < Main.screen.y || endPoints[2].x < Main.screen.x
//                || endPoints[3].y > 0 || endPoints[4].x>0) {
//            endPoints[2].x = endPoints[2].x + pointVelocity[0];
//            endPoints[4].x = endPoints[4].x - pointVelocity[0];
//            endPoints[1].y = endPoints[1].y + pointVelocity[1];
//            endPoints[3].y = endPoints[3].y - pointVelocity[1];
//        }
//        else {
//            numberOfTurns --;
//            Vector2 temp = endPoints[1];
//            endPoints[1] = endPoints[4];
//            endPoints[4] = endPoints[3];
//            endPoints[3] = endPoints[2];
//            endPoints[2] = temp;
//        }
//    }
}
