package com.sdsmdg.kd.gameplay.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;

public class Magnus {
    /** CLASS MEMBERS *******************************************************/
    private Vector2       magnusPosition;
    private double        magnusVelocity;
    private int           magnusRadius;

    private RandomXS128 random;
    /**--------------------------------------------------------------------**/

    /** CONSTRUCTOR *********************************************************/
    public Magnus() {
        float screenWidth       = Gdx.graphics.getWidth();
        float screenHeight      = Gdx.graphics.getHeight();
        int gameWidth           = 136;
        int gameHeight          = (int)((screenHeight/screenWidth) * gameWidth);

        int x = random.nextInt(2);
        if(x == 1) {
            x = gameWidth;
        }
        int y = random.nextInt(gameHeight + 1);

        this.magnusPosition     = new Vector2(x, y);
        this.magnusRadius       = gameWidth * gameHeight / (int)(12 * Math.PI);
        this.magnusVelocity     = random.nextInt(15) + 15;
    }
    /**--------------------------------------------------------------------**/

    /** GETTER FUNCTIONS *****************************************************/
    public Vector2 getMagnusPosition() {
        return magnusPosition;
    }

    public float getMagnusRadius() {
        return magnusRadius;
    }
    /**--------------------------------------------------------------------**/

    /**SETTER FUNCTIONS *****************************************************/
        public void setMagnusPosition(Vector2 a) {
        magnusPosition = a;
    }
    /**--------------------------------------------------------------------**/
}
