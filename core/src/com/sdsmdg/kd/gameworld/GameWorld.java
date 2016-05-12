package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.math.RandomXS128;
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
    private RandomXS128 randomXS128;


    public GameWorld() {
        magnus = new Magnus();
        bullet = new Bullet();
        magnusController = new MagnusController(magnus);
        bulletController = new BulletController(bullet,magnus);
        randomXS128 = new RandomXS128();
    }

    public void update(float delta) {
        if (GameScreen.isTouched) {
            if (magnus.sleepTime==1 && !magnus.active){
                int weaponSelector = randomXS128.nextInt();
                if (weaponSelector %2==0){
                    magnus.sleepTime = 0;
                    magnus.activate();
                    magnus.prepareForAttack();
                    magnus.attack();
                }
                else if (weaponSelector %2==1){
                    bullet.activate();
                    bullet.initBullets(magnus);
                    bullet.setDirection();
                }
            }
            magnusController.control();
            bulletController.control();
        }
    }
}
