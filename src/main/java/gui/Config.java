package gui;

import engine.State;

import java.awt.*;

class Config {
    static final long REFRESH_TIME = 10;
    static final int BOX_HEIGHT = 10;
    static final int BOX_WIDTH = 10;
    static final int PADDING = 0;

    static Color getColor(State state) {
        return state == State.NONE ? Color.BLACK : Color.WHITE;
    }
}
