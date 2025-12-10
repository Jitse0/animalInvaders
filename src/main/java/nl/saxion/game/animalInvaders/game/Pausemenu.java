package nl.saxion.game.animalInvaders.game;
import Screens.Highscorescreen;
import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class Pausemenu {
    int selectedItem = 0;
    private boolean paused = false;

    public boolean isPaused() {
        return paused;
    }

    public void update() {
        if (GameApp.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
        }
    }

    public Pausemenu() {}

    public void draw() {
        if (GameApp.isKeyJustPressed(Input.Keys.DOWN)){
            selectedItem = (selectedItem + 1) % 3;
        }else if (GameApp.isKeyJustPressed(Input.Keys.UP)) {
            selectedItem -= 1;
            if (selectedItem < 0) {
                selectedItem = 2;
            }
            if (selectedItem > 3) {
                selectedItem = 0;
            }
        }
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 0){
            paused = !paused;
        } else if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 1) {
            GameApp.addScreen("AnimalGame", new Game(new Highscorescreen()));
            GameApp.switchScreen("AnimalGame");
        } else if (GameApp.isKeyJustPressed((Input.Keys.SPACE ) )& selectedItem == 2) {
            GameApp.switchScreen("HomeMenu");
        }

        if (!paused) return;

        GameApp.startShapeRenderingFilled();
        GameApp.setColor(20, 20, 20, 220);
        GameApp.drawRoundedRect(465, 260, 350, 200, 15);
        GameApp.endShapeRendering();

        GameApp.startSpriteRendering();
        GameApp.addFont("pause", "fonts/basic.ttf", 45);
        GameApp.addFont("options", "fonts/basic.ttf", 25);
        GameApp.drawTextHorizontallyCentered("pause", "Paused", GameApp.getWorldWidth() / 2, 450, "white");

        if (selectedItem == 0){
            GameApp.drawTextHorizontallyCentered("options", "Resume", GameApp.getWorldWidth()/2, 390, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("options", "Resume", GameApp.getWorldWidth()/2, 390, "black" );
        }
        if (selectedItem == 1){
            GameApp.drawTextHorizontallyCentered("options", "Restart", GameApp.getWorldWidth()/2, 350, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("options", "Restart", GameApp.getWorldWidth()/2, 350, "black" );
        }
        if (selectedItem == 2){
            GameApp.drawTextHorizontallyCentered("options", "Quit", GameApp.getWorldWidth()/2, 310, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("options", "Quit", GameApp.getWorldWidth()/2, 310 , "black" );
        }
        GameApp.endSpriteRendering();
    }



}

