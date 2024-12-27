package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceStructure {
    static final Boolean I = Boolean.TRUE;
    static final Boolean O = Boolean.FALSE;
    static final List<Boolean[][]> L_PIECE = Arrays.asList(
            new Boolean[][] {
                    {O, O, O, O},
                    {O, O, O, O},
                    {O, I, O, O},
                    {O, I, I, I},
                    {O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O},
                    {O, O, O, O},
                    {O, I, I, O},
                    {O, I, O, O},
                    {O, I, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O},
                    {I, I, I, O},
                    {O, O, I, O},
                    {O, O, O, O},
                    {O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, I, O},
                    {O, O, I, O},
                    {O, I, I, O},
                    {O, O, O, O},
                    {O, O, O, O}
            }
    );

}
