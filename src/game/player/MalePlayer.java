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
    FrameCounter cooldownBanana;
    FrameCounter slowPoopBullet;
    static int v;

    public MalePlayer() {
        super();
        this.v = 10;
        velocity = new Vector2D();
        slowPoopBullet = new FrameCounter(50);
        cooldownBanana = new FrameCounter(30);
    }

    @Override
    public void move(Player player) {
        Vector2D position = player.position;
        velocity.set(0,0);
        if (InputManager.instance.rightPressed) {
            velocity.x = v;
        }

        if (InputManager.instance.leftPressed) {
           velocity.x = -v;
        }

        if (InputManager.instance.upPressed){
            velocity.y = -v;
        }

        if (InputManager.instance.downPressed){
           velocity.y = v;
        }
        position.addUp(velocity);
        position.x = Mathx.clamp(position.x, 0,6000);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        eatCondom();
        eatPoopBullet();
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
