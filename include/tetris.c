#include <SDL2/SDL.h>
#include "tetris.h"
#include "draw.h"
#include <time.h>
#include <stdlib.h>


int SHAPE_DATA[7][4][4][4] = {
{
    {
        {1, 1, 0, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    },
    {
        {1, 1, 0, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    },
    {
        {1, 1, 0, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    },
    {
        {1, 1, 0, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    }
},
{
    {
        {0, 0, 0, 0},
        {0, 0, 0, 1},
        {0, 1, 1, 1},
        {0, 0, 0, 0}
    },
    {
        {0, 1, 0, 0},
        {0, 1, 0, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {1, 1, 1, 0},
        {1, 0, 0, 0}
    },
    {
        {0, 1, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 0, 0}
    }
},
{
    {
        {0, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 1, 1, 1},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 0, 0},
        {0, 1, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 1},
        {0, 0, 0, 1},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 1, 0, 0},
        {1, 1, 0, 0}
    }
},
{
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    }
},
{
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    }
},
{
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    }
},
{
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    },
    {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    }
}
};



void _renderPiece(SDL_Renderer *renderer, int x, int y, Game *game) {

    int row = game->piece.row;
    int col = game->piece.col;
    int initialCol = col;
    for (int shapeRow = 0; shapeRow < 4; shapeRow++) {
        for (int shapeCol = 0; shapeCol < 4; shapeCol++) {
            if (SHAPE_DATA[game->piece.shape][game->piece.rotation][shapeRow][shapeCol]) {
                SDL_Rect rect = {
                .x = x+col*(game->grid.size+game->grid.margin),
                .y = y+row*(game->grid.size+game->grid.margin),
                .h = game->grid.size, .w = game->grid.size};
                drawRect(renderer, &rect, game->piece.color, 255);
            }
        col++;
        }
    col = initialCol;
    row++;
    }
}


int _isPieceGrounded(Game *game) {
    int row = game->piece.row;
    int col = game->piece.col;
    int initialCol = col;
    for (int shapeRow = 0; shapeRow < 4; shapeRow++) {
        for (int shapeCol = 0; shapeCol < 4; shapeCol++) {
            if (SHAPE_DATA[game->piece.shape][game->piece.rotation][shapeRow][shapeCol]) {
                if (row > GRID_ROWS) {
                    return 1;
                }
                if (game->grid.map[row+1][col] != &WHITE) {
                    return 1;
                }
            }
        col++;
        }
    col = initialCol;
    row++;
    }
    return 0;
}

void _mapPiece(Game *game) {
    int row = game->piece.row;
    int col = game->piece.col;
    int initialCol = col;
    for (int shapeRow = 0; shapeRow < 4; shapeRow++) {
        for (int shapeCol = 0; shapeCol < 4; shapeCol++) {
            if (SHAPE_DATA[game->piece.shape][game->piece.rotation][shapeRow][shapeCol]) {
                game->grid.map[row][col] = game->piece.color;
            }
        col++;
        }
    col = initialCol;
    row++;
    }
}

Piece _getRandomPiece(void) {
    srand(time(NULL));
    Shape shape = rand() % 7;
    Color *color = RAINBOW_COLORS[rand() % 6];    
    Piece piece = {.color = color, .shape = shape,
    .row = 0, .col = 0, .rotation = 0};
    return piece;
}

void initGame(Game *game) {
    Piece piece = {.row = 0, .col = 0, .rotation = 0, .shape = L_SHAPE, .color = &RED};
    Grid grid = {.margin = 1, .size = 25, .map = {
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE},
        {&WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE, &WHITE}
    }};
    Piece nextPiece = _getRandomPiece();
    game->grid = grid;
    game->piece = piece;
    game->nextPiece = nextPiece;
    game->score = 0;
    game->_gTimer = 0;
}

void rotatePiece(Game *game, int amount) {
    game->piece.rotation += amount;
    if (game->piece.rotation < 0) {
        game->piece.rotation = 3;
    }
    else if (game->piece.rotation > 3) {
        game->piece.rotation = 0;
    }
}

void movePiece(Game *game, int rowChange, int colChange) {
    game->piece.col += colChange;
    game->piece.row += rowChange;
}

void groundPiece(Game *game) {

}

void updateGame(Game *game) {
    if (_isPieceGrounded(game)) {
        _mapPiece(game);
        game->piece = game->nextPiece;
        Piece piece = _getRandomPiece();
        game->nextPiece = piece;

    }
    if (clock() - game->_gTimer > 80000) {
        game->_gTimer = clock();
        game->piece.row += 1;
    }
}


void renderGrid(SDL_Renderer *renderer, int x, int y, Game *game) {
    int initialX = x;
    int initialY = y;

    Grid grid = game->grid;

    for (int row = 0; row < GRID_ROWS; row++) {
        for (int col = 0; col < GRID_COLS; col++) {
            SDL_Rect rect = {.x = x, .y = y, .w = grid.size, .h = grid.size};
            drawRect(renderer, &rect, grid.map[row][col], 255);
            x += grid.size+grid.margin;
        }
        x = initialX;
        y += grid.size+grid.margin;
    }
    _renderPiece(renderer, initialX, initialY, game);
}

