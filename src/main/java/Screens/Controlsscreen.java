package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class Controlsscreen extends ScalableGameScreen {
    int selectedItem = 0;

    public Controlsscreen() {
        super(1280, 720);
    }

    @Override
    public void show() {
        GameApp.addTexture("background", "Photos/Background.jpg");
        GameApp.addFont("basic", "fonts/basic.ttf", 50);
        GameApp.addFont("basic1", "fonts/basic.ttf", 75);
        GameApp.addSound("select", "audio/menu-button.mp3");

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        /*
        if (GameApp.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedItem = (selectedItem + 1) % 4;
            GameApp.playSound("select", 1f);
        } else if (GameApp.isKeyJustPressed(Input.Keys.UP)) {
            selectedItem -= 1;
            GameApp.playSound("select", 1f);
            if (selectedItem < 0) {
                selectedItem = 3;
            }
            if (selectedItem > 4) {
                selectedItem = 0;
            }
        }

         */

        if (GameApp.isKeyJustPressed((Input.Keys.SPACE)) & selectedItem == 0) {
            GameApp.switchScreen("Optionscreen");
        }

        GameApp.clearScreen();

        GameApp.startSpriteRendering();

        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());

        if (selectedItem == 0) {
            GameApp.drawTextHorizontallyCentered("basic1", "Back", 100, 75, "white");

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Back", 100, 75, "white");
        }

        GameApp.drawTextHorizontallyCentered("basic", "move:                     < ^ >", getWorldWidth()/2 - 50, 575, "white");
        GameApp.drawTextHorizontallyCentered("basic", "shoot:                     spacebar", getWorldWidth()/2, 475, "white");
        GameApp.drawTextHorizontallyCentered("basic", "pause menu:          [esc]", getWorldWidth()/2 - 40, 375, "white");

        GameApp.endSpriteRendering();
    }


    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");

    }
}

