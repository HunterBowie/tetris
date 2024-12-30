package com.hunterbowie.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.hunterbowie.util.Constants.*;

public enum PieceShape {
    L_PIECE(Arrays.asList(
            new Boolean[][] {
        {O, O, O, O, O},
        {O, I, O, O, O},
        {O, I, O, O, O},
        {O, I, I, O, O},
        {O, O, O, O, O}
    },
            new Boolean[][] {
        {O, O, O, O, O},
        {O, O, O, O, O},
        {O, O, O, I, O},
        {O, I, I, I, O},
        {O, O, O, O, O}
    },
            new Boolean[][] {
        {O, O, O, O, O},
        {O, O, I, I, O},
        {O, O, O, I, O},
        {O, O, O, I, O},
        {O, O, O, O, O}
    },
            new Boolean[][] {
        {O, O, O, O, O},
        {O, I, I, I, O},
        {O, I, O, O, O},
        {O, O, O, O, O},
        {O, O, O, O, O}
    }
    )),

    O_PIECE(Arrays.asList(
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, I, I, O, O},
                    {O, I, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, I, I, O, O},
                    {O, I, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, I, I, O, O},
                    {O, I, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, I, I, O, O},
                    {O, I, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            }
    )),

    S_PIECE(Arrays.asList(
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, O, I, I, O},
                    {O, I, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, I, O, O, O},
                    {O, I, I, O, O},
                    {O, O, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, O, I, I, O},
                    {O, I, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, I, O, O, O},
                    {O, I, I, O, O},
                    {O, O, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            }
    )),

    T_PIECE(Arrays.asList(
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, I, I, I, O},
                    {O, O, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, I, O, O},
                    {O, O, I, I, O},
                    {O, O, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, I, O, O},
                    {O, I, I, I, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, I, O, O},
                    {O, I, I, O, O},
                    {O, O, I, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            }
    )),

    Z_PIECE(Arrays.asList(
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, I, I, O, O},
                    {O, O, I, I, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, I, O, O},
                    {O, I, I, O, O},
                    {O, I, O, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, I, I, O, O},
                    {O, O, I, I, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, I, O, O},
                    {O, I, I, O, O},
                    {O, I, O, O, O},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            }
    )),

    J_PIECE(Arrays.asList(
            new Boolean[][] {
        {O, O, I, O, O},
        {O, O, I, O, O},
        {O, I, I, O, O},
        {O, O, O, O, O},
        {O, O, O, O, O}
    },
            new Boolean[][] {
        {O, O, O, O, O},
        {I, I, I, O, O},
        {O, O, I, O, O},
        {O, O, O, O, O},
        {O, O, O, O, O}
    },
            new Boolean[][] {
        {O, O, O, O, O},
        {O, I, I, O, O},
        {O, I, O, O, O},
        {O, I, O, O, O},
        {O, O, O, O, O}
    },
            new Boolean[][] {
        {O, O, O, O, O},
        {O, I, O, O, O},
        {O, I, I, I, O},
        {O, O, O, O, O},
        {O, O, O, O, O}
    }
    )),

    I_PIECE(Arrays.asList(
            new Boolean[][] {
                    {O, I, O, O, O},
                    {O, I, O, O, O},
                    {O, I, O, O, O},
                    {O, I, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, O, O, O, O},
                    {I, I, I, I, I},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, I, O, O, O},
                    {O, I, O, O, O},
                    {O, I, O, O, O},
                    {O, I, O, O, O},
                    {O, O, O, O, O}
            },
            new Boolean[][] {
                    {O, O, O, O, O},
                    {O, O, O, O, O},
                    {I, I, I, I, I},
                    {O, O, O, O, O},
                    {O, O, O, O, O}
            }
    ));

    private static final PieceShape[] values = values();

    public List<Boolean[][]> structure;

    PieceShape(List<Boolean[][]> structure) {
        this.structure = structure;
    }

    public static PieceShape random() {
        Random r = new Random();
        return values[r.nextInt(values.length)];
    }

    /**
     * Get the number of the farthest right column with a filled block
     */
    public int farthestRight(int rotation) {
        int highestCol = 0;
        for (int row = 0; row < PIECE_STRUCT_ROWS; row++) {
            for (int col = 0; col < PIECE_STRUCT_COLS; col++) {
                if (structure.get(rotation)[row][col]) {
                    highestCol = Math.max(col, highestCol);
                }
            }
        }
        return highestCol;
    }
    /**
     * Get the number of the farthest left column with a filled block
     */
    public int farthestLeft(int rotation) {
        int lowestCol = structure.get(rotation)[0].length-1;
        for (int row = 0; row < PIECE_STRUCT_ROWS; row++) {
            for (int col = 0; col < PIECE_STRUCT_COLS; col++) {
                if (structure.get(rotation)[row][col]) {
                    lowestCol = Math.min(col, lowestCol);
                }
            }
        }
        return lowestCol;
    }
}
