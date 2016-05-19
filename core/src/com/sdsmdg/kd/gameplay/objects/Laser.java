package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 */
public class Laser extends GameObject {
    private RandomXS128 random;

    public Laser() {
        this.random = new RandomXS128();
    }

    public void setMagnusVelocityComponents (Magnus magnus) {
        magnus.velocity = random.nextInt(15) + 15;
        magnus.calcVelocityComponent(new Vector2(Main.screen.x,Main.screen.y));
    }

    public void moveMagnusToCentre(Magnus magnus) {
        magnus.add(velocityComponent);
    }
}
