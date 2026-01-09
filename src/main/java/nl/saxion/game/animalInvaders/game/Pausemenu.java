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
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("select", 1f);
            }
        }else if (GameApp.isKeyJustPressed(Input.Keys.UP)) {
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("select", 1f);
            }
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
            GameApp.addScreen("AnimalGame", new Game(new Highscorescreen(), 1));
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
        GameApp.addFont("options1", "fonts/basic.ttf", 35);
        GameApp.drawTextHorizontallyCentered("pause", "Paused", GameApp.getWorldWidth() / 2, 450, "white");

        if (selectedItem == 0){
            GameApp.drawTextHorizontallyCentered("options1", "Resume", GameApp.getWorldWidth()/2, 390, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("options", "Resume", GameApp.getWorldWidth()/2, 390, "white" );
        }
        if (selectedItem == 1){
            GameApp.drawTextHorizontallyCentered("options1", "Restart", GameApp.getWorldWidth()/2, 350, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("options", "Restart", GameApp.getWorldWidth()/2, 350, "white" );
        }
        if (selectedItem == 2){
            GameApp.drawTextHorizontallyCentered("options1", "Quit", GameApp.getWorldWidth()/2, 310, "white" );

        } else {
            GameApp.drawTextHorizontallyCentered("options", "Quit", GameApp.getWorldWidth()/2, 310 , "white" );
        }
        GameApp.endSpriteRendering();
    }



}

