package com.sdsmdg.kd.gameplay.controllers;

import com.sdsmdg.kd.gameplay.objects.HeatWave;
import com.sdsmdg.kd.gameplay.objects.Magnus;
import com.sdsmdg.kd.gameworld.GameWorld;
import com.sdsmdg.kd.magnetomania.Main;

/**
 * @author Karan Desai
 */
public class HeatWaveController {
    private HeatWave heatWave;

    public HeatWaveController(HeatWave heatWave) {
        this.heatWave = heatWave;
    }

    public void control(Magnus magnus) {
        if (heatWave.active) {
            if (heatWave.radius <= 3 * Main.screen.y / 2) {
                heatWave.expand();
            } else {
                heatWave.reset();
                GameWorld.gameState = GameWorld.GameState.NEXT_MAGNUS;
            }
        }
    }
}
