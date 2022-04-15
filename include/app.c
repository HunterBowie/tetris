#include <SDL2/SDL.h>
#include "app.h"

int initApp(App *app, char caption[], int x, int y, int width, int height) {
    SDL_Init(SDL_INIT_EVERYTHING);     

    app->window = SDL_CreateWindow(
        caption,// window title
        x,// initial x position
        y,// initial y position
        width,// width, in pixels
        height,// height, in pixels
        0 // flags - see below
    );

    // Check that the window was successfully created
    if (app->window == NULL) {
        printf("Could not create window: %s\n", SDL_GetError());
        return 1;
    }

    app->renderer = SDL_CreateRenderer(app->window, -1, SDL_RENDERER_ACCELERATED);
    SDL_SetRenderDrawBlendMode(app->renderer, SDL_BLENDMODE_BLEND); 
    return 0;
}

void destroyApp(App *app) {
    SDL_DestroyWindow(app->window);
    SDL_Quit();
}

