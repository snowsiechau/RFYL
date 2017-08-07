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
import org.omg.CORBA.MARSHAL;
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
    public static int vFemale;

    public FemalePlayer() {
        super();
        velocity = new Vector2D();
        this.cooldownBullet = new FrameCounter(30);
        cooldownBanana = new FrameCounter(30);
        this.vFemale = 10;
    }

    @Override
    public void move(Player player) {
        this.velocity.set(0,0);
        Vector2D position = player.position;
        if (InputManager.instance.dPressed) {
            this.velocity.x = vFemale;
        }

        if (InputManager.instance.aPressed) {
            this.velocity.x = -vFemale;
        }

        if (InputManager.instance.wPressed){
            this.velocity.y = -vFemale;
        }

        if (InputManager.instance.sPressed){
           this.velocity.y = vFemale;
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
            vFemale = 0;
        }
        if (vFemale == 0){
            if (cooldownBanana.run()){
                cooldownBanana.reset();
                vFemale = 10;
            }
        }
    }

    private void castBullet() {
        if (!bulletDisable && bullet > 0){
            if (InputManager.instance.bPressed){
                Vector2D velocityPoop;
                Vector2D positionPoop;
                if (this.position.x > MalePlayer.instanceMale.position.x){
                    velocityPoop = new Vector2D(-20, 0);
                    positionPoop = new Vector2D(this.position.x - 10, this.position.y - 20);
                }else {
                    velocityPoop = new Vector2D(20,0);
                    positionPoop = new Vector2D(this.position.x + 10, this.position.y - 20);
                }
                PlayerPoopBullet playerPoopBullet = new PlayerPoopBullet(velocityPoop);
                playerPoopBullet.position.set(positionPoop);
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
