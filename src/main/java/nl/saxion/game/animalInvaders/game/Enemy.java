package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.GameApp;

import java.util.ArrayList;

public class Enemy {
    private int healthpoints;
    private int posX;
    private int posY;
    private int size;
    private int speed;
    private String direction;
    private int fireRate;
    private int timer;
    private Game game;

    public Enemy(int healthpoints, int posX, int posY, int size, int speed, String direction, int fireRate, Game game) {
        this.healthpoints = healthpoints;
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.speed = speed;
        this.direction = direction;
        this.fireRate = (int) (fireRate / GameApp.getDeltaTime());
        this.timer = this.fireRate;
        this.game = game;
    }

    public void drawEnemy() {
        moveEnemy(speed);
        shoot();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(0,255,0);
        GameApp.drawCircle(posX, posY, size);
        GameApp.endShapeRendering();
    }

    public void moveEnemy(int speed) {
        if (direction.equals("right")) {
            if (posX > GameApp.getWorldWidth() - 10) {
                direction = "left";
            }
            posX += speed * GameApp.getDeltaTime();
        }
        else if (direction.equals("left")) {
            if (posX < 10) {
                direction = "right";
            }
            posX -= speed * GameApp.getDeltaTime();
        }
    }

    public void shoot() {
        if (this.timer <= 0) {
            //Schieten
            game.addBullet(new Bullet(posX, posY, 20, 1));
            this.timer = this.fireRate;
        }
        else {
            this.timer--;
        }
    }

    public void takeDamage(int damage) {
        healthpoints -= damage;
        if (healthpoints <= 0) {
            game.removeEnemy(this);
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSize() {
        return size;
    }
}
