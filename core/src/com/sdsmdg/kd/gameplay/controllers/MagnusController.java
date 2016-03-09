package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameplay.objects.Magnus;

import java.util.Random;

/**
 * Created by Haresh on 08-03-2016.
 */
public class MagnusController {

    /** CLASS MEMBERS *****************************************************/
    private Magnus magnus;
    /**--------------------------------------------------------------------**/

    /** CONSTRUCTOR *****************************************************/
    public MagnusController(Magnus magnus) {
        this.magnus = magnus;
    }
    /**--------------------------------------------------------------------**/

    /** MAGNUS UPDATE METHODS *****************************************************/
    public void MagnusControl () {
        magnus.prepareForSleepAndAttack();
        magnus.attackFingerPosition();
    }
    /**--------------------------------------------------------------------**/
}
