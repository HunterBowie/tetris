package com.hunterbowie.ui;

import javax.swing.*;
import java.awt.*;

import static com.hunterbowie.util.Constants.*;

public class StatsBox extends JPanel {
    // YAY SHARED MUTABLE STATE
    public int score = 0;
    public int lines = 0;
    public int level = 1;

    public StatsBox(int x, int y) {
        super();
        setBounds(x, y, STATS_BOX_WIDTH, STATS_BOX_HEIGHT);
        setOpaque(false);
        setLayout(null);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 50, 50);
        g.drawString("Lines: " + lines, 50, 100);
        g.drawString("Level: " + level, 50, 150);

    }

}
