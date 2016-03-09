package com.sdsmdg.kd.gameplay.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameplay.controllers.MagnusController;
import com.sdsmdg.kd.gameplay.utilities.Geometry;
import com.sdsmdg.kd.helpers.InputHandler;

public class Magnus {
    /** CLASS MEMBERS *******************************************************/
    public Vector2             magnusPosition;
    MagnusController           magnusController;
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
        this.magnusController  = new MagnusController(this);
        int gameWidth          = 136;
        int gameHeight         = (int)((screenHeight/screenWidth) * gameWidth);

        int x = random.nextInt(2);
        if(x == 1) {
            x = (int)screenWidth;
        }
        int y = random.nextInt((int)screenHeight + 1);

        this.magnusPosition     = new Vector2(x, y);
        this.magnusRadius       = (int)(Math.sqrt((screenWidth*screenHeight) / (int)(12 * Math.PI)));
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

            magnusController.destinationPoint = new Vector2(InputHandler.touch.x,InputHandler.touch.y);
            magnusController.initialPoint = magnusPosition;
            magnusVelocity = random.nextInt(15) + 15 + (int)(magnusController.score / 1000);
        }
    }

    public void attackFingerPosition(){
        Vector2 mVelocityComponent    = Geometry.calcVelocityComponents(magnusController.destinationPoint,magnusController.initialPoint,(int)magnusVelocity);
        magnusVelocity               -= 0.05;
        magnusPosition.x             += mVelocityComponent.x;
        magnusPosition.y             += mVelocityComponent.y;

    }
    /**--------------------------------------------------------------------**/
}
