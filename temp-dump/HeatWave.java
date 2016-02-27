package com.sdsmdg.kd.magnetomania;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;


public class HeatWave {

    /******************************************** CLASS MEMBERS ********************************************/
    protected Point  heatCenter = new Point(0,0);

    protected int    heatWaveVelocity;
    protected int    heatWaveRadius;
    protected Paint  heatWavePaint = new Paint();
    /**---------------------------------------------------------------------------------------------------**/


    /********************************************* CONSTRUCTOR *********************************************/
    public HeatWave() {
        this.heatCenter.x     = GameActivity.mScreenSize.x + 80;
        this.heatCenter.y     = GameActivity.mScreenSize.y + 80;
        this.heatWaveVelocity = 10;
        this.heatWaveRadius   = 0;
        this.heatWavePaint.setAlpha(10);
        this.heatWavePaint.setColor(Color.YELLOW);
        this.heatWavePaint.setStrokeWidth(20);
        this.heatWavePaint.setStyle(Paint.Style.STROKE);
        this.heatWavePaint.setStrokeCap(Paint.Cap.BUTT);
    }
    /**--------------------------------------------------------------------------------------------------**/


    public void initHeatWave(MonsterBall monsterBall) {
        heatCenter          = Geometry.setCoordinates(monsterBall.monsterPosition);
        heatWaveRadius      = 0;
        heatWaveVelocity    = 10;
        heatWavePaint.setAlpha(10);
    }


    public RectF setHeatWaveSize(Point center) {
        RectF heatRect     = new RectF();
        heatRect.left      = center.x - heatWaveRadius;
        heatRect.top       = center.y - heatWaveRadius;
        heatRect.right     = center.x + heatWaveRadius;
        heatRect.bottom    = center.y + heatWaveRadius;

        int alpha = heatWavePaint.getAlpha();
        if(alpha < 255) {
            alpha += 5;
            if(alpha > 255) {
                alpha = 255;
            }
        }
        heatWavePaint.setAlpha(alpha);
        heatWaveRadius += heatWaveVelocity;
        return heatRect;
    }


    public void drawHeatWave(Canvas canvas, RectF heatRect, int startAngle) {
        for(int i=0; i<6; i++) {
            canvas.drawArc(heatRect, startAngle + 60*i, 30, false, heatWavePaint);
        }
    }


    public boolean didHeatWaveBurnTheFinger (int waveType) {
        int distance = Geometry.distance(GameView.fingerPosition, heatCenter);
        int angle = (int)(Math.atan2((double)(GameView.fingerPosition.y - heatCenter.y), (double)(GameView.fingerPosition.x - heatCenter.x))*180/Math.PI);

        if (distance <= heatWaveRadius+5 && distance >= heatWaveRadius-30) {
            if(waveType == 1) {
                return ((angle > 30 && angle < 60)  || (angle > 90  && angle < 120) || (angle > 150  && angle < 180) ||
                        (angle < 0  && angle > -30) || (angle < -60 && angle > -90) || (angle < -120 && angle > -150));
            }
            else {
                return (angle > 0   && angle < 30)  || (angle > 60  && angle < 90)   || (angle > 120 && angle < 150) ||
                       (angle < -30 && angle > -60) || (angle < -90 && angle > -120) || (angle < -150 && angle > -180);
            }
        }
        return false;
    }
}
