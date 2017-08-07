package game.items;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.Physicbody;
import game.bases.renderer.ImageRenderer;
import game.cameras.Camera;

import javax.xml.bind.util.ValidationEventCollector;
import java.awt.*;

/**
 * Created by Nttung PC on 8/4/2017.
 */
public class Condom extends GameObject implements Physicbody{
    public BoxCollider boxCollider;
    Vector2D velocity;

    public Condom(Vector2D velocity) {
        super();
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/condom.png"));
        boxCollider = new BoxCollider(32,32);
        this.children.add(boxCollider);
        this.velocity = velocity;
    }

    public Condom(){
        this(new Vector2D(0,0));
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
    }

    public void getEat() {
        this.isActive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
