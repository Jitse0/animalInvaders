package nl.saxion.game;

import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;

public class Main {
    public static void main(String[] args) {
        // Add screens
        GameApp.addScreen("AnimalGame", new Game());

        // Start game loop and show main menu screen
        GameApp.start("Animal Invaders", 1280, 720, 165, false, "AnimalGame");
    }
}
