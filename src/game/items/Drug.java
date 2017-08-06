package game.items;

import game.Utils;
import game.bases.GameObject;
import game.bases.physics.BoxCollider;
import game.bases.physics.Physicbody;
import game.bases.renderer.ImageRenderer;

import javax.swing.*;

/**
 * Created by Nttung PC on 8/4/2017.
 */
public class Drug extends GameObject implements Physicbody{
    BoxCollider boxCollider;
    public Drug() {
        super();
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/drug.png"));
        this.boxCollider = new BoxCollider(32,32);

        children.add(boxCollider);
    }

    public void getEat(){
        this.isActive = false;
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
