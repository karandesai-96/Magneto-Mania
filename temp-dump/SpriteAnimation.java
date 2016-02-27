package com.sdsmdg.kd.magnetomania;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;


public class SpriteAnimation {

    /******************************************** CLASS MEMBERS ********************************************/
    protected GameView    mGameView;
    protected Bitmap      mBitmap;

    protected int         BMP_ROWS;
    protected int         BMP_COLS;
    protected int         bmpUnitsOnSheet;

    protected Point       spriteUnitDimension = new Point(0,0);
    protected int         spriteSheetIterator;

    protected Rect        fromSheet = new Rect();
    protected Rect        toDisplay = new Rect();
    /**--------------------------------------------------------------------------------------------------**/



    public SpriteAnimation (GameView gameView, int rows, int cols) {
        this.mGameView          = gameView;
        this.BMP_ROWS           = rows;
        this.BMP_COLS           = cols;
        this.bmpUnitsOnSheet    = rows * cols;
        this.spriteSheetIterator= 0;
    }


    public void setSpriteUnitDimension () {
        spriteUnitDimension.x   = mBitmap.getWidth()  / BMP_COLS;
        spriteUnitDimension.y   = mBitmap.getHeight() / BMP_ROWS;
    }


    public void iteratorIncrement () {
        spriteSheetIterator     = ++spriteSheetIterator % bmpUnitsOnSheet;
    }


    public void setSourceDestinyRects (Point center, int radius) {
        int srcX = (spriteSheetIterator % BMP_COLS) * spriteUnitDimension.x;
        int srcY = (spriteSheetIterator / BMP_COLS) * spriteUnitDimension.y;

        fromSheet.set(srcX, srcY, srcX + spriteUnitDimension.x, srcY + spriteUnitDimension.y);
        toDisplay.set(center.x - radius, center.y - radius, center.x + radius, center.y + radius);
    }


    public void setRotatedCanvas (Canvas canvas, Point axis, int angle) {
        canvas.save();
        canvas.rotate(angle, axis.x, axis.y);
    }


    public void drawBitmap(Canvas canvas) {
        canvas.drawBitmap(mBitmap, fromSheet, toDisplay, null);
    }
}
