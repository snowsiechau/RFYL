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
public class Banana extends GameObject implements Physicbody {
    public BoxCollider boxCollider;
    Vector2D velocity;
    public Banana(Vector2D velocity) {
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/banana.png"));
        this.boxCollider = new BoxCollider(5,5);
        children.add(boxCollider);
        this.velocity = velocity;
    }

    public Banana(){
        this(new Vector2D(0,0));
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.position.y < 680)
        this.position.addUp(velocity);
    }

    public void getEat(){
        this.isActive = false;
    }

    @Override
    public BoxCollider getBoxCollier() {
        return boxCollider;
    }
}
