package game.bird;

import com.sun.corba.se.impl.protocol.giopmsgheaders.FragmentMessage;
import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderer.Animation;
import game.bases.renderer.ImageRenderer;
import game.items.*;
import org.omg.CORBA.MARSHAL;

/**
 * Created by SNOW on 8/9/2017.
 */
public class Bird extends GameObject{
    FrameCounter frameCounter;

    public Bird() {
        this.renderer = new Animation(
                Utils.loadImage("assets/images/bird/0.png"),
                Utils.loadImage("assets/images/bird/1.png"),
                Utils.loadImage("assets/images/bird/2.png"),
                Utils.loadImage("assets/images/bird/3.png"),
                Utils.loadImage("assets/images/bird/4.png"),
                Utils.loadImage("assets/images/bird/5.png"),
                Utils.loadImage("assets/images/bird/6.png"),
                Utils.loadImage("assets/images/bird/7.png"),
                Utils.loadImage("assets/images/bird/8.png"),
                Utils.loadImage("assets/images/bird/9.png"),
                Utils.loadImage("assets/images/bird/10.png"),
                Utils.loadImage("assets/images/bird/11.png"),
                Utils.loadImage("assets/images/bird/12.png")
        );
        this.position.set(0, 50);

        frameCounter = new FrameCounter(100);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(5,0);
        int r = (int) (Math.random() * 4);

        if (frameCounter.run()) {
            frameCounter.reset();
            switch (r) {
                case 0:
                    Banana banana = new Banana(new Vector2D(0, 10));
                    banana.position.set(this.position);
                    GameObject.add(banana);
                    break;
                case 1:
                    Condom condom = new Condom(new Vector2D(0, 10));
                    condom.position.set(this.position);
                    GameObject.add(condom);
                    break;
                case 2:
                    Drug drug = new Drug(new Vector2D(0, 10));
                    drug.position.set(this.position);
                    GameObject.add(drug);
                    break;
                case 3:
                    Heart heart = new Heart(new Vector2D(0, 10));
                    heart.position.set(this.position);
                    GameObject.add(heart);
                    break;
                case 4:
                    Poop poop = new Poop(new Vector2D(0, 10));
                    poop.position.set(this.position);
                    GameObject.add(poop);
                    break;

                default:
                    break;
            }
        }
    }
}
