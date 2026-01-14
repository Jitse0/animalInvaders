package nl.saxion.game.animalInvaders.game;

import Screens.Highscorescreen;
import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class HomeMenu extends ScalableGameScreen {
    int selectedItem = 0;
    public HomeMenu (){
        super(1280, 720);
    }


    @Override
    public void show() {

        GameApp.addFont("basic", "fonts/basic.ttf", 50);
        GameApp.addFont("basic1", "fonts/basic.ttf", 75);
        GameApp.addSpriteSheet("titleSheet", "animations/title.png",800,196);
        GameApp.addSound("select", "audio/menu-button.mp3");
        GameApp.addSound("confirm", "audio/confirm.mp3");
        GameApp.addAnimationFromSpritesheet("titleAnim", "titleSheet", 0.1f, true);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (GameApp.isKeyJustPressed(Input.Keys.DOWN)){
            selectedItem = (selectedItem + 1) % 4;
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("select", 1f);
            }
        }else if (GameApp.isKeyJustPressed(Input.Keys.UP)){
            selectedItem -= 1;
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("select", 1f);
            }
            if (selectedItem < 0) {
                selectedItem = 3;
            }
            if (selectedItem > 4){
                selectedItem = 0;
            }

        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 0){
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("confirm", 1f);
            }
             GameApp.switchScreen("Levelscreen");
        } else if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 1) {
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("confirm", 1f);
            }
            GameApp.switchScreen("Highscorescreen");
        } else if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 2) {
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("confirm", 1f);
            }
            GameApp.switchScreen("Optionscreen");
        } else if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 3) {
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("confirm", 1f);
            }
            GameApp.quit();
        }

        GameApp.clearScreen();

        GameApp.startSpriteRendering();


        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());
        GameApp.drawAnimation("titleAnim", (getWorldWidth() - 800) / 2, 430, 800, 196);
        GameApp.updateAnimation("titleAnim");

        if (selectedItem == 0){
            GameApp.drawTextHorizontallyCentered("basic1", "Play", getWorldWidth()/2, 375, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Play", getWorldWidth()/2, 375, "white" );
        }
        if (selectedItem == 1){
            GameApp.drawTextHorizontallyCentered("basic1", "High score (Hall of Fame)", getWorldWidth()/2, 275, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "High score (Hall of Fame)", getWorldWidth()/2, 275, "white" );
        }
        if (selectedItem == 2){
            GameApp.drawTextHorizontallyCentered("basic1", "Options", getWorldWidth()/2, 175, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Options", getWorldWidth()/2, 175, "white" );
        }
        if (selectedItem == 3){
            GameApp.drawTextHorizontallyCentered("basic1", "Quit", getWorldWidth()/2, 75, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Quit", getWorldWidth()/2, 75, "white" );
        }
        GameApp.endSpriteRendering();
    }




    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");

    }
}