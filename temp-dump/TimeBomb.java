package com.sdsmdg.kd.magnetomania;


import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;



public class TimeBomb {
    /******************************************** CLASS MEMBERS ********************************************/
    protected Point     bombPosition        = new Point(0,0);
    protected Paint     bombPaint           = new Paint();

    protected int       bombInitialRadius   = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize) / (300 * Math.PI)));
    protected int       bombExplosionRadius = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize) / (2 * Math.PI)));
    protected int       explosionIncreaseRate = 5;

    protected int       bombCurrentRadius;
    protected int       timeBombCounter;
    protected boolean   is_bomb_planted;
    /**--------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public TimeBomb () {
        this.bombPosition.x     = GameActivity.mScreenSize.x + 30;
        this.bombPosition.y     = GameActivity.mScreenSize.y + 30;

        this.is_bomb_planted    = false;
        this.bombCurrentRadius  = 0;
        bombPaint.setColor(Color.parseColor("#0033EE"));
        bombPaint.setAlpha(255);
    }
    /**--------------------------------------------------------------------------------------------------**/


    public void initTimeBomb (MonsterBall monsterBall) {
        bombPosition            = Geometry.setCoordinates(monsterBall.monsterPosition);
        bombCurrentRadius       = bombInitialRadius;
        timeBombCounter         = 20;
        is_bomb_planted         = true;
        bombPaint.setAlpha(255);
        bombExplosionRadius     = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize) / (3 * Math.PI)));
    }


    public void tickTockCountDown () {
        timeBombCounter--;
    }


    public void increaseBombExplosion () {
        if(bombCurrentRadius < bombExplosionRadius) {
            bombCurrentRadius += explosionIncreaseRate;
            int alpha = bombPaint.getAlpha();
            alpha -= 5;
            bombPaint.setAlpha(alpha);
        }
        else {
            bombCurrentRadius = 0;
            bombPosition.set(GameActivity.mScreenSize.x - 100, GameActivity.mScreenSize.y - 100);
            bombExplosionRadius = 0;

        }

    }


    public boolean didFingerBecameVictimOfBombBlast () {
        int distance = Geometry.distance(GameView.fingerPosition, bombPosition);
        return distance < bombCurrentRadius;
    }
}
