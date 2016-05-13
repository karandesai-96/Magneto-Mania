package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * Created by Haresh on 09-05-2016.
 */
public class Bullet extends GameObject{
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

    public void initBullets(Magnus magnus){
        activate();
        this.x = magnus.x;
        this.y = magnus.y;
    }

    public void setDirection(){
        calcVelocityComponent(new Vector2(InputHandler.touch.x,InputHandler.touch.y));
        Gdx.app.log("Bullet direction set.","");
    }

    public void shootBullet(){
        add(velocityComponent);
        Gdx.app.log("Bullet attacking, components: ", "" + velocityComponent.x + " " + velocityComponent.y);
    }

    public boolean didBulletGetTheFinger(){
        //some code
        return false;
    }
}
