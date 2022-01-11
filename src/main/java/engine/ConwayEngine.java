package engine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class ConwayEngine {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ScheduledFuture scheduledFuture;

    private final Cell[][] cells = new Cell[Config.HORIZONTAL_CELLS_SIZE][Config.VERTICAL_CELLS_SIZE];

    public ConwayEngine() {
        initEmptyCells();
        initCellsNear();
        this.scheduledFuture = scheduler.scheduleAtFixedRate(moveCells, Config.DELAY * 5, Config.DELAY, Config.DELAY_TIME_UNIT);
    }

    private final void initEmptyCells() {
        for (int i = 0; i < Config.HORIZONTAL_CELLS_SIZE; i++)
            for (int y = 0; y < Config.VERTICAL_CELLS_SIZE; y++)
                this.cells[i][y] = new Cell();
    }

    private final void initCellsNear() {
        for (int x = 0; x < Config.HORIZONTAL_CELLS_SIZE; x++)
            for (int y = 0; y < Config.VERTICAL_CELLS_SIZE; y++)
                for (int sx = -1; sx <= 1; sx++)
                    for (int sy = -1; sy <= 1; sy++)
                        if (!(sx == 0 && sy == 0))
                            this.cells[x][y].addNear(
                                    this.cells
                                            [(x + sx + Config.HORIZONTAL_CELLS_SIZE) % Config.HORIZONTAL_CELLS_SIZE]
                                            [(y + sy + Config.VERTICAL_CELLS_SIZE) % Config.VERTICAL_CELLS_SIZE]
                            );
    }

    private final Runnable moveCells = () -> {

        State[][] states = new State[Config.HORIZONTAL_CELLS_SIZE][Config.VERTICAL_CELLS_SIZE];

        for (int i = 0; i < Config.HORIZONTAL_CELLS_SIZE; i++)
            for (int y = 0; y < Config.VERTICAL_CELLS_SIZE; y++)
                states[i][y] = this.cells[i][y].move();

        for (int i = 0; i < Config.HORIZONTAL_CELLS_SIZE; i++)
            for (int y = 0; y < Config.VERTICAL_CELLS_SIZE; y++)
                this.cells[i][y].setState(states[i][y]);
    };

    public Cell[][] getCells() {
        return cells;
    }
}
