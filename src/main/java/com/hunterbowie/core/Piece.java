package com.hunterbowie.core;

import com.hunterbowie.util.PieceColor;
import com.hunterbowie.util.PieceShape;
import com.hunterbowie.util.RotateDirection;
import com.hunterbowie.util.ShiftDirection;

import javax.swing.*;
import java.awt.*;

import static com.hunterbowie.util.Constants.*;
import static com.hunterbowie.util.Constants.CELL_WIDTH;

/**
 * A Tetris piece
 */
public class Piece extends JPanel {
    public PieceShape pieceShape;
    public PieceColor pieceColor;
    public int rotation;
    public Integer x;
    public Integer y;
    public Integer row;
    public Integer col;
    public Boolean isInsideBoard;

    public Piece(PieceShape pieceShape, PieceColor pieceColor) {
        this.pieceShape = pieceShape;
        this.pieceColor = pieceColor;
        this.rotation = 0;
        this.setOpaque(false);
    }

    /**
     * Place the piece inside the board using row, col
     */
    public void placeInsideBoard(int row, int col) {
        isInsideBoard = true;
        this.row = row;
        this.col = col;
        this.x = null;
        this.y = null;
        updateBonds();
    }

    /**
     * Place the piece outside the board using x, y
     */
    public void placeOutsideBoard(int x, int y) {
        isInsideBoard = false;
        this.x = x;
        this.y = y;
        row = null;
        col = null;
        updateBonds();
    }

    private void updateBonds() {
        if (isInsideBoard) {
            this.setBounds(col * (CELL_WIDTH + CELL_MARGIN),
                    row * (CELL_WIDTH + CELL_MARGIN),
                    PIECE_STRUCT_COLS * (CELL_WIDTH + CELL_MARGIN),
                    PIECE_STRUCT_ROWS * (CELL_WIDTH + CELL_MARGIN));
        }
        else {
            this.setBounds(x, y,
                    PIECE_STRUCT_COLS * (CELL_WIDTH + CELL_MARGIN),
                    PIECE_STRUCT_ROWS * (CELL_WIDTH + CELL_MARGIN));
        }
    }

    /**
     * Get the current shape (with correct rotation)
     */
    public Boolean[][] getCurrentShape() {
        return pieceShape.structure.get(rotation);
    }

    public void moveDown() {
        row++;
        updateBonds();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(pieceColor.color);
        for (int row = 0; row < PIECE_STRUCT_ROWS; row++) {
            for (int col = 0; col < PIECE_STRUCT_COLS; col++) {
                if (getCurrentShape()[row][col]) {
                    if (isInsideBoard) {
                        if (this.row + row >= GHOST_ROWS){
                            g.fillRect(
                                    (col * (CELL_WIDTH + CELL_MARGIN)),
                                    (row * (CELL_WIDTH + CELL_MARGIN)),
                                    CELL_WIDTH, CELL_WIDTH);
                        }

                    }
                    else {
                        g.fillRect(
                                (col * (CELL_WIDTH + CELL_MARGIN)),
                                (row * (CELL_WIDTH + CELL_MARGIN)),
                                CELL_WIDTH, CELL_WIDTH);
                    }
                }

            }
        }
    }

    /**
     * Shift the piece by moving it left/right
     */
    public void shift(ShiftDirection direction) {
        if (isInsideBoard) {
            if (direction == ShiftDirection.LEFT) {
                col--;
            }
            else if (direction == ShiftDirection.RIGHT) {
                col++;
            }
        }
        updateBonds();
        repaint();
    }

    /**
     * Rotate the piece by twisting it clockwise/anticlockwise
     */
    public void rotate(RotateDirection direction) {
        if (direction == RotateDirection.CLOCKWISE) {
            rotation++;
            if (rotation >= ROTATIONS) {
                rotation = 0;
            }
        }
        else if (direction == RotateDirection.ANTICLOCKWISE) {
            rotation--;
            if (rotation < 0) {
                rotation = ROTATIONS - 1;
            }
        }
        repaint();
    }

    /**
     * Make a clone of the piece
     */
    @Override
    public Piece clone() {
        Piece piece = new Piece(pieceShape, pieceColor);
        piece.rotation = rotation;
        if (isInsideBoard) {
            piece.placeInsideBoard(row, col);
        }
        else {
            piece.placeOutsideBoard(x, y);
        }
        return piece;
    }

    /**
     * Get a piece with a random shape and color
     */
    public static Piece random() {
        var shape = PieceShape.random();
        PieceColor color = switch (shape) {
            case PieceShape.L_PIECE -> PieceColor.ORANGE;
            case PieceShape.I_PIECE -> PieceColor.CYAN;
            case PieceShape.J_PIECE -> PieceColor.BLUE;
            case PieceShape.O_PIECE -> PieceColor.YELLOW;
            case PieceShape.S_PIECE -> PieceColor.GREEN;
            case PieceShape.T_PIECE -> PieceColor.PINK;
            case PieceShape.Z_PIECE -> PieceColor.RED;
            default ->
                // Code to execute if no case matches
                    throw new IllegalArgumentException("Unsupported piece shape: " + shape);
        };

        return new Piece(shape, color);
    }

    /**
     * Stamp shape onto given blocks
     */
    public void stamp(Color[][] blocks) {
        for (int row = 0; row < PIECE_STRUCT_ROWS; row++) {
            for (int col = 0; col < PIECE_STRUCT_COLS; col++) {
                if (getCurrentShape()[row][col]) {
                    int block_row = row+this.row;
                    int block_col = col+this.col;
                    if (block_row >= ROWS+GHOST_ROWS) continue;
                    if (block_col >= COLUMNS) continue;
                    blocks[block_row][block_col] = pieceColor.color;
                }

            }
        }
    }

    public String toString() {
        if (isInsideBoard) {
            return "Piece (in board) #" + this.row + "," + this.col
                    + ", " + pieceShape.toString();
        }
        else {
            return "Piece (off board) #" + this.x + "," + this.y
                    + ", " + pieceShape.toString();
        }
    }


    public int farthestRight() {
        return pieceShape.farthestRight(rotation);
    }

    public int farthestLeft() {
        return pieceShape.farthestLeft(rotation);
    }
}
