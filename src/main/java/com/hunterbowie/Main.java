package com.hunterbowie;

import com.hunterbowie.core.Game;
import com.hunterbowie.util.PieceColor;

import javax.swing.*;

import static com.hunterbowie.util.Constants.*;

public class Main {
    JFrame window;
    Game game;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        window = new JFrame(WINDOW_TITLE);
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setResizable(false);
        game = new Game();
        window.add(game);
        window.addKeyListener(game);
        window.addMouseListener(game);
        window.setVisible(true);
    }
}