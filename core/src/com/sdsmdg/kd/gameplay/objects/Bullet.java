package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Haresh Khanna
 * @author Karan Desai
 */
public class Bullet extends GameObject {
    public Bullet() {
        // Setting the radius such that the bullet is (1/300)th the size of screen.
        this.radius = (int) Math.sqrt(Main.screenArea/(300* MathUtils.PI));

        // The bullet should be out of screen when the game starts, hence its
        // coordinates are set a little outside the screen.
        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        this.velocity = 20;

        // Bullet is inactive when game starts.
        this.active = false;
    }

    public void initBullets(Magnus magnus) {
        /**
         * This method initializes bullets when time to shoot them arrives.
         * The bullets' center is set as Magnus' center and the direction to
         * shoot them is determined, by finger's position.
         *
         * @param magnus For using the coordinates of its center.
         */
        activate();
        this.x = magnus.x;
        this.y = magnus.y;

        calcVelocityComponent(new Vector2(InputHandler.touch.x,InputHandler.touch.y));
        Gdx.app.log("Bullet direction set.","");
    }

    public void shootBullet() {
        /**
         * This method adds the velocity components to current position of bullet,
         * hence making it move in a specific direction.
         */
        add(velocityComponent);
        Gdx.app.log("Bullet attacking, components:", " " + velocityComponent.x + " " + velocityComponent.y);
    }

    public void resetBullet(Magnus magnus) {
        /**
         * This method resets the bullets when they escape the screen while moving.
         * The bullets are deactivated and their center is set outside the screen again.
         * shoot them is determined, by finger's position.
         *
         * @param magnus For using the coordinates of its center.
         */
        deactivate();

        this.x = Main.screen.x + (4 * this.radius);
        this.y = Main.screen.y + (4 * this.radius);

        calcVelocityComponent(new Vector2(InputHandler.touch.x, InputHandler.touch.y));
        Gdx.app.log("Bullet has been reset.","");
    }

    public boolean didBulletGetTheFinger() {
        //some code
        return false;
    }
}
