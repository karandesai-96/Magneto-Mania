package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameplay.controllers.MagnusController;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.screens.GameScreen;


public class GameWorld {
    /** CLASS MEMBERS *******************************************************/
    public Magnus            magnus;
    protected MagnusController  magnusController;
    /**--------------------------------------------------------------------**/

    /** CONSTRUCTOR *********************************************************/
    public GameWorld() {
        magnus = new Magnus();
        magnusController = new MagnusController(magnus);
    }
    /** UPDATE METHOD *******************************************************/
    public void update(float delta) {
        if (GameScreen.isTouched){
            magnusController.MagnusControl();
        }
        Gdx.app.log("GameWorld", "update");
    }
    /**--------------------------------------------------------------------**/
}
