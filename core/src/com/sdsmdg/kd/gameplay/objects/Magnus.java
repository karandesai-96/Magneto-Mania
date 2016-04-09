package com.sdsmdg.kd.gameplay.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;


public class Magnus extends GameObject {
    private RandomXS128 random;
    public int sleepTime;


    public Magnus () {
        this.random = new RandomXS128();
        this.sleepTime = 0;

        if(random.nextInt(2) == 1)
            this.x = (int) Main.screen.x;
        else
            this.x = 0;

        this.y = random.nextInt((int)Main.screen.y + 1);
        Gdx.app.log("X: " + this.x, "Y: " + this.y);
        this.radius = (int)(Math.sqrt((Main.screenArea) / (12 * Math.PI)));
        this.velocity = random.nextInt(15) + 15;
        this.active = true;
    }


    public void prepareForSleep () {
        deactivate();
        Gdx.app.log("Deactivated", "" + active);
        sleepTime = random.nextInt(15) + 15;
        Gdx.app.log("Preparing to sleep, sleepTime: ", "" + sleepTime);
    }


    public void sleep () {
        sleepTime--;
        Gdx.app.log("Sleeping: ", "" + sleepTime);
        if (sleepTime < 1) {
            activate();
            Gdx.app.log("Activated !", "" + active);
        }
    }


    public void prepareForAttack () {
        velocity = random.nextInt(15) + 15;
        calcVelocityComponent(new Vector2(InputHandler.touch.x, InputHandler.touch.y));
        Gdx.app.log("Preparing to attack, components: ", "" + velocityComponent.x + " " + velocityComponent.y);
    }

    public void attack () {
        velocity -= 0.05;
        x += velocityComponent.x;
        y += velocityComponent.y;
    }
}
