package gui;

import engine.Cell;
import engine.State;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Box extends JPanel {
    public Box(int x, int y, Cell cell) {
        super();
        setBounds(Config.PADDING + x * Config.BOX_HEIGHT, Config.PADDING + y * Config.BOX_WIDTH, Config.BOX_HEIGHT, Config.BOX_WIDTH);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cell.turn();
            }
        });
    }

    public void changeColor(State state) {
        setBackground(Config.getColor(state));
    }
}
