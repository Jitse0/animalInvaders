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
    private ArrayList<Power> powers = new ArrayList<>();
    private ArrayList<Burger> killedBurgers = new ArrayList<>();
    private ArrayList<Bacon> killedBacons = new ArrayList<>();
    private ArrayList<Steak> killedSteaks = new ArrayList<>();
    private ArrayList<Power> killedPowers = new ArrayList<>();
    private int highscore = 0;
    private ArrayList <String> highscoreNames = new ArrayList<>();
    private Scoreinputscreen scoreinputscreen = new Scoreinputscreen();
    private Highscorescreen highscorescreen;
    private Pausemenu pauseMenu = new Pausemenu(this);
    private int level;
    private Waves waves;
    private int enemyShotsLeft = 2;
    private float enemyShotWindowTimer = 0f;
    private static final float ENEMY_SHOT_WINDOW = 1.5f;



    public Game(Highscorescreen highscorescreen, int level) {
        super(1280, 720);
        this.highscorescreen = highscorescreen;
        this.level = level;
        GameApp.addTexture("Power", "Photos/Power.png");
        GameApp.addTexture("Laser", "Photos/laser.png");
        GameApp.addTexture("Bacon", "Photos/bacon_pig.png");
        GameApp.addTexture("ChickenLeg", "Photos/Chicken_leg4.png");
        GameApp.addTexture("Steak", "Photos/Steak.png");
        GameApp.addSpriteSheet("Pig", "animations/Pig.png",25,37);
        GameApp.addAnimationFromSpritesheet("Pigmoving", "Pig", 0.1f, true);
        GameApp.addSound("PigOink", "audio/pig-oink.mp3");
        GameApp.addSpriteSheet("Cow", "animations/Cow.png",30,35);
        GameApp.addAnimationFromSpritesheet("Cowmoving", "Cow", 0.1f, true);
        GameApp.addSound("CowMooing", "audio/cow-mooing.mp3");
        GameApp.addSpriteSheet("Chicken", "animations/pixilart-sprite.png",36,24);
        GameApp.addAnimationFromSpritesheet("ChickenFly", "Chicken", 0.1f, true);
        GameApp.addSound("ChickenNoise", "audio/chicken-noise.mp3");
        GameApp.addTexture("Mech", "Photos/mech.png");
        GameApp.addSpriteSheet("mechFireCenter", "animations/mechFiringCenter.png", 128, 64);
        GameApp.addSpriteSheet("chickenPilot", "animations/pixilart-sprite.png", 36, 24);
        GameApp.addAnimationFromSpritesheet("pilotChicken", "chickenPilot", 0.05f, false);
        GameApp.addSpriteSheet("cowPilot", "animations/Cow.png", 30, 35);
        GameApp.addAnimationFromSpritesheet("pilotCow", "cowPilot", 0.05f, false);
        GameApp.addSpriteSheet("pigPilot", "animations/Pig.png", 25, 37);
        GameApp.addAnimationFromSpritesheet("pilotPig", "pigPilot", 0.05f, false);
        GameApp.addTexture("Mud", "Photos/Mud.png");
        GameApp.addTexture("Milk", "Photos/Milk_bottle.png");
        GameApp.addSpriteSheet("Egg", "animations/bullet_egg.png",19,18);
        GameApp.addAnimationFromSpritesheet("EggThrow", "Egg", 0.075f, true);
        GameApp.addSpriteSheet("Ship", "animations/ship.png",21,25);
        GameApp.addAnimationFromSpritesheet("ShipFly", "Ship", 0.1f, true);
        GameApp.addSpriteSheet("ShipExplosion", "animations/Ship_explode.png", 30, 32);
        GameApp.addAnimationFromSpritesheet("ShipExplodes", "ShipExplosion", 0.3f, false);
        GameApp.addSound("Laser", "audio/laser.mp3");
        GameApp.addSound("explotion", "audio/explosion.mp3");
        GameApp.addSound("select", "audio/menu-button.mp3");
        GameApp.addMusic("GameMusic8-bit", "audio/GameMusic8-bit.mp3");
        GameApp.addTexture("background", "Photos/Background.jpg" );
        GameApp.addSound("select", "audio/menu-button.mp3");
        GameApp.addSound("confirm", "audio/confirm.mp3");
        GameApp.addAnimationFromSpritesheet("titleAnim", "titleSheet", 0.1f, true);
        GameApp.addTexture("heart", "Photos/heart.png");
        GameApp.addTexture("meat", "Photos/meat.png");
        waves = new Waves(this, this.level);
    }

    @Override

    public void show() {
        GameApp.addTexture("Background", "Photos/space_background.png");

        if (!GameSettings.musicMuted) {
            GameApp.playMusic("GameMusic8-bit", true, 1f);
        } else {
            GameApp.stopMusic("GameMusic8-bit");
        }

        waves.start();
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
        waves.update();
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
        if (boss != null) {
            boss.drawBoss();
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
        for (Power power : powers) {
            power.drawPower();
        }

        ship.drawShip();
        hud.draw();
        waves.drawWaveText();

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

        }
    }

    @Override
    public void hide() {
        GameApp.stopMusic("GameMusic8-bit");
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
    public int getLevel() {
        return level;
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
    public void addPower(Power power) {
        powers.add(power);
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
    public void removePower(Power power){killedPowers.add(power);}

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
        for (Power power : killedPowers) {
            powers.remove(power);
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
    public void finishLevel() {
        scoreinputscreen.setHighScore(highscore);
        scoreinputscreen.setHighscoreScreen(highscorescreen);
        GameApp.addScreen("Scoreinputscreen", scoreinputscreen);
        GameApp.switchScreen("Scoreinputscreen");
    }
    public boolean tryEnemyShoot() {

        enemyShotWindowTimer += GameApp.getDeltaTime();


        if (enemyShotWindowTimer >= ENEMY_SHOT_WINDOW) {
            enemyShotWindowTimer = 0f;
            enemyShotsLeft = 2;
        }


        if (enemyShotsLeft <= 0) return false;

        enemyShotsLeft--;
        return true;
    }




}