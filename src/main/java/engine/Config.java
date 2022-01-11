package engine;

import java.util.concurrent.TimeUnit;

class Config {

    private Config() {
    }

    public static final TimeUnit DELAY_TIME_UNIT = TimeUnit.MILLISECONDS;
    public static final Integer DELAY = 100;

    public static final int VERTICAL_CELLS_SIZE = 40;
    public static final int HORIZONTAL_CELLS_SIZE = 60;


}
