package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;
import nl.saxion.game.animalInvaders.game.GameSettings;


public class Soundscreen extends ScalableGameScreen {
    int selectedItem = 0;
    public Soundscreen (){
        super(1280, 720);
    }




    @Override
    public void show() {
        GameApp.addTexture("background", "Photos/Background.jpg");
        GameApp.addFont("basic", "fonts/basic.ttf", 50);
        GameApp.addFont("basic1", "fonts/basic.ttf", 75);
        GameApp.addMusic("GameMusic8-bit", "audio/GameMusic8-bit.mp3");


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        String soundText = GameSettings.musicMuted ? "Unmute Music" : "Mute Music";
        String sfxText   = GameSettings.sfxMuted   ? "Unmute Sounds" : "Mute Sounds";


        if (GameApp.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedItem = (selectedItem + 1) % 3;
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("select", 1f);
            }

        } else if (GameApp.isKeyJustPressed(Input.Keys.UP)) {
            selectedItem -= 1;
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("select", 1f);
            }

            if (selectedItem < 0) {
                selectedItem = 1;
            }
            if (selectedItem > 3) {
                selectedItem = 0;
            }
        }
        if (GameApp.isKeyJustPressed(Input.Keys.SPACE) && selectedItem == 0) {
            // mute/unmute
            GameSettings.musicMuted = !GameSettings.musicMuted;

            if (GameSettings.musicMuted) {
                GameApp.stopMusic("GameMusic8-bit");
            }
        } else if (GameApp.isKeyJustPressed(Input.Keys.SPACE) && selectedItem == 1) {
            GameSettings.sfxMuted = !GameSettings.sfxMuted;
        } else if (GameApp.isKeyJustPressed(Input.Keys.SPACE) && selectedItem == 2) {
            // back
            GameApp.switchScreen("Optionscreen");
            selectedItem = 0;
        }


        GameApp.clearScreen();

        GameApp.startSpriteRendering();

        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());

        GameApp.drawTextHorizontallyCentered("basic1", "Sound", getWorldWidth() / 2, 650, "white");
        if (selectedItem == 0) {
            GameApp.drawTextHorizontallyCentered("basic1", soundText, getWorldWidth() / 2, 450, "white");
        } else {
            GameApp.drawTextHorizontallyCentered("basic", soundText, getWorldWidth() / 2, 450, "white");
        }
        if (selectedItem == 1) {
            GameApp.drawTextHorizontallyCentered("basic1", sfxText, getWorldWidth() / 2, 300, "white");
        } else {
            GameApp.drawTextHorizontallyCentered("basic", sfxText, getWorldWidth() / 2, 300, "white");
        }
        if (selectedItem == 2) {
            GameApp.drawTextHorizontallyCentered("basic1", "Back", 100, 75, "white");

        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Back", 100, 75, "white");
        }

        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");

    }
    private double musicVolume = 0.8;
    private double sfxVolume = 0.8;

    public void setMusicVolume(double value) {
        musicVolume = clamp(value, 0.0, 1.0);
    }

    public void setSfxVolume(double value) {
        sfxVolume = clamp(value, 0.0, 1.0);
    }

    public double getMusicVolume() {
        return musicVolume;
    }

    public double getSfxVolume() {
        return sfxVolume;
    }

    private double clamp(double value, double min, double max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}

