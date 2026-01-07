package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;


public class Scoreinputscreen extends ScalableGameScreen {
    int selectedItem = 0;
    private String currentName = "";
    private int highScore;
    private Highscorescreen highscorescreen;

    public Scoreinputscreen (){
        super(1280, 720);
    }

    @Override
    public void show() {
        GameApp.addTexture("background", "Photos/Background.jpg" );
        GameApp.addFont("basic", "fonts/basic.ttf", 50);
        GameApp.addFont("basic1", "fonts/basic.ttf", 75);
    }

    @Override
    public void render(float delta) {
        super.render(delta);


        GameApp.clearScreen();

        GameApp.startSpriteRendering();

        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());
        GameApp.drawTextHorizontallyCentered("basic", "Name: " + currentName, GameApp.getWorldWidth()/2, 500, "white" );
        GameApp.drawTextHorizontallyCentered("basic", "Score: " + highScore, GameApp.getWorldWidth()/2, 400, "white" );

        if (selectedItem == 0){
            GameApp.drawTextHorizontallyCentered("basic", "Back", 100, 75, "white" );
        } else {
            GameApp.drawTextHorizontallyCentered("basic1", "Back", 100, 75, "white" );
        }
        GameApp.endSpriteRendering();
        if (GameApp.isKeyJustPressed((Input.Keys.ENTER ) )& selectedItem == 0) {
            GameApp.addScreen("HighscoreScreen", highscorescreen);
            GameApp.switchScreen("HighscoreScreen");
        }
    }
    @Override
    public boolean keyTyped(char character) {
        if (character == '\b') { // Backspace
            if (!currentName.isEmpty()) {
                // Remove the last character
                currentName = currentName.substring(0, currentName.length() - 1);
            }
        } else if (character == '\r' || character == '\n') {// Enter characters
            submitName(currentName);
        } else {
            // Add the typed character
            currentName += character;
        }
        return true;
    }
    private void submitName(String name) {
        System.out.println("Name entered: " + name);
        highscorescreen.addScore(currentName,highScore);
    }
    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setHighscoreScreen(Highscorescreen highscorescreen) {
        this.highscorescreen = highscorescreen;
    }
}

