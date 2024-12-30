package com.hunterbowie.util;

public class Constants {
    public static final String WINDOW_TITLE = "Tetris";
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;
    public static final int GHOST_ROWS = 5;
    public static final int CELL_WIDTH = 20;
    public static final int CELL_MARGIN = 2;
    public static final int BOARD_WIDTH = (CELL_WIDTH + CELL_MARGIN) * COLUMNS - CELL_MARGIN;
    public static final int BOARD_HEIGHT = (CELL_WIDTH + CELL_MARGIN) * ROWS - CELL_MARGIN;
    public static final int GHOST_BOARD_HEIGHT = (GHOST_ROWS * (CELL_WIDTH + CELL_MARGIN));
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 550;
    public static final int BOARD_X = SCREEN_WIDTH / 2 - BOARD_WIDTH / 2;
    public static final int BOARD_Y = 50;
    public static final int STATS_BOX_X = 75;
    public static final int STATS_BOX_Y = 250;
    public static final int STATS_BOX_WIDTH = 200;
    public static final int STATS_BOX_HEIGHT = 200;
    public static final int NEXT_BOX_X = 525;
    public static final int NEXT_BOX_Y = 50;
    public static final int NEXT_BOX_WIDTH = 200;
    public static final int NEXT_BOX_HEIGHT = 400;
    public static final int HOLD_BOX_WIDTH = 200;
    public static final int HOLD_BOX_HEIGHT = 150;
    public static final int HOLD_BOX_X = 75;
    public static final int HOLD_BOX_Y = 50;
    public static final int PIECE_STRUCT_ROWS = 5;
    public static final int PIECE_STRUCT_COLS = 5;
    public static final int ROTATIONS = 4;
    public static final Boolean I = Boolean.TRUE;
    public static final Boolean O = Boolean.FALSE;
    public static final double INITIAL_DELAY = .5;
    public static final double DELAY_MULTIPLIER = .80;

}
