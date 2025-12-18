package nl.saxion.game.animalInvaders.game;

import Enemies.Boss;
import Enemies.Chicken;
import Enemies.Cow;
import Enemies.Pig;

import java.util.ArrayList;

public class Level {
    private Game game;
    private ArrayList<Cow> cows = new ArrayList<>();
    private ArrayList<Chicken> chickens = new ArrayList<>();
    private ArrayList<Pig> pigs = new ArrayList<>();
    private int number;

    public Level (int number, Game game) {
        this.number = number;
        this.game = game;
    }

    public void loadLevel() {
        switch (number) {
            case 1:
                level1();
            case 2:
                level2();
            default:
                break;
        }
    }

    private void level1() {
        game.addEnemy(new Chicken(1, 640, 620, 20,100, "right", 1, game));
        game.addEnemy(new Pig(1, 300, 680, 20,100, "right", 1, game));
        game.addEnemy(new Cow(1, 100, 680, 20,100, "right", 1, game));
    }
    private void level2() {
    }
}
