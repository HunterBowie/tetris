#include <stdio.h>
#include <SDL2/SDL.h>
#include "draw.h"
#include "app.h"
#include "entity.h"

int WIDTH = 800;
int HEIGHT = 600;
int NUM_ENTITIES = 8;

int main(int argc, char *argv[]) {
    
    App app = {};

    int error = initApp(&app, "My Physics Simulation", 100, 100, WIDTH, HEIGHT);
    if (error == 1) {
        return 1;
    }

    Entity topEntity;
    initEntity(&topEntity, 0, -10, WIDTH, 10, -1);
    Entity bottomEntity;
    initEntity(&bottomEntity, 0, HEIGHT, WIDTH, 10, -1);
    Entity rightEntity;
    initEntity(&rightEntity, WIDTH, 0, 10, HEIGHT, -1);
    Entity leftEntity;
    initEntity(&leftEntity, -10, 0, 10, HEIGHT, -1);

    Entity smallEntity;
    initEntity(&smallEntity, 300, 200, 50, 50, 2);
    Entity bigEntity;
    initEntity(&bigEntity, 400, 80, 100, 100, 4);

    Entity otherEntity1;
    initEntity(&otherEntity1, 300, 250, 50, 50, 2);
    Entity otherEntity2;
    initEntity(&otherEntity2, 300, 300, 50, 50, 2);

    Entity *entities[] = {&topEntity, &bottomEntity, &rightEntity, &leftEntity,
    &smallEntity, &bigEntity, &otherEntity1, &otherEntity2};


    World world = {.gravity = {0, 0}, .collisionEnergyLost=0.0, .airResistance=0.1};
   
    int speed = 2;
    int forceX;
    int forceY;
    int quit = 0;
    const Uint8 *
    keystate = SDL_GetKeyboardState(NULL);
    while (!quit){
        forceX = 0;
        forceY = 0;
        SDL_Event event;
        while (SDL_PollEvent(&event)) {
            switch (event.type) {
                case SDL_QUIT:
                    quit = 1;
                    break;
                
                case SDL_KEYDOWN:
                    if (event.key.keysym.scancode == SDL_SCANCODE_W) {
                        forceY = -speed*smallEntity.mass;
                        pushEntity(&smallEntity, forceX, forceY);
                        break;
                    }
                    if (event.key.keysym.scancode == SDL_SCANCODE_S) {
                        forceY = speed*smallEntity.mass;
                        pushEntity(&smallEntity, forceX, forceY);
                        break;
                    }
                    if (event.key.keysym.scancode == SDL_SCANCODE_A) {
                        forceX = -speed*smallEntity.mass;
                        pushEntity(&smallEntity, forceX, forceY);
                        break;
                    }
                    if (event.key.keysym.scancode == SDL_SCANCODE_D) {
                        forceX = speed*smallEntity.mass;
                        pushEntity(&smallEntity, forceX, forceY);
                        break;
                    }
                    if (event.key.keysym.scancode == SDL_SCANCODE_UP) {
                        forceY = -speed*bigEntity.mass;
                        pushEntity(&bigEntity, forceX, forceY);
                        break;
                    }
                    if (event.key.keysym.scancode == SDL_SCANCODE_DOWN) {
                        forceY = speed*bigEntity.mass;
                        pushEntity(&bigEntity, forceX, forceY);
                        break;
                    }
                    if (event.key.keysym.scancode == SDL_SCANCODE_LEFT) {
                        forceX = -speed*bigEntity.mass;
                        pushEntity(&bigEntity, forceX, forceY);
                        break;
                    }
                    if (event.key.keysym.scancode == SDL_SCANCODE_RIGHT) {
                        forceX = speed*bigEntity.mass;
                        pushEntity(&bigEntity, forceX, forceY);
                        break;
                    }
                    break;
                
                case SDL_MOUSEBUTTONDOWN:
                    speed += 1;
                    break;
                    
                              
                default:
                    break;
            }

        }
        
        if (keystate[SDL_SCANCODE_W]) {
            
        }
        else if (keystate[SDL_SCANCODE_S]) {
            
        }
        if (keystate[SDL_SCANCODE_A]) {
            
        }
        else if (keystate[SDL_SCANCODE_D]) {
            
        }
        if (keystate[SDL_SCANCODE_UP]) {

        }
        else if (keystate[SDL_SCANCODE_DOWN]) {

        }
        if (keystate[SDL_SCANCODE_LEFT]) {

        }
        else if (keystate[SDL_SCANCODE_RIGHT]) {
        }
        updateEntities(entities, NUM_ENTITIES, &world);

        drawFill(app.renderer, &WHITE);
        
        for (int index = 0; index < NUM_ENTITIES; index++) {
            drawRect(app.renderer, &entities[index]->rect, &BLUE, 255);
        }
        SDL_RenderPresent(app.renderer);
        
        
        
    }
    destroyApp(&app);
    
    return 0;
}

//gcc main.c -o play -I include -L lib -l SDL2-2.0.0