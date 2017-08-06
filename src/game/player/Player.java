package game.player;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.BoxCollider;
import game.bases.physics.Physicbody;
import game.bases.physics.Physics;
import game.bases.renderer.ImageRenderer;
import game.items.Heart;

import javax.swing.*;

/**
 * Created by Nttung PC on 8/3/2017.
 */
public class Player extends GameObject implements Physicbody{
    PlayerMove playerMove;
    BoxCollider boxCollider;
    FrameCounter frameCounter = new FrameCounter(5);


    public Player() {
        super();
        frameCounter = new FrameCounter(50);
        boxCollider = new BoxCollider(40,40);
        children.add(boxCollider);
    }

    public static Player createMalePlayer() {
        Player player = new Player();
        player.renderer = new ImageRenderer(Utils.loadImage("assets/images/playerboy/shape229.png"));
        return player;
    }

    public void setPlayerMove(PlayerMove playerMove) {
        this.playerMove = playerMove;
    }

    public static Player createFemalePlayer() {
        Player player = new Player();
        player.renderer = new ImageRenderer(Utils.loadImage("assets/images/playerboy/shape235.png"));
        return player;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (playerMove != null)
            playerMove.move(this);

        Heart eatHeart = Physics.bodyinRed(this.boxCollider, Heart.class);

        if (eatHeart != null){
            FemaleMove.velocity = 5;
            eatHeart.getEat();
        }

        if (FemaleMove.velocity == 5){
            if (frameCounter.run()){
                frameCounter.reset();
                FemaleMove.velocity = 10;
            }
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
