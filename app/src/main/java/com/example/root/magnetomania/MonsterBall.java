package com.example.root.magnetomania;

import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;


public class MonsterBall {

    /******************************************** CLASS MEMBERS ********************************************/
    protected int monsterX;
    protected int monsterY;
    protected int monsterVelocity;

    protected Paint monsterPaint = new Paint();
    protected final int monsterRadius = 100;

    /*This is the time for which the monster sticks to the boundary, between two successive attacks.*/
    protected int monsterSleepTime;

    /*monsterAttackTrick is to randomize the way in which the monster attacks the finger.
     *Different values correspond to different calls of methods.*/
    protected int monsterAttackTrick;
    /**--------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public MonsterBall()
    {
        Random random = new Random();

        this.monsterY = random.nextInt(GameActivity.mScreenSize.y + 1);
        this.monsterX = random.nextInt(2);

        if(this.monsterX == 1)
            this.monsterX = GameActivity.mScreenSize.x;


        /*Velocity and sleeptime is randomized for each attack.*/
        this.monsterVelocity = random.nextInt(10) + 15;
        this.monsterSleepTime = random.nextInt(10) + 10;

        this.monsterAttackTrick = 0;
        monsterPaint.setColor(Color.parseColor("#FFFFFF"));
    }
    /**--------------------------------------------------------------------------------------------------**/



    /***************** METHOD BY WHICH MONSTER RUSHES AT THE FINGER ALONG LINE OF CONTACT *****************/
    public void attackFingerPosition(int attackAtX, int attackAtY, int attackFromX, int attackFromY) {

        /*distance is the length of he line of contact between monster and finger at the moment,
         * when the monster is resting on the wall, and just about to attack the finger. */
        int distance = (int) Math.sqrt((attackAtX - attackFromX) * (attackAtX - attackFromX) + (attackAtY - attackFromY) * (attackAtY - attackFromY));

        /*These are the x and y components of the velocity of the monster, so that the monster
         *travels with constant velocity during one rush towards the finger.*/
        int monsterVelocityX = monsterVelocity * (attackAtX - attackFromX) / distance;
        int monsterVelocityY = monsterVelocity * (attackAtY - attackFromY) / distance;

            monsterX += monsterVelocityX;
            monsterY += monsterVelocityY;
    }

    public boolean didMonsterGetTheFinger (int fingerX, int fingerY)
    {
        int distance = (int) Math.sqrt((this.monsterX - fingerX)*(this.monsterX - fingerX) + (this.monsterY - fingerY)*(this.monsterY - fingerY));

        if (distance < this.monsterRadius)
            return true;
        else
            return false;
    }
    /**--------------------------------------------------------------------------------------------------**/
}
