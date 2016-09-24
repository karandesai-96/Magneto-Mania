package com.sdsmdg.kd.gameplay.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.sdsmdg.kd.gameplay.objects.HeatWave;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.helpers.InputHandler;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Karan Desai
 */
public class HeatWaveController {
    private HeatWave heatWave;

    public HeatWaveController(HeatWave heatWave) {
        this.heatWave = heatWave;
    }

    public void control(float delta) {
        if (heatWave.active) {
            if (heatWave.radius <= Main.d) {
                heatWave.expand(delta);
            } else {
                heatWave.reset();
                GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
            }
        }
    }


    public boolean check (Magnus magnus) {
        float distance = heatWave.dst(InputHandler.touch.x, InputHandler.touch.y);
        Gdx.app.log("X: " +InputHandler.touch.x, "Y: " + InputHandler.touch.y);
//        touchPosition.x = InputHandler.touch.x;
//        touchPosition.y = InputHandler.touch.y;
//        angleDifference = touchPosition.angle(magnus);
//        if (angleDifference < 0) {
//            angleDifference += 360;
//        }
//        Gdx.app.log("Angle", "" + angleDifference);
//        for (int i = 0; i < 5; i++) {
//            if (heatWave.dst(touchPosition) <= heatWave.radius[i] + 5 &&
//                    heatWave.dst(touchPosition) >= heatWave.radius[i] - 5) {
//                if (i % 2 == 0) {
//                    for (int j = 0; j < 10; j++) {
//                        if (angleDifference > heatWave.startAngle[j][0] &&
//                                angleDifference < heatWave.startAngle[j][0] + heatWave.sweepAngle) {
//                            Gdx.app.log("GameOver", "Collision with HeatWave!");
//                            return true;
//                        }
//                    }
//                } else {
//                    for (int j = 0; j < 10; j++) {
//                        if (angleDifference > heatWave.startAngle[j][1] &&
//                                angleDifference < heatWave.startAngle[j][1] + heatWave.sweepAngle) {
//                            Gdx.app.log("GameOver", "Collision with HeatWave!");
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
        return distance < heatWave.radius;
    }
}
