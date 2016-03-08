package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameplay.controllers.MagnusController;
import com.sdsmdg.kd.gameplay.objects.Magnus;


public class GameWorld {
    /** CLASS MEMBERS *******************************************************/

    private Magnus magnus;
    private MagnusController magnusController;

    /**--------------------------------------------------------------------**/
    /** MAGNUS RELATED *******************************************************/

    Vector2 magnusPosition = magnus.getMagnusPosition();
    float   magnusRadius   = magnus.getMagnusRadius();
    /**--------------------------------------------------------------------**/

    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        //Update code
    }
}
