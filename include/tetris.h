#ifndef GRID_GUARD
#define GRID_GUARD
#include "draw.h"
#include <SDL2/SDL.h>
#include <time.h>
#define GRID_ROWS 20
#define GRID_COLS 10

typedef enum Shape {
    O_SHAPE,
    L_SHAPE,
    J_SHAPE,
    S_SHAPE,
    I_SHAPE,
    Z_SHAPE,
    T_SHAPE
} Shape;

typedef struct Piece {
    Shape shape;
    Color *color;
    int row;
    int col;
    int rotation;
} Piece;

typedef struct Grid {
    Color* map[GRID_ROWS][GRID_COLS];
    int margin;
    int size;
} Grid;

typedef struct Game {
    int running;
    Grid grid;
    Piece piece;
    Piece nextPiece;
    int score;
    clock_t _gTimer;
} Game;

void initGame(Game *game);
void rotatePiece(Game *game, int amount);
void movePiece(Game *game, int rowChange, int colChange);
void groundPiece(Game *game);
void updateGame(Game *game);
int isGameOver(Game *game);
void renderGrid(SDL_Renderer *renderer, int x, int y, Game *game);





#endif