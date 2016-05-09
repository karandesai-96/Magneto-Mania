package com.sdsmdg.kd.gameplay.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * Created by Haresh on 09-05-2016.
 */
public class Bullet extends GameObject{
    public Bullet() {
        this.x = 0;
        this.y = 0;
        this.velocity = 0;
        this.radius = (int) Math.sqrt(Main.screenArea/(300* MathUtils.PI));
        Gdx.app.log("X: " + this.x, "Y: " + this.y);
        this.active = false;
    }

    public void initBullets(){
        this.active = true;
        this.x = GameWorld.magnus.x;
        this.y = GameWorld.magnus.y;
        this.velocity = 20;
    }

    public void setDirectionAndShoot(){
        calcVelocityComponent(new Vector2(InputHandler.touch.x,InputHandler.touch.y));
        add(velocityComponent);
        Gdx.app.log("Bullet preparing to attack, components: ", "" + velocityComponent.x + " " + velocityComponent.y);
    }



    public boolean didBulletGetTheFinger(){
        //some code
        return false;
    }
}
