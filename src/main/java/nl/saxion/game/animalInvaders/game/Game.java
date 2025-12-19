package nl.saxion.game.animalInvaders.game;

import Bullets.Egg;
import Bullets.Milk;
import Enemies.Boss;
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
    private Boss boss;
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
    private int level;

    public Game(Highscorescreen highscorescreen, int level) {
        super(1280, 720);
        this.highscorescreen = highscorescreen;
        this.level = level;
    }

    @Override
    public void show() {
        addEnemy(new Chicken(1, 640, 620, 20,100, "right", 1, this));
        addEnemy(new Pig(1, 300, 680, 20,100, "right", 1, this));
        addEnemy(new Cow(1, 100, 680, 20,100, "right", 1, this));
        GameApp.addMusic("GameMusic8-bit", "audio/Game_Background_music.mp3");
        GameApp.playMusic("GameMusic8-bit", true, 0.6f);
        addEnemy(new Boss(800, 500, this));
        GameApp.addTexture("Background", "Photos/space_background.png");
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

        GameApp.startSpriteRendering();
        GameApp.drawTexture("Background", 0, 0, getWorldWidth(), getWorldHeight());
        GameApp.endSpriteRendering();


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
        boss.drawBoss();
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
        if (!chickens.isEmpty()) {
            GameApp.updateAnimation("ChickenFly");
        }

        if (!eggs.isEmpty()){
            GameApp.updateAnimation("EggThrow");
        }

        if (!pigs.isEmpty()) {
            GameApp.updateAnimation("Pigmoving");
        }
        if (!cows.isEmpty()) {
            GameApp.updateAnimation("Cowmoving");
        }
        if (ship != null && ship.getHealthPoints() > 0) {
            GameApp.updateAnimation("ShipFly");
        }
        if (ship != null &&   ship.getHealthPoints() <= 0) {
            GameApp.updateAnimation("ShipExplodes");
        }
        if (boss != null) {
            GameApp.updateAnimation("mechFireCenterAnim");
        }
    }

    @Override
    public void hide() {
        GameApp.disposeMusic("GameMusic8-bit");
    }
    private void checkGameOver() {
        if (chickens.isEmpty() & pigs.isEmpty() && cows.isEmpty() && boss == null) {
            //GameApp.switchScreen("Levelscreen");
            scoreinputscreen.setHighScore(highscore);
            scoreinputscreen.setHighscoreScreen(highscorescreen);
            GameApp.addScreen("Scoreinputscreen", scoreinputscreen);
            GameApp.switchScreen("Scoreinputscreen");
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

    public Boss getBoss() {
        return boss;
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
    public void addEnemy(Boss boss) {
        this.boss = boss;
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

    public void removeEnemy(Boss boss) {
        this.boss = null;
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