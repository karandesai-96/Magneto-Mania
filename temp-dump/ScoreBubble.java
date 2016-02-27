package com.sdsmdg.kd.magnetomania;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;



public class ScoreBubble {
    /******************************************** CLASS MEMBERS ********************************************/
    protected final Point   center       = new Point(GameActivity.mScreenSize.x/2, GameActivity.mScreenSize.y/2);

    private   GameView      gameView;
    protected int           bubbleStartTheta;
    protected int           orbitRadius;
    protected Point         bubbleCenter = new Point(0,0);
    protected int           bubbleValue;
    protected int           bubbleRadius;

    protected int           bubbleTheta;
    protected int           bubbleOmega;
    protected Bitmap        bubbleBitmap;
    protected Bitmap        bonusBitmap;

    protected Paint         bubblePaint  = new Paint();
    protected Paint         bonusPaint   = new Paint();

    protected Random        random       = new Random();
    protected final int[]   scoreValues  = {50, 100, 250, 500};
    protected boolean       is_bubble_taken;
    protected boolean       is_time_out;
    protected boolean       is_to_fade_out;
    protected boolean       clockwise;
    protected boolean       is_luck_strong;

    protected Rect          fromSheet = new Rect();
    protected Rect          toDisplay = new Rect();
    /**---------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public ScoreBubble(GameView gameView) {
        this.gameView        = gameView;
        this.bubblePaint.setColor(Color.TRANSPARENT);
        this.bonusPaint.setColor(Color.TRANSPARENT);
        this.bonusPaint.setAlpha(255);
        this.is_bubble_taken = false;
        this.is_time_out     = false;
        this.is_to_fade_out  = false;
        this.is_luck_strong  = false;
        this.clockwise       = random.nextBoolean();
    }
    /**--------------------------------------------------------------------------------------------------**/


    public void initBubble () {
        bubbleStartTheta                = random.nextInt(360) - 180;
        orbitRadius                     = random.nextInt(3*GameActivity.mScreenSize.x/8) + GameActivity.mScreenSize.x/8;
        bubbleCenter                    = Geometry.setPolarCoordinates(center, orbitRadius, bubbleStartTheta);
        bubbleValue                     = scoreValues[random.nextInt(4)];

        if(bubbleValue == 250 || bubbleValue == 500) {
            is_luck_strong              = random.nextBoolean();
            if(!is_luck_strong) {
                if(bubbleValue == 250) {
                    bubbleValue = 50;
                }
                else {
                    bubbleValue = 100;
                }
            }
        }

        bubbleRadius                    = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize) / (100 * Math.PI)));
        bubbleTheta                     = bubbleStartTheta;
        bubbleOmega                     = 2;

        switch(bubbleValue) {
            case 50:
                bubbleBitmap = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.score50);
                bonusBitmap  = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.score50);
                break;
            case 100:
                bubbleBitmap = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.score100);
                bonusBitmap  = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.score100);
                break;
            case 250:
                bubbleBitmap = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.score250);
                bonusBitmap  = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.score250);
                break;
            case 500:
                bubbleBitmap = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.score500);
                bonusBitmap  = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.score500);
                break;
        }

        bubblePaint.setAlpha(0);
        is_bubble_taken                 = false;
        is_time_out                     = false;
        clockwise                       = random.nextBoolean();
        fromSheet.set(0, 0, bubbleBitmap.getWidth(), bubbleBitmap.getHeight());
    }


    public void bubbleClockwiseFadeIn () {
        bubbleTheta          += bubbleOmega;
        bubbleCenter          = Geometry.setPolarCoordinates(center, orbitRadius, bubbleTheta);
        bubblePaint.setAlpha(bubblePaint.getAlpha() + 3);
    }

    public void bubbleClockwiseDisplacement () {
        bubbleTheta          += bubbleOmega;
        bubbleCenter          = Geometry.setPolarCoordinates(center, orbitRadius, bubbleTheta);

        if(bubbleTheta > bubbleStartTheta + 360) {
            is_to_fade_out = true;
        }
    }

    public void bubbleClockwiseFadeOut () {
        bubbleTheta          += bubbleOmega;
        bubbleCenter          = Geometry.setPolarCoordinates(center, orbitRadius, bubbleTheta);
        bubblePaint.setAlpha(bubblePaint.getAlpha() - 3);

        if(bubblePaint.getAlpha() < 5) {
            is_time_out = true;
        }
    }

    public void bubbleAntiClockwiseFadeIn () {
        bubbleTheta          -= bubbleOmega;
        bubbleCenter          = Geometry.setPolarCoordinates(center, orbitRadius, bubbleTheta);
        bubblePaint.setAlpha(bubblePaint.getAlpha() + 3);
    }

    public void bubbleAntiClockwiseDisplacement () {
        bubbleTheta          -= bubbleOmega;
        bubbleCenter          = Geometry.setPolarCoordinates(center, orbitRadius, bubbleTheta);

        if(bubbleTheta < bubbleStartTheta - 360) {
            is_to_fade_out = true;
        }
    }

    public void bubbleAntiClockwiseFadeOut () {
        bubbleTheta          -= bubbleOmega;
        bubbleCenter          = Geometry.setPolarCoordinates(center, orbitRadius, bubbleTheta);
        bubblePaint.setAlpha(bubblePaint.getAlpha() - 3);

        if(bubblePaint.getAlpha() < 5) {
            is_time_out = true;
        }
    }


    public void drawBubble (Canvas canvas) {
        switch(bubbleValue) {
            case 50:
                toDisplay.set(bubbleCenter.x - bubbleRadius - 5, bubbleCenter.y - bubbleRadius - 5,
                              bubbleCenter.x + bubbleRadius + 5, bubbleCenter.y + bubbleRadius + 5);
                break;
            case 100:
                toDisplay.set(bubbleCenter.x - bubbleRadius - 10, bubbleCenter.y - bubbleRadius - 10,
                              bubbleCenter.x + bubbleRadius + 10, bubbleCenter.y + bubbleRadius + 10);
                break;
            case 250:
                toDisplay.set(bubbleCenter.x - bubbleRadius - 15, bubbleCenter.y - bubbleRadius - 15,
                              bubbleCenter.x + bubbleRadius + 15, bubbleCenter.y + bubbleRadius + 15);
                break;
            case 500:
                toDisplay.set(bubbleCenter.x - bubbleRadius - 20, bubbleCenter.y - bubbleRadius - 20,
                              bubbleCenter.x + bubbleRadius + 20, bubbleCenter.y + bubbleRadius + 20);
                break;
        }
        canvas.drawBitmap(bubbleBitmap, fromSheet, toDisplay, bubblePaint);
    }


    public void drawBonusScore (Canvas canvas) {
        fromSheet.set(0, 0, bonusBitmap.getWidth(), bonusBitmap.getHeight());

        if(bonusPaint.getAlpha() > 0) {
            int alpha = bonusPaint.getAlpha();
            bonusPaint.setAlpha(alpha - 3);

            toDisplay.set(bubbleCenter.x - bubbleRadius - (51 - alpha / 5), bubbleCenter.y - bubbleRadius - (51 - alpha / 5),
                    bubbleCenter.x + bubbleRadius + (51 - alpha/5), bubbleCenter.y + bubbleRadius + (51 - alpha/5));

            canvas.drawBitmap(bonusBitmap, fromSheet, toDisplay, bonusPaint);
        }
        else {
            is_time_out = true;
        }
    }
}
