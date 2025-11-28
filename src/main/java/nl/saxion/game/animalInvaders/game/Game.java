package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.*;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.nio.channels.Pipe;
import java.util.ArrayList;


public class Game extends ScalableGameScreen {

    private Ship ship = new Ship(10, 640, 100, this);
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Projectile> killedProjectiles = new ArrayList<>();

    public Game() {
        super(1280, 720);
    }

    @Override
    public void show() {
        addEnemy(new Enemy(1, 640, 680, 20,1, "right", 165, this));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //Veeg het scherm schoon voor het volgende frame
        GameApp.clearScreen();
        //Teken alle entities, denk aan de volgorde!
        killProjectiles();
        for (Enemy enemy : enemies) {
            enemy.drawEnemy();
        }
        for (Bullet bullet : bullets) {
            bullet.drawBullet();
        }
        for (Projectile projectile : projectiles) {
            projectile.drawProjectile();
        }
        ship.drawShip();
        GameApp.endShapeRendering();
    }

    @Override
    public void hide() {

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

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }
    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeProjectile(Projectile projectile) {
        killedProjectiles.add(projectile);
    }
    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void killProjectiles() {
        for (Projectile projectile : killedProjectiles) {
            projectiles.remove(projectile);
        }
    }
}
