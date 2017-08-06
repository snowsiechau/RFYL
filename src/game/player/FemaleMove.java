package game.player;

import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.Physicbody;
import game.bases.physics.Physics;
import game.items.Heart;
import inputs.InputManager;
import tklibs.Mathx;

import javax.swing.*;

/**
 * Created by Nttung PC on 8/3/2017.
 */
public class FemaleMove extends GameObject implements PlayerMove, Physicbody{

    public static int velocity;
    BoxCollider boxCollider;

    public FemaleMove() {
        this.velocity = 10;
        boxCollider = new BoxCollider(40,40);

        children.add(boxCollider);
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public void move(Player player) {
        Vector2D position = player.position;

        if (InputManager.instance.dPressed) {

            position.addUp(velocity, 0);
        }

        if (InputManager.instance.aPressed) {
            position.addUp(-velocity, 0);
        }

        if (InputManager.instance.wPressed){
            position.addUp(0,-velocity);
        }

        if (InputManager.instance.sPressed){
            position.addUp(0,velocity);
        }
        position.x = Mathx.clamp(position.x, 0,3300);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        Heart eatHeart = Physics.bodyinRed(this.boxCollider, Heart.class);

        if (eatHeart != null){
            eatHeart.getEat();
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
