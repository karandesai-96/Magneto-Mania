package com.sdsmdg.kd.gameworld;

import com.sdsmdg.kd.gameplay.controllers.BulletController;
import com.sdsmdg.kd.gameplay.objects.Bullet;
import com.sdsmdg.kd.screens.GameScreen;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameplay.controllers.MagnusController;


public class GameWorld {
    public Magnus magnus;
    public Bullet bullet;
    public MagnusController magnusController;
    public BulletController bulletController;


    public GameWorld() {
        magnus = new Magnus();
        bullet = new Bullet();
        bulletController = new BulletController(bullet,magnus);
        magnusController = new MagnusController(magnus,bullet,bulletController);
    }


    public void update(float delta) {
        if (GameScreen.isTouched) {
            magnusController.control();
        }
    }
}
