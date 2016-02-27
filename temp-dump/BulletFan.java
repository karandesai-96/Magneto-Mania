package com.sdsmdg.kd.magnetomania;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;



public class BulletFan {

    /******************************************** CLASS MEMBERS ********************************************/
    protected final int SIZE                     = 7;
    protected Point     bulletPathCenter         = new Point(0,0);
    protected Point     bulletPosition[]         = new Point[SIZE];
    protected Point     bulletPrevPosition[]     = new Point[SIZE];

    protected Point     bulletDestination        = new Point(0,0);
    protected int       destinationPositionAngle;
    protected int[]     bulletAngleOfPropagation = new int[SIZE];
    protected int       bulletPathRadius;

    protected int       bulletsVelocity;

    protected Point     bulletDraw[]             = new Point[SIZE];

    protected Paint     bulletsPaint             = new Paint();
    protected final int bulletsRadius = (int)(Math.sqrt(Geometry.area(GameActivity.mScreenSize) / (300 * Math.PI)));
    /**--------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public BulletFan() {
        for(int i = 0; i < SIZE; i++) {
            this.bulletPosition[i]              = new Point(GameActivity.mScreenSize.x + 80, GameActivity.mScreenSize.y + 80);
            this.bulletPrevPosition[i]          = new Point(GameActivity.mScreenSize.x + 80, GameActivity.mScreenSize.y + 80);
            this.bulletDraw[i]                  = new Point(GameActivity.mScreenSize.x + 80, GameActivity.mScreenSize.y + 80);
            this.bulletAngleOfPropagation[i]    = 0;
        }

        this.destinationPositionAngle           = 0;
        this.bulletPathRadius                   = 0;
        this.bulletsVelocity                    = 0;
        bulletsPaint.setColor(Color.parseColor("#11FF22"));
    }
    /**--------------------------------------------------------------------------------------------------**/


    public void initBullets(MonsterBall monsterBall) {
        bulletPathCenter         = Geometry.setCoordinates(monsterBall.monsterPosition);
        for(int i = 0; i < SIZE; i++) {
            bulletPosition[i]    = Geometry.setCoordinates(bulletPathCenter);
            bulletPrevPosition[i]= Geometry.setCoordinates(bulletPosition[i]);
        }
        bulletDestination        = Geometry.setCoordinates(GameView.destinationPoint);
        bulletsVelocity = 20;

        destinationPositionAngle = (int)(Math.atan2(bulletDestination.y - bulletPosition[3].y,
                                                    bulletDestination.x - bulletPosition[3].x)*180/Math.PI);

        for(int i = 0; i < SIZE; i++) {
            bulletAngleOfPropagation[i] = destinationPositionAngle + 15*(i- SIZE/2);
        }
        bulletPathRadius         = 0;
    }


    public void setDirectionAndShoot() {
        bulletPathRadius += bulletsVelocity;
        for(int i = 0; i < SIZE; i++) {
            bulletPrevPosition[i]= Geometry.setCoordinates(bulletPosition[i]);
            bulletPosition[i]    = Geometry.setPolarCoordinates(bulletPathCenter, bulletPathRadius, bulletAngleOfPropagation[i]);
        }
    }


    public boolean didBulletGetTheFinger() {
        int distance;

        for (int i = 0; i < SIZE; i++) {
            distance = Geometry.distance(bulletPosition[i], GameView.fingerPosition);

            if (distance < bulletsRadius)
                return true;
        }
        return false;
    }


    public void drawBulletFan (Canvas canvas, float interpolation) {
        for(int i=0; i<SIZE; i++) {
            bulletDraw[i].x = (int)(((bulletPosition[i].x - bulletPrevPosition[i].x) * interpolation) + bulletPrevPosition[i].x);
            bulletDraw[i].y = (int)(((bulletPosition[i].y - bulletPrevPosition[i].y) * interpolation) + bulletPrevPosition[i].y);

            canvas.drawCircle((float) bulletDraw[i].x, (float) bulletDraw[i].y, (float) bulletsRadius, bulletsPaint);
        }
    }
}
