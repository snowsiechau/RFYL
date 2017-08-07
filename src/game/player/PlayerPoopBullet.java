package game.player;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.Physicbody;
import game.bases.renderer.ImageRenderer;

/**
 * Created by SNOW on 8/6/2017.
 */
public class PlayerPoopBullet extends GameObject implements Physicbody{
    BoxCollider boxCollider;
    Vector2D velocity;

    public PlayerPoopBullet(Vector2D velocity) {
        super();
        this.velocity = velocity;
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/poop.png"));
        this.boxCollider = new BoxCollider(32,32);
        children.add(boxCollider);
    }

    public void getEat(){
        this.isActive = false;
    }


    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
