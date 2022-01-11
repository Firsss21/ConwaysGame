package engine;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private State state;
    private final List<Cell> nearCells;

    public State getState() {
        return state;
    }

    public void changeState() {
        this.state = this.state == State.ALIVE ? State.NONE : State.ALIVE;
    }

    public Cell() {
        this.state = State.NONE;
        this.nearCells = new ArrayList<>();
    }

    public boolean addNear(Cell cell) {
        return this.nearCells.add(cell);
    }

    private int countNearAliveCells() {
        return (int) this.nearCells.stream().filter(e -> e.state == State.ALIVE).count();
    }

    State move() {
        int nearAliveCells = countNearAliveCells();

        switch (state) {
            case NONE:
                return nearAliveCells == 3 ? State.ALIVE : State.NONE;
            case ALIVE:
                return nearAliveCells >= 4 || nearAliveCells <= 1 ? State.NONE : State.ALIVE;
            default:
                return State.NONE;
        }
    }

    public void turn() {
        for (Cell nearCell : this.nearCells) {
            nearCell.changeState();
        }
    }

    public void setState(State state) {
        this.state = state;
    }
}
