package game.actions;

import game.bases.FrameCounter;
import game.bases.GameObject;

import java.io.FileReader;

/**
 * Created by SNOW on 8/7/2017.
 */
public class WaitAction implements Action {

    private FrameCounter frameCounter;

    public WaitAction(int countMax) {
        frameCounter = new FrameCounter(countMax);
    }

    @Override
    public boolean run(GameObject gameObject) {
        return frameCounter.run();
    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
