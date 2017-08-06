package game.items;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.Physicbody;
import game.bases.renderer.ImageRenderer;

/**
 * Created by Nttung PC on 8/4/2017.
 */
public class Drug extends GameObject implements Physicbody{
    BoxCollider boxCollider;

    public Drug() {
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/drug.png"));

        boxCollider = new BoxCollider(40,40);

        children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);


    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
