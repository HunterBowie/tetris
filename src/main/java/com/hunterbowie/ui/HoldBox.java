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
        setLayout(null);
    }

    public void setPiece(Piece newPiece) {
        if (hasPiece()) {
            removePiece();
        }
        this.piece = newPiece;
        add(newPiece);
        revalidate();
        repaint();
    }

    private void removePiece() {
        remove(piece);
        piece = null;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

    }

    public void newUnplacedPiece(Piece newPiece) {
        newPiece.placeOutsideBoard(60, 30);
        setPiece(newPiece);
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
