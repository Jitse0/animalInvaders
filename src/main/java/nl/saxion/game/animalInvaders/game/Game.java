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

    public Game(Highscorescreen highscorescreen) {
        super(1280, 720);
        this.highscorescreen = highscorescreen;
    }

    @Override
    public void show() {
        addEnemy(new Chicken(1, 640, 620, 20,100, "right", 1, this));
        addPig(new Pig(1, 300, 680, 20,100, "right", 1, this));
        addCow(new Cow(1, 100, 680, 20,100, "right", 1, this));
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

        killProjectiles();
        killBurgers();
        killBacons();
        killSteaks();
        killEggs();
        killMilks();
        killMuds();

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
        GameApp.updateAnimation("ChickenFly");
        if (!eggs.isEmpty()){
            GameApp.updateAnimation("EggThrow");
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


    public ArrayList<Chicken> getEnemies() {
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
    public void addEgg(Egg egg) {
        eggs.add(egg);
    }
    public void addMud(Mud mud) {
        muds.add(mud);
    }
    public void addMilk(Milk milk) {
        milks.add(milk);
    }
    public void addBurger(Burger burger) {
        burgers.add(burger);
    }
    public void addBacon(Bacon bacon) {
        bacons.add(bacon);
    }
    public void addSteak(Steak steak) {
        steaks.add(steak);
    }
    public void addEnemy(Chicken chicken) {
        chickens.add(chicken);
    }
    public void addPig(Pig pig) {
        pigs.add(pig);
    }
    public void addCow(Cow cow) {
        cows.add(cow);
    }

    public void removeProjectile(Projectile projectile) {
        killedProjectiles.add(projectile);
    }
    public void removeChicken(Chicken chicken) {
        chickens.remove(chicken);
    }
    public void removePigs(Pig pig) {
        pigs.remove(pig);
    }
    public void removeCows(Cow cow) {
        cows.remove(cow);
    }
    public void removeBurger(Burger burger) {
        killedBurgers.add(burger);
    }
    public void removeBacon(Bacon bacon) {
        killedBacons.add(bacon);
    }
    public void removeSteak(Steak steak) {killedSteaks.add(steak);}
    public void removeEgg(Egg egg){killedEggs.add(egg);}
    public void removeMilk(Milk milk){killedMilks.add(milk);}
    public void removeMud(Mud mud){killedMuds.add(mud);}





    public void killProjectiles() {
        for (Projectile projectile : killedProjectiles) {
            projectiles.remove(projectile);
        }
    }
    public void killEggs(){
        for (Egg egg : killedEggs){
            eggs.remove(egg);
        }
    }
    public void killMilks(){
        for (Milk milk : killedMilks){
            milks.remove(milk);
        }
    }
    public void killMuds(){
        for (Mud mud : killedMuds){
            muds.remove(mud);
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

    public void killBurgers() {
        for (Burger burger : killedBurgers) {
            burgers.remove(burger);
        }
    }
    public void killBacons() {
        for (Bacon bacon : killedBacons) {
            bacons.remove(bacon);
        }
    }
    public void killSteaks() {
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