package game.scenes;

import game.bases.GameObject;

/**
 * Created by SNOW on 8/11/2017.
 */
public abstract class Scene {

    public abstract void init();

    public void deInit(){
        GameObject.clear();
    }
}
