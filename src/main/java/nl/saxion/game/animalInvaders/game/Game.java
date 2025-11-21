package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.*;
import nl.saxion.gameapp.screens.ScalableGameScreen;


public class Game extends ScalableGameScreen {


    public Game() {
        super(1280, 720);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //Handle de inputs
        handleInputKeys(delta);
        handleMouseInput(delta);

        //Veeg het scherm schoon voor het volgende frame
        GameApp.clearScreen();
        GameApp.endShapeRendering();
    }

    private void handleInputKeys(float delta) {

    }

    private void handleMouseInput(float delta) {

    }

    @Override
    public void hide() {

    }
}
