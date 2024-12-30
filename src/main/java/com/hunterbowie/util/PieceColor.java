package com.hunterbowie.util;
import java.awt.*;
import java.util.Random;

public enum PieceColor {

    RED(new Color(234, 51, 35)),
    BLUE(new Color(0, 0, 245)),
    CYAN(new Color(117, 251, 253)),
    GREEN(new Color(117, 251, 76)),
    YELLOW(new Color(255, 255, 84)),
    ORANGE(new Color(242, 169, 59)),
    PINK(new Color(254, 31, 247)),;

    final static PieceColor[] values = values();
    public final Color color;

    PieceColor(Color color) {
        this.color = color;
    }

    public static PieceColor random() {
        Random r = new Random();
        return values[r.nextInt(values.length)];
    }
}
