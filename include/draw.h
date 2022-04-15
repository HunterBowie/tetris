#ifndef DRAW_GUARD
#define DRAW_GUARD


typedef struct Color {
    int r;
    int g;
    int b;
}Color;

const Color BLUE;
const Color GREEN;
const Color RED;
const Color WHITE;
const Color BLACK;
const Color YELLOW;

void drawFill(SDL_Renderer *renderer, const Color *color);
void drawRect(SDL_Renderer *renderer, SDL_Rect *rect, const Color *color, int alpha);
void drawLine(SDL_Renderer *renderer, int startX, int startY, int endX, int endY, int thickness, const Color *color, int alpha);
void drawCircle(SDL_Renderer *renderer, int centreX, int centreY, int radius, const Color *color, int alpha);
void drawPoint(SDL_Renderer *renderer, int x, int y, const Color *color, int alpha);

#endif