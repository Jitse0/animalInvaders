package nl.saxion.game;

import Screens.*;
import nl.saxion.game.animalInvaders.game.*;
import nl.saxion.gameapp.GameApp;

public class Main {
    private static Highscorescreen highscorescreen = new Highscorescreen();
    public static void main(String[] args) {
        // Add screens

        GameApp.addScreen("HomeMenu", new HomeMenu());
        GameApp.addScreen("Levelscreen", new Levelscreen(highscorescreen));
        GameApp.addScreen("Optionscreen", new Optionscreen());
        GameApp.addScreen("Soundscreen", new Soundscreen());
        GameApp.addScreen("Controlsscreen", new Controlsscreen());
        GameApp.addScreen("Creditsscreen", new Creditsscreen());
        GameApp.addScreen("Highscorescreen", highscorescreen);






        // Start game loop and show main menu screen
        GameApp.start("Animal Invaders", 1280, 720, 60, false, "HomeMenu");
    }
}
