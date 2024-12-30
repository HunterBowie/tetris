package com.hunterbowie.ui;

import com.hunterbowie.core.Piece;

import javax.swing.*;
import java.awt.*;

import static com.hunterbowie.util.Constants.*;

public class NextPiecesBox extends JPanel {
    private Piece piece1;
    private Piece piece2;
    private Piece piece3;

    public NextPiecesBox(int x, int y) {
        super();
        setBounds(x, y, NEXT_BOX_WIDTH, NEXT_BOX_HEIGHT);
        setOpaque(false);
        setLayout(null);
        piece1 = Piece.random();
        piece2 = Piece.random();
        piece3 = Piece.random();
        piece1.placeOutsideBoard(50, 20);
        piece2.placeOutsideBoard(50, 120);
        piece3.placeOutsideBoard(50, 220);
        this.add(piece1);
        this.add(piece2);
        this.add(piece3);
    }

    public Piece shiftOutPiece() {
        var outPiece = piece1.clone();
        remove(piece1);
        piece1 = piece2;
        piece2 = piece3;
        piece3 = Piece.random();
        add(piece3);
        piece1.placeOutsideBoard(50, 20);
        piece2.placeOutsideBoard(50, 130);
        piece3.placeOutsideBoard(50, 240);
        return outPiece;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

    }



}
