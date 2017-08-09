package game.items;

import game.Utils;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.Physicbody;
import game.bases.physics.Physics;
import game.bases.renderer.ImageRenderer;
import game.cameras.Camera;

import java.awt.*;

/**
 * Created by Nttung PC on 8/4/2017.
 */
public class Lava extends GameObject implements Physicbody {

    private BoxCollider boxCollider;
    public Lava() {
        super();
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/lava.png"));
        this.boxCollider = new BoxCollider(32, 32);
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        Banana banana = Physics.bodyInRect(this.boxCollider, Banana.class);
        Condom condom = Physics.bodyInRect(this.boxCollider, Condom.class);
        Drug drug = Physics.bodyInRect(this.boxCollider, Drug.class);
        Heart heart = Physics.bodyInRect(this.boxCollider, Heart.class);
        Poop poop = Physics.bodyInRect(this.boxCollider, Poop.class);

        if (banana != null) banana.velocity.set(0,0);
        if (condom != null) condom.velocity.set(0,0);
        if (drug != null) drug.velocity.set(0,0);
        if (heart != null) heart.velocity.set(0,0);
        if (poop != null) poop.velocity.set(0,0);
    }

    @Override
    public BoxCollider getBoxCollier() {
        return this.boxCollider;
    }
}
