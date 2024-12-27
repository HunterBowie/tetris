package org.example;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.example.Constants.BOARD_X;
import static org.example.Constants.BOARD_Y;

public class Game extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    Board board;
    int score;
    int level;
    int lines;
    Piece heldPiece;
    Piece[] nextPieces;
    Boolean running;

    public Game() {
        super();

        board = new Board(BOARD_X, BOARD_Y);
        this.add(board);

        score = 0;
        level = 0;
        lines = 0;
        heldPiece = null;
        nextPieces = new Piece[3];
        Piece piece1 = Piece.random(), piece2 = Piece.random(), piece3 = Piece.random();
        nextPieces[0] = piece1;
        nextPieces[1] = piece2;
        nextPieces[2] = piece3;
        this.add(nextPieces[0]);
        this.add(nextPieces[1]);
        this.add(nextPieces[2]);
    }

    public void start() {
        running = true;
        // Add a WindowListener to stop the loop when the window is closed
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                running = false;
            }
        });

        while (running) {
            tick();
        }
    }

    private void tick() {
        // update the board and check for inputs
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
