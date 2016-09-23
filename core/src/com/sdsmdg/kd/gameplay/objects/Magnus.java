package com.sdsmdg.kd.gameplay.objects;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;


public class Magnus extends GameObject {
    private RandomXS128 random;
    public int sleepTime;


    public Magnus () {
        this.random = new RandomXS128();
        this.sleepTime = this.random.nextInt(15) + 15;

        this.x = (random.nextInt(2) == 1)? (int) Main.screen.x : 0;
        this.y = random.nextInt((int)Main.screen.y + 1);

        this.radius = (int)(Math.sqrt((Main.screenArea) / (12 * MathUtils.PI)));
        this.velocity = this.random.nextInt(15) + 15;
        this.velocity *= Main.scaleFactor;
    }


    public void prepareForSleep () {
        this.sleepTime = this.random.nextInt(15) + 15;
    }


    public void sleep () {
        this.sleepTime--;
    }


    public void prepareForAttack () {
        this.velocity = this.random.nextInt(15) + 15;
        this.velocity *= Main.scaleFactor;
        calcVelocityComponent(new Vector2(InputHandler.touch.x, InputHandler.touch.y));
    }

    public void attack (float delta) {
        mulAdd(this.velocityComponent, delta);
    }
}
