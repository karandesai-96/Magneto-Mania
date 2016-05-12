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
        magnusController = new MagnusController(magnus);
        bulletController = new BulletController(bullet,magnus);
    }

    public void update(float delta) {
        if (GameScreen.isTouched) {
            if (MagnusController.weaponSelector%2==0){
                magnus.activate();
                bullet.deactivate();
            }
            else if (MagnusController.weaponSelector%2==1){
                magnus.deactivate();
                bullet.activate();
            }
            magnusController.control();
            bulletController.control();
        }
    }
}
