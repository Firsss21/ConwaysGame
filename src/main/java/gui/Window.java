package gui;

import engine.Cell;

import javax.swing.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Window {

    private final JFrame frame;
    private final Box[][] boxes;

    public Window(Cell[][] cells) {

        frame = new JFrame();
        boxes = new Box[cells.length][cells[0].length];
        initBoxes(cells);

        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Conway's game");
        frame.setSize(Config.PADDING * 2 + cells.length * Config.BOX_WIDTH, Config.PADDING * 2 + cells[0].length * Config.BOX_HEIGHT);

        initScheduler(cells);

    }

    private void initScheduler(Cell[][] cells) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(() -> {
            for (int x = 0; x < boxes.length; x++)
                for (int y = 0; y < boxes[0].length; y++) {
                    boxes[x][y].changeColor(cells[x][y].getState());
                }
        },Config.REFRESH_TIME, Config.REFRESH_TIME,TimeUnit.MILLISECONDS);
    }

    private void initBoxes(Cell[][] cells) {
        int width = cells.length;
        int height = cells[0].length;
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                boxes[x][y] = new Box(x, y, cells[x][y]);
                frame.add(boxes[x][y]);
            }

    }
}
