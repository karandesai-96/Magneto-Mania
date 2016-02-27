package com.sdsmdg.kd.magnetomania;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;


public class LightSaber {

    /******************************************** CLASS MEMBERS ********************************************/
    protected Point         lightSaberCenter   = new Point(0,0);
    protected Point         saberDestination   = new Point(0,0);

    protected double[]      lightSaberTipX = new double[4];
    protected double[]      lightSaberTipY = new double[4];
    protected double[]      lightSaberTip  = {0,0};

    protected double        saberCenterVelocity;
    protected double        lightSaberAngle;
    protected double        lightSaberOmega;

    protected Paint         saberCentralPaint  = new Paint();
    protected Paint         saberClassOnePaint = new Paint();
    protected Paint         saberClassTwoPaint = new Paint();

    protected final int     saberCentralRadius = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize) / (175 * Math.PI)));
    protected final int     saberTipRadius     = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize) / (4 * Math.PI)));

    protected boolean       is_saber_thrown;
    private SpriteAnimation animation = null;
    /**---------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public LightSaber(GameView gameView) {

        this.lightSaberCenter.x   = GameActivity.mScreenSize.x + 300;
        this.lightSaberCenter.y   = GameActivity.mScreenSize.y + 300;

        this.saberCenterVelocity  = 8;

        for (int i = 0; i < 4; i++) {
            this.lightSaberTipX[i] = 0;
            this.lightSaberTipY[i] = 0;
        }

        this.lightSaberAngle      = 0;
        this.lightSaberOmega      = 3;

        this.saberCentralPaint.setColor(Color.GRAY);

        this.saberClassOnePaint.setAlpha(3);
        this.saberClassTwoPaint.setAlpha(3);

        this.saberClassOnePaint.setStrokeCap(Paint.Cap.ROUND);
        this.saberClassTwoPaint.setStrokeCap(Paint.Cap.ROUND);

        this.saberClassOnePaint.setColor(Color.WHITE);
        this.saberClassTwoPaint.setColor(Color.WHITE);

        this.saberClassOnePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.saberClassTwoPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        this.saberClassOnePaint.setStrokeWidth(25);
        this.saberClassTwoPaint.setStrokeWidth(25);

        this.is_saber_thrown      = false;
        this.animation            = new SpriteAnimation(gameView, 1 ,1);
        this.animation.mBitmap    = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.sabercenter);
        this.animation.setSpriteUnitDimension();
    }
    /**--------------------------------------------------------------------------------------------------**/


    public void initLightSaberBlade(MonsterBall monsterBall) {
        lightSaberCenter = Geometry.setCoordinates(monsterBall.monsterPosition);
        saberDestination = Geometry.setCoordinates(GameView.fingerPosition);

        for (int i = 0; i < 4; i++) {
            lightSaberTipX[i] = lightSaberCenter.x + (saberTipRadius * Math.cos(Math.PI*i/2));
            lightSaberTipY[i] = lightSaberCenter.y - (saberTipRadius * Math.sin(Math.PI*i/2));
        }

        this.lightSaberAngle    = 0;
        this.saberClassOnePaint.setAlpha(3);
        this.saberClassTwoPaint.setAlpha(3);
    }


    public void swirlTowardsFinger() {
        Point mVelocityComponent = Geometry.calcVelocityComponents(saberDestination, GameView.initialPoint, (int)saberCenterVelocity);

        lightSaberCenter.x += mVelocityComponent.x;
        lightSaberCenter.y += mVelocityComponent.y;

        lightSaberAngle += lightSaberOmega;

        for(int i = 0; i < 4; i++) {
            lightSaberTipX[i] += mVelocityComponent.x;
            lightSaberTipY[i] += mVelocityComponent.y;
        }


        for(int i = 0; i < 4; i++) {
            lightSaberTip[0] = lightSaberTipX[i];
            lightSaberTip[1] = lightSaberTipY[i];
            lightSaberTip = Geometry.circularPathDisplacement(lightSaberTip, lightSaberCenter, saberTipRadius, lightSaberOmega);
            lightSaberTipX[i] = lightSaberTip[0];
            lightSaberTipY[i] = lightSaberTip[1];
        }
    }


    public boolean didSaberCutThroughTheFinger() {
        int distance = Geometry.distance(GameView.fingerPosition, lightSaberCenter);

        if(distance < saberCentralRadius) {
            return true;
        }
        int fingerToCenterAngle = 0;
        int tipToCenterAngle = 0;

        if(distance < saberTipRadius + 10) {
            fingerToCenterAngle = (int) (Math.atan2(GameView.fingerPosition.y - lightSaberCenter.y, GameView.fingerPosition.x - lightSaberCenter.x) * 180 / Math.PI);

            for (int i = 0; i < 4; i++) {
                tipToCenterAngle = (int) (Math.atan2(lightSaberTipY[i] - lightSaberCenter.y, lightSaberTipX[i] - lightSaberCenter.x) * 180 / Math.PI);
                if (fingerToCenterAngle > tipToCenterAngle - 5 && fingerToCenterAngle < tipToCenterAngle + 5) {
                    return true;
                }
            }
        }
        return false;
    }


    public void setSaberClassOnePaint(int strokeWidth, int A, int R, int G, int B, Canvas canvas) {
        saberClassOnePaint.setStrokeWidth(strokeWidth);
        saberClassOnePaint.setColor(Color.argb(A,R,G,B));

        for(int i = 0; i < 4; i++) {
            if(i % 2 == 0) {
                canvas.drawLine(lightSaberCenter.x, lightSaberCenter.y, (float) lightSaberTipX[i], (float) lightSaberTipY[i], saberClassOnePaint);
            }
        }
    }

    public void setSaberClassTwoPaint(int strokeWidth, int A, int R, int G, int B, Canvas canvas) {
        saberClassTwoPaint.setStrokeWidth(strokeWidth);
        saberClassTwoPaint.setColor(Color.argb(A, R, G, B));

        for(int i = 0; i < 4; i++) {
            if(i % 2 == 1) {
                canvas.drawLine(lightSaberCenter.x, lightSaberCenter.y, (float) lightSaberTipX[i], (float) lightSaberTipY[i], saberClassTwoPaint);
            }
        }
    }

    public void drawLightSaberBlade(Canvas canvas) {
        int alpha = saberClassOnePaint.getAlpha();
        if(alpha < 255) {
            if(alpha + 1 + alpha/20 >= 255) {
                alpha = 255;
            }
            else {
                alpha = (alpha + 1 + alpha / 20) % 256;
            }
            saberClassOnePaint.setAlpha(alpha);
        }

        setSaberClassOnePaint(30, alpha, 33, 150, 243, canvas);
        setSaberClassOnePaint(26, alpha, 100, 181, 246, canvas);
        setSaberClassOnePaint(18, alpha, 227, 242, 253, canvas);

        alpha = saberClassTwoPaint.getAlpha();
        if(alpha < 255) {
            if(alpha + 1 + alpha/20 >= 255) {
                alpha = 255;
            }
            else {
                alpha = (alpha + 1 + alpha / 20) % 256;
            }
            saberClassTwoPaint.setAlpha(alpha);
        }

        setSaberClassTwoPaint(30, alpha, 118, 255,   3, canvas);
        setSaberClassTwoPaint(26, alpha, 178, 255,  89, canvas);
        setSaberClassTwoPaint(18, alpha, 241, 255, 233, canvas);

        animation.setRotatedCanvas(canvas, lightSaberCenter, (int)lightSaberAngle);
        animation.setSourceDestinyRects(lightSaberCenter, saberCentralRadius);
        animation.drawBitmap(canvas);
        canvas.restore();
    }
}
