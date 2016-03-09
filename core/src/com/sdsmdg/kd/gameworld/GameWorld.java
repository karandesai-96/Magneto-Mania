package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameplay.controllers.MagnusController;
import com.sdsmdg.kd.gameplay.objects.Magnus;


public class GameWorld {
    /** CLASS MEMBERS *******************************************************/

    protected Magnus magnus = new Magnus();
    protected MagnusController magnusController = new MagnusController(magnus);

    /**--------------------------------------------------------------------**/
    /** MAGNUS RELATED *******************************************************/

    /**--------------------------------------------------------------------**/

    /** UPDATE METHOD *******************************************************/
    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        //Update code
    }
    /**--------------------------------------------------------------------**/
}
