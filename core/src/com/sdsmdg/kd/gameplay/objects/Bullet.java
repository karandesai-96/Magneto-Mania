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
        this.x = 0;
        this.y = 0;
        this.velocity = 0;
        this.radius = (int) Math.sqrt(Main.screenArea/(300* MathUtils.PI));
        Gdx.app.log("X: " + this.x, "Y: " + this.y);
        this.active = false;
    }

    public void initBullets(Magnus magnus){
        activate();
        this.x = magnus.x;
        this.y = magnus.y;
        this.velocity = 20;
    }

    public void setDirection(){
        calcVelocityComponent(new Vector2(InputHandler.touch.x,InputHandler.touch.y));
        Gdx.app.log("Bullet direction set.","");
    }

    public void shootBullet(){
        add(velocityComponent);
        Gdx.app.log("Bullet preparing to attack, components: ", "" + velocityComponent.x + " " + velocityComponent.y);
    }

    public boolean didBulletGetTheFinger(){
        //some code
        return false;
    }
}
