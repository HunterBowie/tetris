#include <SDL2/SDL.h>
#include "app.h"



void _audioCallback(void* userdata, Uint8* stream, int streamLength)
{
	AudioData* audio = (AudioData*)userdata;

	if(audio->length == 0)
	{
		return;
	}

	Uint32 length = (Uint32)streamLength;
	length = (length > audio->length ? audio->length : length);

	SDL_memcpy(stream, audio->pos, length);

	audio->pos += length;
	audio->length -= length;
}


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
    SDL_CloseAudioDevice(app->_audioDeviceID);
    SDL_DestroyWindow(app->window);
    SDL_Quit();
}

void playSound(App *app, char filePath[]){
	Uint8* wavStart;
	Uint32 wavLength;

	if(SDL_LoadWAV(filePath, &app->_audioSpec, &wavStart, &wavLength) == NULL)
	{
		printf("Could not setup audio window: %s\n", SDL_GetError());
		return;
	}
	app->_audioData.pos = wavStart;
	app->_audioData.length = wavLength;

	app->_audioSpec.callback = _audioCallback;
	app->_audioSpec.userdata = &app->_audioData;

    SDL_AudioDeviceID deviceID = SDL_OpenAudioDevice(NULL, 0, &app->_audioSpec, NULL,
			SDL_AUDIO_ALLOW_ANY_CHANGE);
    if(deviceID == 0)
	{
		printf("Device issues: %s\n", SDL_GetError());
		return;
	}
    app->_audioDeviceID = deviceID;
    SDL_PauseAudioDevice(deviceID, 0);
   
}

void pauseSound(App *app) { 
    SDL_PauseAudioDevice(app->_audioDeviceID, 1);
}

void stopSound(App *app) {
    SDL_CloseAudioDevice(app->_audioDeviceID);
}

