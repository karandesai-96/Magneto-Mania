package com.sdsmdg.kd.magnetomania;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;


public class LaserBeam {

    /******************************************** CLASS MEMBERS ********************************************/
    protected final Point   center = new Point(GameActivity.mScreenSize.x/2, GameActivity.mScreenSize.y/2);
    protected double        laserDestinationX;
    protected double        laserDestinationY;
    protected double        laserPrevDestinationX;
    protected double        laserPrevDestinationY;
    protected double        laserDrawX;
    protected double        laserDrawY;
    protected float         laserBeamAngle;
    protected Point         laserMidPoint = new Point(0,0);
    protected Paint         laserBeamPaint   = new Paint();
    private SpriteAnimation animation = null;
    /**--------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public LaserBeam(GameView gameView) {
        this.laserDestinationX = center.x;
        this.laserDestinationY = center.y;
        this.laserPrevDestinationX = this.laserDestinationX;
        this.laserPrevDestinationY = this.laserDestinationY;
        this.laserBeamPaint.setColor(Color.parseColor("#9C27B0"));
        this.laserBeamPaint.setAlpha(0);
        this.laserBeamPaint.setStrokeWidth(30);
        this.laserBeamPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.laserBeamPaint.setStrokeCap(Paint.Cap.ROUND);
        this.animation         = new SpriteAnimation(gameView, 1, 1);
        this.animation.mBitmap = BitmapFactory.decodeResource(gameView.getResources(), R.mipmap.laserbeam);
        this.animation.setSpriteUnitDimension();
        this.laserBeamAngle    = 0;
    }
    /**--------------------------------------------------------------------------------------------------**/


    public void initLaserBeam(int orientation) {
        switch (orientation) {
            case 0:
                this.laserDestinationX = 2 * center.x;
                this.laserDestinationY = 0;
                break;
            case 1:
                this.laserDestinationX = 0;
                this.laserDestinationY = 0;
                break;
            case 2:
                this.laserDestinationX = 0;
                this.laserDestinationY = 2 * center.y;
                break;
            case 3:
                this.laserDestinationX = 2 * center.x;
                this.laserDestinationY = 2 * center.y;
                break;
        }
        laserPrevDestinationX          = laserDestinationX;
        laserPrevDestinationY          = laserDestinationY;
        laserBeamPaint.setStrokeWidth(30);
        laserBeamPaint.setColor(Color.parseColor("#9C27B0"));
    }


    public void rotateBeam(int orientation) {

        switch (orientation) {
            case 0:
                laserPrevDestinationX = laserDestinationX;
                laserDestinationX -= (double) GameActivity.mScreenSize.x / 30.0;
                break;
            case 1:
                laserPrevDestinationY = laserDestinationY;
                laserDestinationY += (double) GameActivity.mScreenSize.y / 30.0;
                break;
            case 2:
                laserPrevDestinationX = laserDestinationX;
                laserDestinationX += (double) GameActivity.mScreenSize.x / 30.0;
                break;
            case 3:
                laserPrevDestinationY = laserDestinationY;
                laserDestinationY -= (double) GameActivity.mScreenSize.y / 30.0;
                break;
        }
    }


    public boolean didLaserBeamPenetrateTheFinger() {
        int destinationToCenterAngle = (int)(Math.atan2(laserDestinationY - center.y, laserDestinationX - center.x)*180/Math.PI);
        int fingerToCenterAngle = (int)(Math.atan2(GameView.fingerPosition.y - center.y, GameView.fingerPosition.x - center.x)*180/Math.PI);

        return (fingerToCenterAngle < destinationToCenterAngle + 3 && fingerToCenterAngle > destinationToCenterAngle - 3);
    }


    public void setLaserBeamPaint(int strokeWidth, String color, Canvas canvas, float interpolation) {
        laserBeamPaint.setStrokeWidth(strokeWidth);
        laserBeamPaint.setColor(Color.parseColor(color));

        laserDrawX = ((laserDestinationX - laserPrevDestinationX) * interpolation) + laserPrevDestinationX;
        laserDrawY = ((laserDestinationY - laserPrevDestinationY) * interpolation) + laserPrevDestinationY;

        canvas.drawLine((float) center.x, (float) center.y, (float) laserDrawX, (float) laserDrawY, laserBeamPaint);
        canvas.drawCircle((float)laserDrawX, (float)laserDrawY, strokeWidth/4, laserBeamPaint);
    }

    public void drawLaserBeam(Canvas canvas, float interpolation) {
        setLaserBeamPaint(45, "#9C27B0", canvas, interpolation);
        setLaserBeamPaint(43, "#AB47BC", canvas, interpolation);
        setLaserBeamPaint(39, "#B868C8", canvas, interpolation);
        setLaserBeamPaint(33, "#CE93D8", canvas, interpolation);
        setLaserBeamPaint(25, "#E1BEE7", canvas, interpolation);
        setLaserBeamPaint(20, "#F3E5F5", canvas, interpolation);
    }


    public void drawLaser (Canvas canvas) {
        laserBeamAngle = (float) (Math.atan2(laserDestinationY - center.y, laserDestinationX - center.x)*180/Math.PI);
        animation.setRotatedCanvas(canvas, center, (int)laserBeamAngle);

        laserMidPoint.set((int)(center.x + laserDestinationX)/2, (int)(center.y + laserDestinationY)/2);
        animation.fromSheet.set(0,0, animation.mBitmap.getWidth(), animation.mBitmap.getHeight());
        animation.toDisplay.set(center.x, center.y - 20, center.x + GameActivity.mScreenSize.y, center.y + 20);
        animation.drawBitmap(canvas);
        canvas.restore();
    }
}