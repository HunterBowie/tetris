package org.example;

import javax.swing.*;
import java.awt.*;

import static org.example.Constants.*;

/**
 * A representation of a Tetris board
 */
public class Board extends JPanel {
    public int x;
    public int y;
    public Piece piece;

    private final Color[][] blocks = new Color[ROWS][COLUMNS];

    public Board(int x , int y) {
        super();
        this.x = x;
        this.y = y;
        initBoard();
    }
    /** Stamp the held piece onto the board so that it becomes blocks */
    public void stampPiece() {
        // !!!
    }

    private void initBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                blocks[i][j] = Color.BLACK;
            }
        }
    }

    private void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(x, y, BOARD_WIDTH, BOARD_HEIGHT);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                g.setColor(blocks[i][j]);
                g.fillRect(x + (j * (CELL_WIDTH + CELL_MARGIN)),
                        y + (i * (CELL_WIDTH + CELL_MARGIN)),
                        CELL_WIDTH, CELL_WIDTH);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
        if (piece != null) {
            piece.render(g);
        }

    }

}
