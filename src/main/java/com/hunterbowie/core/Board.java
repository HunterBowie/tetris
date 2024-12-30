package com.hunterbowie.core;

import com.hunterbowie.util.RotateDirection;
import com.hunterbowie.util.ShiftDirection;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static com.hunterbowie.util.Constants.*;

/**
 * A representation of a Tetris board
 */
public class Board extends JPanel {
    public Piece piece;
    public Color[][] blocks = new Color[ROWS+GHOST_ROWS][COLUMNS];

    public Board(int x , int y) {
        super();
        setOpaque(false);
        setLayout(null);
        setBounds(x, y-GHOST_BOARD_HEIGHT, BOARD_WIDTH, BOARD_HEIGHT+GHOST_BOARD_HEIGHT);
        newRandomPiece();
        resetBoard();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ghost rows are not rendered
        for (int r = GHOST_ROWS; r < (ROWS + GHOST_ROWS); r++) {
            for (int c = 0; c < COLUMNS; c++) {
                g.setColor(blocks[r][c]);
                g.fillRect((c * (CELL_WIDTH + CELL_MARGIN)),
                        (r * (CELL_WIDTH + CELL_MARGIN)),
                        CELL_WIDTH, CELL_WIDTH);
            }
        }
    }

    /**
     * Sets all the boards blocks to Black
     */
    public void resetBoard() {
        for (int i = 0; i < ROWS+GHOST_ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                blocks[i][j] = Color.BLACK;
            }
        }
    }

    /**
     * Checks whether the board's piece is legal
     * - checks out of bounds
     * - checks collision with filled blocks (not BLACK)
     */
    public Boolean isPieceLegal() {
        for (int row = 0; row < PIECE_STRUCT_ROWS; row++) {
            for (int col = 0; col < PIECE_STRUCT_COLS; col++) {
                if (piece.getCurrentShape()[row][col]) {
                    int block_row = piece.row + row;
                    int block_col = piece.col + col;
                    if (block_row < 0 || block_row >= ROWS+GHOST_ROWS) return false;
                    if (block_col < 0 || block_col >= COLUMNS) return false;
                    if (blocks[block_row][block_col] != Color.BLACK) return false;
                }

            }
        }
        return true;

    }

    /**
     * Move the board's piece down. Must be legal
     */
    public void pieceDown() {
        if (!isPieceGrounded()) {
            piece.moveDown();
            // must repaint because piece method is not called
            repaint();
        }
    }

    /**
     * Clones the board
     */
    @Override
    public Board clone() {
        Board board = new Board(this.getX(), this.getY());
        board.blocks = blocks;
        board.piece = piece;
        return board;
    }

    /**
     * Shifts board's piece to left/right. Must be legal
     */
    public void pieceShift(ShiftDirection direction) {
        Piece testPiece = piece.clone();
        Board board = clone();
        testPiece.shift(direction);
        board.piece = testPiece;
        if (board.isPieceLegal()) {
            piece.shift(direction);
        }
    }

    /**
     * Rotate the board's piece to up/down. Must be legal
     */
    public void pieceRotate(RotateDirection direction) {
        Piece testPiece = piece.clone();
        Board board = clone();
        testPiece.rotate(direction);
        board.piece = testPiece;
        if (board.isPieceLegal()) {
            piece.rotate(direction);
        }
    }

    /**
     * Checks whether the board's piece is grounded (as far down as possible)
     */
    public boolean isPieceGrounded() {
        Piece testPiece = piece.clone();
        testPiece.moveDown();
        Board testBoard = makeCustom(this.getX(), this.getY(), testPiece, blocks);
        // will also return true if current piece is illegal
        return !testBoard.isPieceLegal();
    }

    /**
     * Stamps the board's piece onto the board and resets piece
     */
    public void stampPiece() {
        piece.stamp(blocks);
        remove(piece);
        piece = null;
    }

    /**
     * Checks whether the board's piece is in ghost rows
     */
    public boolean isPieceAboveMax() {
        for (int row = 0; row < PIECE_STRUCT_ROWS; row++) {
            for (int col = 0; col < PIECE_STRUCT_COLS; col++) {
                if (piece.getCurrentShape()[row][col]) {
                    int block_row = piece.row + row;
                    if (block_row < GHOST_ROWS) return true;
                }
            }
        }
        return false;
    }

    /**
     * Initializes a new random piece placed in the ghost rows
     */
    public void newRandomPiece() {
        piece = Piece.random();
        Random r = new Random();
        int piece_row = 0;

        int pieceShapeLeft = piece.farthestLeft();
        int col_left_bound = -pieceShapeLeft;

        int pieceShapeRight = piece.farthestRight();
        int col_right_bound = COLUMNS - (pieceShapeRight + 1);
        int piece_col = r.nextInt(col_left_bound, col_right_bound);

        piece.placeInsideBoard(piece_row, piece_col);
        add(piece);
        revalidate();
    }

    /**
     * Brings the board's piece to the lowest it is allowed to move
     */
    public void groundPiece() {
        while (!isPieceGrounded()) {
            pieceDown();
        }
    }

    /**
     * Make a custom board with given piece
     */
    public static Board makeCustom(int x, int y, Piece piece, Color[][] blocks) {
        Board board = new Board(x, y);
        board.piece = piece;
        board.blocks = blocks;
        return board;
    }

    /**
     * Make a custom board with given piece
     */
    public static Board makeCustom(int x, int y, Piece piece) {
        Board board = new Board(x, y);
        board.piece = piece;
        return board;
    }

    /**
     * Checks whether the given row has no Black
     */
    public Boolean isFilledRow(int row) {
        for (int col = 0; col < COLUMNS; col++) {
            if (blocks[row][col] == Color.BLACK) return false;
        }
        return true;
    }

    /**
     * Counts the number of filled rows (no black)
     */
    public int numFilledRows() {
        int count = 0;
        for (int row = 0; row < ROWS + GHOST_ROWS; row++) {
            if (isFilledRow(row)) count++;
        }
        return count;
    }

    /**
     * Shifts the board by removing given row
     */
    private void shiftFilledRow(int row) {
        Color[][] oldBlocks = blocks.clone();
        Arrays.fill(blocks[0], Color.BLACK);
        for (int r = 1; r <= row; r++) {
            blocks[r] = oldBlocks[r - 1].clone();
        }
    }

    /**
     * Shifts the entire board down by given shifts
     */
    public void shiftFilledRows(int filledRows) {
        for (int i = 0; i < filledRows; i++) {
            for (int row = 0; row < ROWS + GHOST_ROWS; row++) {
                if (isFilledRow(row)) {
                    shiftFilledRow(row);
                    break;
                }
            }
        }
    }

    public Piece getPiece() {
    return piece;
    }

    public void setPiece(Piece piece) {
        remove(this.piece);
        this.piece = piece;
        add(piece);
        revalidate();
    }
}
