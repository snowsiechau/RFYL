package game.bird;

import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.items.Banana;

/**
 * Created by SNOW on 8/9/2017.
 */
public class BirdSpawner extends GameObject{
    FrameCounter frameCounter;

    public BirdSpawner() {
        this.frameCounter = new FrameCounter(300);
        }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (frameCounter.run()) {
            frameCounter.reset();
            Bird bird = new Bird();
            GameObject.add(bird);
        }
    }
}
