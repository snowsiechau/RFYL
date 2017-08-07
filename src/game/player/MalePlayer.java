package game.player;

import game.bases.FrameCounter;
import game.bases.Vector2D;
import game.bases.physics.Physics;
import game.items.Banana;
import game.items.Condom;
import inputs.InputManager;
import tklibs.Mathx;

/**
 * Created by Nttung PC on 8/3/2017.
 */
public class MalePlayer extends Player implements PlayerMove{

    Vector2D velocity;
    public int condom = 10;
    FrameCounter slowPoopBullet;
    FrameCounter cooldownBanana;
    public static int v;
    boolean bananaStand;

    public MalePlayer() {
        super();
        this.v = 10;
        velocity = new Vector2D();
        slowPoopBullet = new FrameCounter(5);
        cooldownBanana = new FrameCounter(30);
    }

    @Override
    public void move(Player player) {
        Vector2D position = player.position;
        velocity.set(0,0);
        if (InputManager.instance.rightPressed) {
            velocity.x=v;
        }

        if (InputManager.instance.leftPressed) {
           velocity.x=-v;
        }

        if (InputManager.instance.upPressed){
            velocity.y=-v;
        }

        if (InputManager.instance.downPressed){
           velocity.y=v;
        }
        position.addUp(velocity);
        position.x = Mathx.clamp(position.x, 0,6000);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        eatCondom();
        eatPoopBullet();
        eatBanana();

    }

    private void eatBanana() {
        Banana eatBanana = Physics.bodyinRed(this.boxCollider, Banana.class);
        if (eatBanana != null && eatBanana.isActive){
            eatBanana.getEat();
            bananaStand = true;
        }

        if (bananaStand){
            this.v = 0;
            if (cooldownBanana.run()){
                cooldownBanana.reset();
                this.v = 10;
                bananaStand = false;
            }
        }
    }

    private void eatPoopBullet() {
        PlayerPoopBullet eatPoopBullet = Physics.bodyinRed(this.boxCollider, PlayerPoopBullet.class);
        if (eatPoopBullet != null && eatPoopBullet.isActive){
            eatPoopBullet.getEat();
            this.v = 5;
        }

        if (v == 5){
            if (slowPoopBullet.run()){
                slowPoopBullet.reset();
                v = 10;
            }
        }
    }

    private void eatCondom() {
        Condom eatCondom = Physics.bodyinRed(this.boxCollider,Condom.class);
        if (eatCondom != null && eatCondom.isActive){
            condom--;
            eatCondom.getEat();
        }
    }
}
