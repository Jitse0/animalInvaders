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
        GameApp.addFont("basic1", "fonts/basic.ttf", 75);
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
            GameApp.drawTextHorizontallyCentered("basic1", "Back", 100, 75, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Back", 100, 75, "white" );
        }

            GameApp.drawTextHorizontallyCentered("basic1", "Credits", getWorldWidth()/2, 650, "white" );
            GameApp.drawTextHorizontallyCentered("basic", "Groep 43:", getWorldWidth()/2, 500, "white" );
            GameApp.drawTextHorizontallyCentered("basic", "Angela      Jitse      Tony", getWorldWidth()/2, 375, "white" );

        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");

    }
}
