package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.game.animalInvaders.game.GameSettings;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class Levelscreen extends ScalableGameScreen {
    int selectedItem = 0;
    private Highscorescreen highscorescreen;

    public Levelscreen (Highscorescreen highscorescreen){
        super(1280, 720);
        this.highscorescreen = highscorescreen;
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
        if (GameApp.isKeyJustPressed(Input.Keys.DOWN)){
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("select", 1f);
            }
            selectedItem = (selectedItem + 1) % 4;
        }else if (GameApp.isKeyJustPressed(Input.Keys.UP)) {
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("select", 1f);
            }
            selectedItem -= 1;
            if (selectedItem < 0) {
                selectedItem = 3;
            }
            if (selectedItem > 4) {
                selectedItem = 0;
            }
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 0) {
            GameApp.addScreen("AnimalGame", new Game(highscorescreen, 1));
            GameApp.switchScreen("AnimalGame");
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 1) {
            GameApp.addScreen("AnimalGame", new Game(highscorescreen, 2));
            GameApp.switchScreen("AnimalGame");
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 2) {
            GameApp.addScreen("AnimalGame", new Game(highscorescreen, 3));
            GameApp.switchScreen("AnimalGame");
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 3) {
            GameApp.switchScreen("HomeMenu");
            selectedItem = 0;
        }

        GameApp.clearScreen();

        GameApp.startSpriteRendering();

        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());

        if (selectedItem == 0){
            GameApp.drawTextHorizontallyCentered("basic1", "Level 1", getWorldWidth()/2, 600, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Level 1", getWorldWidth()/2, 600, "white" );
        }
        if (selectedItem == 1){
            GameApp.drawTextHorizontallyCentered("basic1", "Level 2", getWorldWidth()/2, 450, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Level 2", getWorldWidth()/2, 450, "white" );
        }
        if (selectedItem == 2){
            GameApp.drawTextHorizontallyCentered("basic1", "Level 3", getWorldWidth()/2, 300, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Level 3", getWorldWidth()/2, 300, "white" );
        }
        if (selectedItem == 3){
            GameApp.drawTextHorizontallyCentered("basic1", "Back", 100, 75, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Back", 100, 75, "white" );
        }
        GameApp.endSpriteRendering();

    }

    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");

    }
}