package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 */
public class Laser extends GameObject {
    private RandomXS128 random;
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

        //the weapon is Initially deactivated.
        this.active = false;
        endPoints[0] = new Vector2(-10,-10);
        endPoints[1] = new Vector2(-10,-10);
        endPoints[2] = new Vector2(-10,-10);
        endPoints[3] = new Vector2(-10,-10);
        endPoints[4] = new Vector2(-10,-10);
    }

    public void setMagnusVelocityComponents (Magnus magnus) {
        magnus.velocity = random.nextInt(15) + 15;
        magnus.calcVelocityComponent(new Vector2(Main.screen.x,Main.screen.y));
    }

    public void moveMagnusToCentre(Magnus magnus) {
        magnus.add(velocityComponent);
    }
}
