package game.bases;

import tklibs.Mathx;

/**
 * Created by Nttung PC on 8/1/2017.
 */
public class Contraints {
    float top;
    float bottom;
    float left;
    float right;

    public Contraints(float top, float bottom, float left, float right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public void make(Vector2D position){
        position.x = Mathx.clamp(position.x,left,right);
        position.y = Mathx.clamp(position.y,top,bottom);
    }
}
