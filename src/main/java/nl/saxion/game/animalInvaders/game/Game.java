package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.*;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;


public class Game extends ScalableGameScreen {

    private Ship ship = new Ship(10, 640, 100);
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Bullet> bullets = new ArrayList<>();
    public ArrayList<Projectile> projectiles = new ArrayList<>();

    public Game() {
        super(1280, 720);
    }

    @Override
    public void show() {
        enemies.add(new Enemy(10, 640, 680, 1, "right", 165, this));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //Veeg het scherm schoon voor het volgende frame
        GameApp.clearScreen();
        //Teken alle entities, ship als laatste!
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
}
