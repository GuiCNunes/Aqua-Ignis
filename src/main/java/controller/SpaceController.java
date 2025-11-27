package controller;

import com.googlecode.lanterna.input.KeyStroke;
import model.Space;

public class SpaceController {
    private Space space;

    public SpaceController(Space space) {
        this.space = space;
    }

    public void processKey(KeyStroke key){
        space.processKey(key);
    }
}