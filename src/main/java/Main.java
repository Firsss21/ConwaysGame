import engine.ConwayEngine;
import gui.Window;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ConwayEngine engine = new ConwayEngine();
        Window window = new Window(engine.getCells());
    }
}
