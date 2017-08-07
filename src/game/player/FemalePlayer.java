package game.player;

import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.physics.Physicbody;
import game.bases.physics.Physics;
import game.items.Banana;
import game.items.Heart;
import game.items.Poop;
import inputs.InputManager;
import tklibs.Mathx;

/**
 * Created by Nttung PC on 8/3/2017.
 */
public class FemalePlayer extends Player implements PlayerMove{
    public Vector2D velocity;
    public static int heart = 5;
    public static int bullet;
    boolean bulletDisable;
    FrameCounter cooldownBullet;
    FrameCounter cooldownBanana;
    public static int v;

    public FemalePlayer() {
        super();
        velocity = new Vector2D();
        this.cooldownBullet = new FrameCounter(30);
        cooldownBanana = new FrameCounter(30);
    }

    @Override
    public void move(Player player) {
        this.velocity.set(0,0);
        Vector2D position = player.position;
        if (InputManager.instance.dPressed) {
            this.velocity.x = v;
        }

        if (InputManager.instance.aPressed) {
            this.velocity.x = -v;
        }

        if (InputManager.instance.wPressed){
            this.velocity.y = -v;
        }

        if (InputManager.instance.sPressed){
           this.velocity.y = v;
        }
        position.addUp(velocity);
        position.x = Mathx.clamp(position.x, 0,6000);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        eatHeart();
        eatPoop();
        castBullet();
        stand();
    }

    private void stand() {
        if (eatBanana()){
            v = 0;
        }
        if (v == 0){
            if (cooldownBanana.run()){
                cooldownBanana.reset();
                v = 10;
            }
        }
    }

    private void castBullet() {
        if (!bulletDisable && bullet > 0){
            if (InputManager.instance.bPressed){
                PlayerPoopBullet playerPoopBullet = new PlayerPoopBullet(new Vector2D(-20,0));
                playerPoopBullet.position.set(this.position.x - 10, this.position.y - 20);
                GameObject.add(playerPoopBullet);
                bullet--;
            }
            bulletDisable = true;
        }
        cooldown();
    }

    private void cooldown() {
        if (bulletDisable){
            if (cooldownBullet.run()){
                cooldownBullet.reset();
                bulletDisable = false;
            }
        }
    }

    private void eatPoop() {
        Poop eatPoop = Physics.bodyinRed(this.boxCollider, Poop.class);

        if (eatPoop != null && eatPoop.isActive){
            bullet += 3;
            eatPoop.getEat();
        }
    }

    private void eatHeart() {
        Heart eatHeart = Physics.bodyinRed(this.boxCollider, Heart.class);
        if (eatHeart != null && eatHeart.isActive){
            heart--;
            eatHeart.getEat();
        }
    }
}
