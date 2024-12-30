package com.hunterbowie.core;

import com.hunterbowie.ui.HoldBox;
import com.hunterbowie.ui.StatsBox;
import com.hunterbowie.util.RotateDirection;
import com.hunterbowie.util.ShiftDirection;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import static com.hunterbowie.util.Constants.*;

public class Game extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    private Board board;
    private Piece[] nextPieces;
    private Timer updateTimer;
    private long lastUpdateTime;
    private StatsBox statsBox;
    private HoldBox holdBox;
    private double dropDelay = INITIAL_DELAY;

    public Game() {
        super();
        setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        setOpaque(false);
        setLayout(null);
        board = new Board(BOARD_X, BOARD_Y);
        add(board);

        statsBox = new StatsBox(STATS_BOX_X, STATS_BOX_Y);
        add(statsBox);

        holdBox = new HoldBox(HOLD_BOX_X, HOLD_BOX_Y);
        add(holdBox);

        initNextPieces();

        // game update loop
        lastUpdateTime = System.nanoTime();
        updateTimer = new Timer(1, e -> update());
        updateTimer.start();
    }

    private void initNextPieces() {
        nextPieces = new Piece[3];
        Piece piece1 = Piece.random();
        Piece piece2 = Piece.random();
        Piece piece3 = Piece.random();
        nextPieces[0] = piece1;
        nextPieces[1] = piece2;
        nextPieces[2] = piece3;
        this.add(nextPieces[0]);
        this.add(nextPieces[1]);
        this.add(nextPieces[2]);
    }

    /**
     * Updates the game
     */
    private void update() {
        long currentTime = System.nanoTime();
        double elapsedTime = (currentTime - lastUpdateTime) / 1_000_000_000.0; // Convert to seconds

        // moving piece down
        if (elapsedTime > dropDelay) {
            System.out.println(board.piece.toString());
            lastUpdateTime = currentTime;
            board.pieceDown();
            updateBoardPiece();
        }
    }

    /**
     * Updates the status of the board's piece
     */
    private void updateBoardPiece() {
        if (board.isPieceGrounded()) {
            if (board.isPieceAboveMax()){
                board.stampPiece();
                over();
            }
            else {
                board.stampPiece();
                int filledRows = board.numFilledRows();
                if (filledRows != 0) {
                    board.shiftFilledRows(filledRows);
                    statsBox.lines += filledRows;
                    switch (filledRows) {
                        case 1:
                            statsBox.score += (40 * (statsBox.level + 1));
                            break;
                        case 2:
                            statsBox.score += (100 * (statsBox.level + 1));
                            break;
                        case 3:
                            statsBox.score += (300 * (statsBox.level + 1));
                            break;
                        default:
                            statsBox.score += (1200 * (statsBox.level + 1));
                            break;
                    }
                    statsBox.level = (int) Math.floor(statsBox.lines / 10.0) + 1;
                    dropDelay = INITIAL_DELAY * Math.pow(DELAY_MULTIPLIER, statsBox.level-1);

                }
                repaint();
                board.newRandomPiece();
            }

        }
    }

    /**
     * Ends the game
     */
    private void over() {
        System.out.println("<------ Game Over -----> ");
        System.exit(15);
    }

    private void newBoardPiece() {

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint the held piece and next pieces
    }

    /**
     * Performs a swap with the board's piece and holdbox's piece
     */
    private void swapWithHold() {
//        if (holdBox.hasPiece()) {
//            var holdPiece = holdBox.getPiece();
//            var boardPiece = board.getPiece();
//            holdBox.setPiece(boardPiece);
//            board.setPiece(holdPiece);
//        }
//        else {
//            holdBox.setPiece(board.getPiece());
//            board.newRandomPiece();
//        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            board.groundPiece();
            updateBoardPiece();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            board.pieceRotate(RotateDirection.CLOCKWISE);
            updateBoardPiece();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            board.pieceRotate(RotateDirection.ANTICLOCKWISE);
            updateBoardPiece();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            board.pieceShift(ShiftDirection.LEFT);
            updateBoardPiece();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            board.pieceShift(ShiftDirection.RIGHT);
            updateBoardPiece();
        }
        else if (e.getKeyCode() == KeyEvent.VK_C) {
            swapWithHold();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // what is BUTTON1?
        if (e.getButton() == MouseEvent.BUTTON1) {
            board.groundPiece();
            updateBoardPiece();
        }
        else if (e.getButton() == MouseEvent.BUTTON2) {
            swapWithHold();
        }

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
