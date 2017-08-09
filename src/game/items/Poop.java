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
public class Poop extends GameObject implements Physicbody {
    BoxCollider boxCollider;
    Vector2D velocity;
    public Poop(Vector2D velocity) {
        super();
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/poop.png"));
        this.boxCollider = new BoxCollider(32,32);
        children.add(boxCollider);
        this.velocity = velocity;
    }

    public Poop(){
        this(new Vector2D(0,0));
    }


    public void getEat(){
        this.isActive = false;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.position.y < 680)
        this.position.addUp(velocity);
    }

    @Override
    public BoxCollider getBoxCollier() {
        return boxCollider;
    }
}
