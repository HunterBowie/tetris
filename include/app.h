#include <SDL2/SDL.h>

#ifndef APP_GUARD
#define APP_GUARD

typedef struct App {
	SDL_Renderer *renderer;
	SDL_Window *window;
}App;

int initApp(App *app, char caption[], int x, int y, int width, int height);
void destroyApp(App *app);
#endif