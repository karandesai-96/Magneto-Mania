package com.sdsmdg.kd.gameworld;

import com.sdsmdg.kd.screens.GameScreen;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameplay.controllers.MagnusController;


public class GameWorld {
    public Magnus magnus;
    protected MagnusController magnusController;


    public GameWorld() {
        magnus = new Magnus();
        magnusController = new MagnusController(magnus);
    }


    public void update(float delta) {
        if (GameScreen.isTouched) {
            magnusController.control();
        }
    }
}
