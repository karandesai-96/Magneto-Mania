package com.sdsmdg.kd.gameplay.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;

public class Magnus {
    /** CLASS MEMBERS *******************************************************/
    public Vector2             magnusPosition;
    public double              magnusVelocity;
    public int                 magnusRadius;
    float                      screenWidth;
    float                      screenHeight;
    private RandomXS128        random;
    /**--------------------------------------------------------------------**/

    /** CONSTRUCTOR *********************************************************/
    public Magnus() {
        this.screenWidth       = Gdx.graphics.getWidth();
        this.screenHeight      = Gdx.graphics.getHeight();
        this.random            = new RandomXS128();
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

    /**MOTION REGULATIONS *****************************************************/
    public void prepareForSleepAndAttack(){
        if (magnusPosition.x >= screenWidth-5 || magnusPosition.x <= 5 ||
                magnusPosition.y >= screenHeight-5 || magnusPosition.y <= 5) {

            // For preventing glitchy movement at the boundary.
            if (magnusPosition.x > screenWidth) {
                magnusPosition.x = screenWidth;
            }
            else if (magnusPosition.x < 0) {
                magnusPosition.x = 0;
            }
            else if (magnusPosition.y > screenHeight) {
                magnusPosition.y = screenHeight;
            }
            else if (magnusPosition.y < 0) {
                magnusPosition.y = 0;
            }

            //GameView.destinationPoint = Geometry.setCoordinates(GameView.fingerPosition);
            //GameView.initialPoint     = Geometry.setCoordinates(magnusPosition);

            //magnusPrevPosition       = Geometry.setCoordinates(magnusPosition);

            //magnusAttackTrick = 0;
            //magnusVelocity  = random.nextInt(15) + 15 + (int)(GameView.Score / 1000);
            //magnusSleepTime = random.nextInt(15) + 15;
        }
    }

    public void attackFingerPosition(){

    }
    /**--------------------------------------------------------------------**/
}
