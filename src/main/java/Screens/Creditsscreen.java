package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class Creditsscreen extends ScalableGameScreen {
    int selectedItem = 0;
    public Creditsscreen (){
        super(1280, 720);
    }

    @Override
    public void show() {
        GameApp.addTexture("background", "Photos/Background.jpg" );
        GameApp.addFont("basic", "fonts/basic.ttf", 50);
        GameApp.addSound("select", "audio/menu-button.mp3");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 0) {
            GameApp.switchScreen("Optionscreen");
        }

        GameApp.clearScreen();

        GameApp.startSpriteRendering();

        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());

        if (selectedItem == 0){
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
