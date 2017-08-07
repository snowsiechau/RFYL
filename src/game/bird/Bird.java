package game.bird;

import com.sun.xml.internal.ws.server.provider.AsyncProviderInvokerTube;
import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderer.ImageRenderer;
import game.items.Banana;
import game.items.Condom;
import game.items.Heart;
import game.items.Poop;

/**
 * Created by SNOW on 8/7/2017.
 */
public class Bird extends GameObject{
    FrameCounter frameCounter;

    public Bird() {
        this.renderer = new ImageRenderer(Utils.loadImage("assets/images/items/drug.png"));
        this.position.set(0, 200);
        frameCounter = new FrameCounter(20);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(10,0);

        if (frameCounter.run()){
            frameCounter.reset();
            int a = (int) (Math.random() * 4);
            System.out.println(a);
            switch (a){
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
                    Heart heart = new Heart(new Vector2D(0, 10));
                    heart.position.set(this.position);
                    GameObject.add(heart);
                    break;

                case 3:
                    Poop poop = new Poop(new Vector2D(0,10));
                    poop.position.set(this.position);
                    GameObject.add(poop);
                    break;
            }
        }

    }
}
