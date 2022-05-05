#include <SDL2/SDL.h>

#ifndef APP_GUARD
#define APP_GUARD

typedef struct AudioData
{
	Uint8* pos;
	Uint32 length;
}AudioData;

typedef struct App {
	SDL_Renderer *renderer;
	SDL_Window *window;
	SDL_AudioDeviceID _audioDeviceID;
	SDL_AudioSpec _audioSpec;
	AudioData _audioData;
}App;




int initApp(App *app, char caption[], int x, int y, int width, int height);
void destroyApp(App *app);
void playSound(App *app, char filePath[]);
void pauseSound(App *app);
void stopSound(App *app);


#endif