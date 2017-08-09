package game.cameras;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderer.ImageRenderer;

/**
 * Created by SNOW on 8/9/2017.
 */
public class HP extends GameObject{

    public HP() {
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/banana.png"));
    }
}
