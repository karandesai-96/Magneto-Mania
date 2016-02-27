package com.sdsmdg.kd.magnetomania;


import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class BoomerangTwister {

    /******************************************** CLASS MEMBERS ********************************************/
    protected Point         twisterPosition     = new Point(0,0);
    protected Point         twisterprevPosition = new Point(0,0);

    protected Point         twisterDraw         = new Point(0,0);

    protected Point         twisterDestination  = new Point(0,0);
    protected double        twisterVelocity;
    protected double        velocityMax;

    protected final int     twisterRadius       = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize)/ (50*Math.PI)));

    protected boolean       is_twister_thrown;
    protected int           twisterAngle;
    private SpriteAnimation animation = null;
    /**---------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public BoomerangTwister(GameView gameView) {
        this.twisterPosition.x = GameActivity.mScreenSize.x + 120;
        this.twisterPosition.y = GameActivity.mScreenSize.y + 120;
        this.is_twister_thrown = false;

        this.twisterVelocity   = 20;
        this.velocityMax       = 20;
        this.twisterAngle      = 0;
        this.animation         = new SpriteAnimation(gameView, 1, 1);
        this.animation.mBitmap = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.boomerang);
        this.animation.setSpriteUnitDimension();
    }
    /**--------------------------------------------------------------------------------------------------**/


    public void initTwister(MonsterBall monsterBall) {
        Random random = new Random();

        twisterPosition               = Geometry.setCoordinates(monsterBall.monsterPosition);
        twisterprevPosition           = Geometry.setCoordinates(twisterPosition);
        twisterVelocity               = random.nextInt(5) + 20;
        velocityMax                   = random.nextInt(5) + 20;
        twisterDestination            = Geometry.setCoordinates(GameView.fingerPosition);
        is_twister_thrown             = true;
        twisterAngle                  = 0;
    }


    public void attackTowardsFinger() {
        Point mVelocityComponent = Geometry.calcVelocityComponents(twisterDestination, GameView.initialPoint, (int)twisterVelocity);

        twisterprevPosition= Geometry.setCoordinates(twisterPosition);
        twisterVelocity   -= 0.25;
        twisterPosition.x += mVelocityComponent.x;
        twisterPosition.y += mVelocityComponent.y;
        twisterAngle       = (twisterAngle + 12) % 360;
    }


    public boolean didTwisterCaptureTheFinger () {
        int distance = Geometry.distance(GameView.fingerPosition, twisterPosition);

        return distance < this.twisterRadius;
    }


    public void drawBoomerangTwister(Canvas canvas, float interpolation) {
        twisterDraw.x = (int)(((twisterPosition.x - twisterprevPosition.x) * interpolation) + twisterprevPosition.x);
        twisterDraw.y = (int)(((twisterPosition.y - twisterprevPosition.y) * interpolation) + twisterprevPosition.y);

        animation.setRotatedCanvas(canvas, twisterDraw, twisterAngle);
        animation.setSourceDestinyRects(twisterDraw, twisterRadius);
        animation.drawBitmap(canvas);
        canvas.restore();
    }
}
