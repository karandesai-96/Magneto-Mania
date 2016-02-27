package com.sdsmdg.kd.magnetomania;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;



public class MagnetRocket {

    /******************************************** CLASS MEMBERS ********************************************/
    protected Point         rocketPosition = new Point(0,0);
    protected Point         rocketPrevPosition = new Point(0,0);
    protected Point         rocketDraw = new Point(0,0);

    protected double        rocketVelocity;

    protected Paint         rocketPaint = new Paint();
    protected final int     rocketRadius = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize)/ (32*Math.PI)));

    protected int           rocketXhaustTime;
    protected float         rocketTipAngle;
    private SpriteAnimation animation = null;
    private int             animationIterator;
    /**---------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public MagnetRocket(GameView gameView) {
        this.rocketPosition.x   = GameActivity.mScreenSize.x + 80;
        this.rocketPosition.y   = GameActivity.mScreenSize.y + 80;
        this.rocketVelocity     = 0;
        this.rocketPrevPosition = Geometry.setCoordinates(rocketPosition);

        this.rocketXhaustTime   = 0;
        this.rocketPaint.setColor(Color.parseColor("#CC1100"));
        this.animation          = new SpriteAnimation(gameView, 2, 4);
        this.animation.mBitmap  = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.rocket);
        this.animation.setSpriteUnitDimension();
        this.animationIterator  = 0;
    }
    /**--------------------------------------------------------------------------------------------------**/


    public void initRocket(MonsterBall monsterBall) {
        Random random = new Random();

        rocketPosition          = Geometry.setCoordinates(monsterBall.monsterPosition);
        rocketVelocity          = random.nextInt(10) + 10;
        rocketXhaustTime        = random.nextInt(50) + 150;
        rocketPrevPosition      = Geometry.setCoordinates(rocketPosition);

        rocketPaint.setColor(Color.parseColor("#CC1100"));
    }


    public void rocketTrackFinger () {
        Point rVelocityComponent = Geometry.calcVelocityComponents(GameView.fingerPosition, rocketPosition, (int)rocketVelocity);

        rocketPrevPosition      = Geometry.setCoordinates(rocketPosition);
        rocketVelocity          += 0.05;
        rocketPosition.x        += rVelocityComponent.x;
        rocketPosition.y        += rVelocityComponent.y;

        animationIterator = ++animationIterator % 4;
        if(animationIterator == 0) {
            animation.iteratorIncrement();
        }
    }


    public boolean didRocketGetTheFinger () {
        int distance = Geometry.distance(GameView.fingerPosition, rocketPosition);

        return distance < this.rocketRadius;
    }


    public void drawMagnetRocket (Canvas canvas, float interpolation) {
//        rocketTipAngle = (float)Math.atan2((double)GameView.fingerPosition.y - rocketPosition.y, (double)GameView.fingerPosition.x - rocketPosition.x)*180/(float)Math.PI + 90;
//        animation.setSourceDestinyRects(rocketPosition, rocketRadius);
//
//        animation.toDisplay.set(rocketPosition.x - rocketRadius, rocketPosition.y - rocketRadius - 10, rocketPosition.x + rocketRadius, rocketPosition.y + rocketRadius + 10);
//
//        animation.setRotatedCanvas(canvas, rocketPosition, (int)rocketTipAngle);
//        animation.drawBitmap(canvas);
//        canvas.restore();

        rocketDraw.x = (int)(((rocketPosition.x - rocketPrevPosition.x) * interpolation) + rocketPrevPosition.x);
        rocketDraw.y = (int)(((rocketPosition.y - rocketPrevPosition.y) * interpolation) + rocketPrevPosition.y);

        canvas.drawCircle((float) rocketDraw.x, (float) rocketDraw.y, (float) rocketRadius, rocketPaint);
    }
}