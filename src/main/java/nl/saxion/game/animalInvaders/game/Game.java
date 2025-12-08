package nl.saxion.game.animalInvaders.game;

import Screens.Highscorescreen;
import Screens.Scoreinputscreen;
import nl.saxion.gameapp.*;
import nl.saxion.gameapp.screens.ScalableGameScreen;


import java.util.ArrayList;


public class Game extends ScalableGameScreen {

    private Ship ship = new Ship(5, 640, 100, this);
    private HUD hud = new HUD(ship, this);
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Projectile> killedProjectiles = new ArrayList<>();
    private ArrayList<Bullet> killedBullets = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> killedItems = new ArrayList<>();
    private int highscore = 0;
    private ArrayList <String> highscoreNames = new ArrayList<>();
    private Scoreinputscreen scoreinputscreen = new Scoreinputscreen();
    private Highscorescreen highscorescreen;

    public Game(Highscorescreen highscorescreen) {
        super(1280, 720);
        this.highscorescreen = highscorescreen;
    }

    @Override
    public void show() {
        addEnemy(new Enemy(1, 640, 680, 20,100, "right", 1, this));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //Veeg het scherm schoon voor het volgende frame
        GameApp.clearScreen();

        killProjectiles();
        killItems();
        killBullets();

        //Teken alle entities, denk aan de volgorde!
        for (Enemy enemy : enemies) {
            enemy.drawEnemy();
        }
        for (Bullet bullet : bullets) {
            bullet.drawBullet();
        }
        for (Projectile projectile : projectiles) {
            projectile.drawProjectile();
        }
        for (Item item : items) {
            item.drawItem();
        }

        ship.drawShip();
        hud.draw();
        checkGameOver();
        GameApp.endShapeRendering();
    }

    @Override
    public void hide() {

    }
    private void checkGameOver() {
        if (enemies.isEmpty()) {
            //GameApp.switchScreen("Levelscreen");
            scoreinputscreen.setHighScore(highscore);
            scoreinputscreen.setHighscoreScreen(highscorescreen);
            GameApp.addScreen("Scoreinputscreen", scoreinputscreen);
            GameApp.switchScreen("Scoreinputscreen");
            //Hier moet hij naar scoreinput-screen en daarna terug naar main menu.
            //Hierna naar highscore-screen
            //Daarna naar homemenu-screen

        }
    }
    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Ship getShip() {
        return ship;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }
    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }
    public void addItem(Item item) {
        items.add(item);
    }
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeProjectile(Projectile projectile) {
        killedProjectiles.add(projectile);
    }
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
    public void removeItem(Item item) {
        killedItems.add(item);
    }
    public void removeBullet(Bullet bullet){killedBullets.add(bullet);}



    public void killProjectiles() {
        for (Projectile projectile : killedProjectiles) {
            projectiles.remove(projectile);
        }
    }
    public void killBullets(){
        for (Bullet bullet : killedBullets){
            bullets.remove(bullet);
        }
    }
    public void addNameHighscores(String name){
        highscoreNames.add(name);
    }

    public String showHighscoreNameList(String name ){
        for (int i = 0; i < highscoreNames.size(); i++) {
            name = highscoreNames.get(i);
            System.out.println(name);
        }return name;
    }

    public void killItems() {
        for (Item item : killedItems) {
            items.remove(item);
        }
    }
    public void addPoints(int score){
        highscore += score;
    }
    public int getHighscore(){
        return highscore;
    }
}
