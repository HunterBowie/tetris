package com.hunterbowie.ui;

import com.hunterbowie.core.Piece;

import javax.swing.*;
import java.awt.*;

import static com.hunterbowie.util.Constants.*;

public class HoldBox extends JPanel {
    private Piece piece;

    public HoldBox(int x, int y) {
        super();
        setBounds(x, y, HOLD_BOX_WIDTH, HOLD_BOX_HEIGHT);
        setOpaque(false);
    }

    public Piece popPiece() {
        var oldPiece = piece;
        piece = null;
        remove(oldPiece);
        return oldPiece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.piece.placeOutsideBoard(0, 0);
        add(piece);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.CYAN);
//        g.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Checks whether piece != null
     */
    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }
}
