package nl.saxion.game.animalInvaders.game;

import Bullets.Egg;
import Bullets.Milk;
import Enemies.Chicken;
import Enemies.Cow;
import Bullets.Mud;
import Enemies.Pig;
import Items.Bacon;
import Items.Burger;
import Items.Steak;
import Screens.Highscorescreen;
import Screens.Scoreinputscreen;
import nl.saxion.gameapp.*;
import nl.saxion.gameapp.screens.ScalableGameScreen;


import java.util.ArrayList;


public class Game extends ScalableGameScreen {

    private Ship ship = new Ship(5, 640, 100, this);
    private HUD hud = new HUD(ship, this);
    private ArrayList<Chicken> chickens = new ArrayList<>();
    private ArrayList<Pig> pigs = new ArrayList<>();
    private ArrayList<Cow> cows = new ArrayList<>();
    private ArrayList<Egg> eggs = new ArrayList<>();
    private ArrayList<Milk> milks = new ArrayList<>();
    private ArrayList<Mud> muds = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Projectile> killedProjectiles = new ArrayList<>();
    private ArrayList<Egg> killedEggs = new ArrayList<>();
    private ArrayList<Milk> killedMilks = new ArrayList<>();
    private ArrayList<Mud> killedMuds = new ArrayList<>();
    private ArrayList<Burger> burgers = new ArrayList<>();
    private ArrayList<Bacon> bacons = new ArrayList<>();
    private ArrayList<Steak> steaks = new ArrayList<>();
    private ArrayList<Burger> killedBurgers = new ArrayList<>();
    private ArrayList<Bacon> killedBacons = new ArrayList<>();
    private ArrayList<Steak> killedSteaks = new ArrayList<>();
    private int highscore = 0;
    private ArrayList <String> highscoreNames = new ArrayList<>();
    private Scoreinputscreen scoreinputscreen = new Scoreinputscreen();
    private Highscorescreen highscorescreen;
    private Pausemenu pauseMenu = new Pausemenu();
    private Level level;

    public Game(Highscorescreen highscorescreen) {
        super(1280, 720);
        this.highscorescreen = highscorescreen;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        pauseMenu.update();

        if (pauseMenu.isPaused()) {
            pauseMenu.draw();
            return;
        }
        //Veeg het scherm schoon voor het volgende frame
        GameApp.clearScreen();

        kill();

        //Teken alle entities, denk aan de volgorde!
        for (Chicken chicken : chickens) {
            chicken.drawChicken();
        }
        for (Pig pig : pigs) {
            pig.drawPig();
        }
        for (Cow cow : cows) {
            cow.drawCow();
        }
        for (Egg egg : eggs) {
            egg.drawEgg();
        }
        for (Milk milk : milks) {
            milk.drawMilk();
        }
        for (Mud mud : muds) {
            mud.drawMud();
        }
        for (Projectile projectile : projectiles) {
            projectile.drawProjectile();
        }
        for (Burger burger : burgers) {
            burger.drawBurger();
        }
        for (Bacon bacon : bacons) {
            bacon.drawBacon();
        }
        for (Steak steak : steaks) {
            steak.drawSteak();
        }

        ship.drawShip();
        hud.draw();
        checkGameOver();
        GameApp.endShapeRendering();
        updateAnimations();
    }

    private void updateAnimations() {
        GameApp.updateAnimation("ChickenFly");
        if (!eggs.isEmpty()){
            GameApp.updateAnimation("EggThrow");
        }
        GameApp.updateAnimation("Pigmoving");
        GameApp.updateAnimation("Cowmoving");
        GameApp.updateAnimation("ShipFly");
        if (ship.getHealthPoints() <= 0) {
            GameApp.updateAnimation("ShipExplodes");
        }
    }

    @Override
    public void hide() {

    }
    private void checkGameOver() {
        if (chickens.isEmpty() & pigs.isEmpty() && cows.isEmpty()) {
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
    public ArrayList<Egg> getEggs() {
        return eggs;
    }
    public ArrayList<Milk> getMilks() {
        return milks;
    }
    public ArrayList<Mud> getMuds() {
        return muds;
    }


    public ArrayList<Chicken> getChickens() {
        return chickens;
    }
    public ArrayList<Pig> getPigs() {
        return pigs;
    }
    public ArrayList<Cow> getCows() {
        return cows;
    }


    public Ship getShip() {
        return ship;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }
    public void addBullet(Egg egg) {
        eggs.add(egg);
    }
    public void addBullet(Mud mud) {
        muds.add(mud);
    }
    public void addBullet(Milk milk) {
        milks.add(milk);
    }
    public void addItem(Burger burger) {
        burgers.add(burger);
    }
    public void addItem(Bacon bacon) {
        bacons.add(bacon);
    }
    public void addItem(Steak steak) {
        steaks.add(steak);
    }
    public void addEnemy(Chicken chicken) {
        chickens.add(chicken);
    }
    public void addEnemy(Pig pig) {
        pigs.add(pig);
    }
    public void addEnemy(Cow cow) {
        cows.add(cow);
    }

    public void removeProjectile(Projectile projectile) {
        killedProjectiles.add(projectile);
    }
    public void removeEnemy(Chicken chicken) {
        chickens.remove(chicken);
    }
    public void removeEnemy(Pig pig) {
        pigs.remove(pig);
    }
    public void removeEnemy(Cow cow) {
        cows.remove(cow);
    }
    public void removeItem(Burger burger) {
        killedBurgers.add(burger);
    }
    public void removeItem(Bacon bacon) {
        killedBacons.add(bacon);
    }
    public void removeItem(Steak steak) {killedSteaks.add(steak);}
    public void removeBullet(Egg egg){killedEggs.add(egg);}
    public void removeBullet(Milk milk){killedMilks.add(milk);}
    public void removeBullet(Mud mud){killedMuds.add(mud);}

    public void kill() {
        for (Projectile projectile : killedProjectiles) {
            projectiles.remove(projectile);
        }
        for (Egg egg : killedEggs){
            eggs.remove(egg);
        }
        for (Milk milk : killedMilks){
            milks.remove(milk);
        }
        for (Mud mud : killedMuds){
            muds.remove(mud);
        }
        for (Burger burger : killedBurgers) {
            burgers.remove(burger);
        }
        for (Bacon bacon : killedBacons) {
            bacons.remove(bacon);
        }
        for (Steak steak : killedSteaks) {
            steaks.remove(steak);
        }
    }
    public void addPoints(int score){
        highscore += score;
    }
    public int getHighscore(){
        return highscore;
    }
    public Highscorescreen getHighscorescreen(){
        return highscorescreen;
    }
}