package org.example;

public class Constants {
    public static final String WINDOW_TITLE = "Tetris";
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;
    public static final int CELL_WIDTH = 20;
    public static final int CELL_MARGIN = 2;
    public static final int BOARD_WIDTH = (CELL_WIDTH + CELL_MARGIN) * COLUMNS;
    public static final int BOARD_HEIGHT = (CELL_WIDTH + CELL_MARGIN) * ROWS;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 500;
    public static final int BOARD_X = SCREEN_WIDTH / 2 - BOARD_WIDTH / 2;
    public static final int BOARD_Y = 20;
}
