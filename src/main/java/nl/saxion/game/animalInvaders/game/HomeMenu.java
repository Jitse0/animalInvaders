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
        GameApp.addTexture("background", "Photos/Background.jpg" );
        GameApp.addFont("basic", "fonts/basic.ttf", 50);
        GameApp.addSpriteSheet("titleSheet", "animations/title.png",800,196);
        GameApp.addAnimationFromSpritesheet("titleAnim", "titleSheet", 0.1f, false);
        GameApp.addSound("select", "audio/menu-button.mp3");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (GameApp.isKeyJustPressed(Input.Keys.DOWN)){
            selectedItem = (selectedItem + 1) % 4;
            GameApp.playSound("select",1f);
        }else if (GameApp.isKeyJustPressed(Input.Keys.UP)){
            selectedItem -= 1;
            GameApp.playSound("select",1f);
            if (selectedItem < 0) {
                selectedItem = 3;
            }
            if (selectedItem > 4){
                selectedItem = 0;
            }

        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 0){
             GameApp.switchScreen("Levelscreen");
        } else if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 1) {
            GameApp.switchScreen("Highscorescreen");
        } else if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 2) {
            GameApp.switchScreen("Optionscreen");
        } else if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 3) {
            GameApp.quit();
        }

        GameApp.clearScreen();

        GameApp.startSpriteRendering();


        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());
        GameApp.drawAnimation("titleAnim", (getWorldWidth() - 800) / 2, 400, 800, 196);
        GameApp.updateAnimation("titleAnim");

        if (selectedItem == 0){
            GameApp.drawTextHorizontallyCentered("basic", "Play", getWorldWidth()/2, 350, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Play", getWorldWidth()/2, 350, "black" );
        }
        if (selectedItem == 1){
            GameApp.drawTextHorizontallyCentered("basic", "High score (Hall of Fame)", getWorldWidth()/2, 250, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "High score (Hall of Fame)", getWorldWidth()/2, 250, "black" );
        }
        if (selectedItem == 2){
            GameApp.drawTextHorizontallyCentered("basic", "Options", getWorldWidth()/2, 150, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Options", getWorldWidth()/2, 150, "black" );
        }
        if (selectedItem == 3){
            GameApp.drawTextHorizontallyCentered("basic", "Quit", getWorldWidth()/2, 50, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Quit", getWorldWidth()/2, 50, "black" );
        }


        GameApp.endSpriteRendering();


    }




    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");

    }
}