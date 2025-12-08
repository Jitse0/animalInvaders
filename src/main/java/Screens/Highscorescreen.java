package Screens;

import com.badlogic.gdx.Input;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.HashMap;
import java.util.Map;


public class Highscorescreen extends ScalableGameScreen {
    int selectedItem = 0;

    public Highscorescreen() {
        super(1280, 720);
    }

    private Map<String, Integer> name_score = new HashMap<>();


    @Override
    public void show() {
        GameApp.addTexture("background", "Photos/Background.jpg");
        GameApp.addFont("basic", "fonts/basic.ttf", 50);

    }

    private Game game;

    @Override
    public void render(float delta) {
        super.render(delta);
        if (GameApp.isKeyJustPressed((Input.Keys.SPACE)) & selectedItem == 0) {
            GameApp.switchScreen("HomeMenu");
        }

        GameApp.clearScreen();

        GameApp.startSpriteRendering();

        GameApp.drawTexture("background", 0, 0, getWorldWidth(), getWorldHeight());
        GameApp.drawTextHorizontallyCentered("basic", "Name            " + "Score", GameApp.getWorldWidth()/2, 700, "white");
        drawScores();

        if (selectedItem == 0) {
            GameApp.drawTextHorizontallyCentered("basic", "Back", 75, 75, "white");
        } else {
            GameApp.drawTextHorizontallyCentered("basic", "Back", 75, 75, "black");
        }
        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        GameApp.disposeTexture("background");
        GameApp.disposeFont("basic");
    }

    public void addScore(String name, Integer score) {
        if (name_score.get(name) == null || (name_score.get(name) != null && name_score.get(name) < score )) {
            name_score.put(name, score);
        }
    }

    public void drawScores() {
        int y = 600;
        String[] names = new String[3];
        names[0] = "";
        names[1] = "";
        names[2] = "";
        names = findTop3();

        for (int i = 0; i < 3; i++){
            if (names[i] != "") {
                GameApp.drawTextHorizontallyCentered("basic", names[i] + "       " + name_score.get(names[i]), GameApp.getWorldWidth()/2, 600 - i*100, "white");
            }
        }
    }

    public String[] findTop3() {
        int highestScore = 0;
        String highestScorer = "";
        int secondScore = 0;
        String secondScorer = "";
        int thirdScore = 0;
        String thirdScorer = "";
        String[] scores = new String[3];
        for (String key : name_score.keySet()) {
            if (name_score.get(key) > highestScore) {
                thirdScore = secondScore;
                thirdScorer = secondScorer;
                secondScore = highestScore;
                secondScorer = highestScorer;
                highestScore = name_score.get(key);
                highestScorer = key;
            }
            else if (name_score.get(key) > secondScore) {
                thirdScore = secondScore;
                thirdScorer = secondScorer;
                secondScore = name_score.get(key);
                secondScorer = key;
            }

            else if (name_score.get(key) > thirdScore) {
                thirdScore = name_score.get(key);
                thirdScorer = key;
            }
        }
        scores[0] = highestScorer;
        scores[1] = secondScorer;
        scores[2] = thirdScorer;
        return scores;
    }
}

