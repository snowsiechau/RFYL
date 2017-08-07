package game.bird;

import game.actions.SequenceAction;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;

/**
 * Created by SNOW on 8/7/2017.
 */
public class BirdSpawner extends GameObject{
    FrameCounter frameCounter;

    public BirdSpawner() {
        frameCounter = new FrameCounter(100);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (frameCounter.run()){
            frameCounter.reset();
            Bird bird = new Bird();
            GameObject.add(bird);
        }
    }
}
