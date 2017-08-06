package game.items;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.Physicbody;
import game.bases.renderer.ImageRenderer;

import javax.swing.*;

/**
 * Created by Nttung PC on 8/4/2017.
 */
public class Heart extends GameObject implements Physicbody{
    BoxCollider boxCollider;
    FrameCounter frameCounter;

    public Heart() {
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/heart.png"));
        frameCounter = new FrameCounter(20);
        boxCollider = new BoxCollider(40,40);
        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
    }

    public void getEat() {
        this.isActive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
