package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;
import nl.saxion.game.animalInvaders.game.GameSettings;

public class Optionscreen extends ScalableGameScreen {

    int selectedItem = 0;
    public Optionscreen (){
        super(1280, 720);
    }

    @Override
    public void show() {
        GameApp.addTexture("background", "Photos/Background.jpg" );
        GameApp.addFont("basic", "fonts/basic.ttf", 50);
        GameApp.addFont("basic1", "fonts/basic.ttf", 75);
        GameApp.addSound("select", "audio/menu-button.mp3");
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
            GameApp.switchScreen("Soundscreen");
            selectedItem = 0;
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 1) {
            GameApp.switchScreen("Controlsscreen");
            selectedItem = 0;
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 2) {
            GameApp.switchScreen("Creditsscreen");
            selectedItem = 0;
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 3) {
            GameApp.switchScreen("HomeMenu");
            selectedItem = 0;
        }
        GameApp.clearScreen();

        GameApp.startSpriteRendering();

        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());

        if (selectedItem == 0){
            GameApp.drawTextHorizontallyCentered("basic1", "Sound", getWorldWidth()/2, 525, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Sound", getWorldWidth()/2, 525, "white" );
        }
        if (selectedItem == 1){
            GameApp.drawTextHorizontallyCentered("basic1", "Controls", getWorldWidth()/2, 425, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Controls", getWorldWidth()/2, 425, "white" );
        }
        if (selectedItem == 2){
            GameApp.drawTextHorizontallyCentered("basic1", "Credits", getWorldWidth()/2, 325, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Credits", getWorldWidth()/2, 325, "white" );
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
