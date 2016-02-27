package com.sdsmdg.kd.gameworld;

import com.badlogic.gdx.Gdx;


public class GameRenderer {

    /** CLASS MEMBERS *******************************************************/
    private GameWorld gameWorld;
    /************************************************************************/

    public GameRenderer (GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void render() {
        Gdx.app.log("GameRenderer", "render");
    }
}
