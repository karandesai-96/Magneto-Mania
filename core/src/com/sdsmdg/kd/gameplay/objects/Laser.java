package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 */
public class Laser extends GameObject {
    private RandomXS128 random;
    public Vector2 screenCenter;
    public int activeTime;

    /**
     * This array of Vector2 type will store the end points of the line segment
     * joining the center of the Magnus to the other end of the laser line. Thus
     * the Laser obstacle basically consists of four line segments whose one end
     * point is common and is at the center of the circle and other end point is
     * defined by the following points.
     */
    public Vector2[] endPoints;

    public Laser() {
        this.random = new RandomXS128();
        this.endPoints = new Vector2[5];
        this.activeTime = 0;

        this.screenCenter = new Vector2((Main.screen.x)/2,(Main.screen.y)/2);

        //the weapon is Initially deactivated.
        this.active = false;

        //sets the end points out of the screen initially
        endPoints[0].x = -10;
        endPoints[0].y = -10;
        endPoints[1].x = -10;
        endPoints[1].y = -10;
        endPoints[2].x = -10;
        endPoints[2].y = -10;
        endPoints[3].x = -10;
        endPoints[3].y = -10;
        endPoints[4].x = -10;
        endPoints[4].y = -10;
    }

    public void setMagnusVelocityComponents (Magnus magnus) {
        magnus.velocity = random.nextInt(15) + 15;
        magnus.calcVelocityComponent(screenCenter);
    }

    public void moveMagnusToCentre(Magnus magnus) {
        magnus.add(velocityComponent);
    }

    public void init (Magnus magnus) {
        setMagnusVelocityComponents(magnus);
        moveMagnusToCentre(magnus);

        endPoints[0] = screenCenter;
        endPoints[1].x = 0;
        endPoints[1].y = 0;
        endPoints[2].x = 0;
        endPoints[2].y = Main.screen.y;
        endPoints[3].x = Main.screen.x;
        endPoints[3].y = Main.screen.y;
        endPoints[4].x = Main.screen.x;
        endPoints[4].y = 0;
    }
}
