package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameplay.objects.Magnus;


/**
 * Created by Haresh on 08-03-2016.
 */
public class MagnusController {

    /** CLASS MEMBERS *****************************************************/
    private Magnus magnus;
    public int score;
    public Vector2 destinationPoint;
    public Vector2 initialPoint;
    /**--------------------------------------------------------------------**/

    /** CONSTRUCTOR *****************************************************/
    public MagnusController(Magnus magnus) {
        this.magnus = magnus;
        score = 0;
    }
    /**--------------------------------------------------------------------**/

    /** MAGNUS UPDATE METHODS *****************************************************/
    public void MagnusControl () {
        magnus.prepareForSleepAndAttack();
        magnus.attackFingerPosition();
    }
    /**--------------------------------------------------------------------**/
}
