package game.scenes;

import game.bases.GameObject;

/**
 * Created by SNOW on 8/12/2017.
 */
public abstract class Scene {
    public abstract void init();

    public void deInit(){
        GameObject.clear();
    }
}
