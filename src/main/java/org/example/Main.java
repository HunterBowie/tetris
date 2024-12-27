package org.example;

import javax.swing.*;

import static org.example.Constants.*;

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
        game = new Game(window);
        game.start();
    }
}