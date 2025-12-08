package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.game.animalInvaders.game.Game;
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

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (GameApp.isKeyJustPressed(Input.Keys.DOWN)){
            selectedItem = (selectedItem + 1) % 2;
        }else if (GameApp.isKeyJustPressed(Input.Keys.UP)) {
            selectedItem -= 1;
            if (selectedItem < 0) {
                selectedItem = 1;
            }
            if (selectedItem > 2) {
                selectedItem = 0;
            }
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 0) {
            GameApp.addScreen("AnimalGame", new Game(highscorescreen));
            GameApp.switchScreen("AnimalGame");
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 1) {
            GameApp.switchScreen("HomeMenu");
            selectedItem = 0;
        }

        GameApp.clearScreen();

        GameApp.startSpriteRendering();

        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());

        if (selectedItem == 0){
            GameApp.drawTextHorizontallyCentered("basic", "Level 1", getWorldWidth()/2, 600, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Level 1", getWorldWidth()/2, 600, "black" );
        }
        if (selectedItem == 1){
            GameApp.drawTextHorizontallyCentered("basic", "Back", 75, 75, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Back", 75, 75, "black" );
        }
        GameApp.endSpriteRendering();

    }

    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");

    }
}
