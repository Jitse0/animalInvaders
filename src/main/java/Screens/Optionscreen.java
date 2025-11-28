package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class Optionscreen extends ScalableGameScreen {

    int selectedItem = 0;
    public Optionscreen (){
        super(1280, 720);
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
            selectedItem = (selectedItem + 1) % 4;
        }else if (GameApp.isKeyJustPressed(Input.Keys.UP)) {
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
            GameApp.drawTextHorizontallyCentered("basic", "Sound", getWorldWidth()/2, 350, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Sound", getWorldWidth()/2, 350, "black" );
        }
        if (selectedItem == 1){
            GameApp.drawTextHorizontallyCentered("basic", "Controls", getWorldWidth()/2, 250, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Controls", getWorldWidth()/2, 250, "black" );
        }
        if (selectedItem == 2){
            GameApp.drawTextHorizontallyCentered("basic", "Credits", getWorldWidth()/2, 150, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Credits", getWorldWidth()/2, 150, "black" );
        }
        if (selectedItem == 3){
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
