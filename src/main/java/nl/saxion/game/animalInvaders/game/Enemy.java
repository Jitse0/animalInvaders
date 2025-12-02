package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.GameApp;

import java.util.ArrayList;

public class Enemy {
    private int healthpoints;
    private int posX;
    private int posY;
    private int speed;
    private String direction;
    private int fireRate;
    private int timer;
    private Game game;

    public Enemy(int healthpoints, int posX, int posY, int speed, String direction, int fireRate, Game game) {
        this.healthpoints = healthpoints;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.direction = direction;
        this.fireRate = fireRate;
        this.timer = fireRate;
        this.game = game;
    }

    public void drawEnemy() {
        moveEnemy(speed);
        shoot();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(0,255,0);
        GameApp.drawCircle(posX, posY, 10);
        GameApp.endShapeRendering();
    }

    public void moveEnemy(int speed) {
        if (direction.equals("right")) {
            if (posX > GameApp.getWorldWidth() - 10) {
                direction = "left";
            }
            posX += speed;
        }
        else if (direction.equals("left")) {
            if (posX < 10) {
                direction = "right";
            }
            posX -= speed;
        }
    }

    public void shoot() {
        if (this.timer <= 0) {
            game.bullets.add(new Bullet(posX, posY, 10, 1,0,game));
            this.timer = this.fireRate;
        }
        else {
            this.timer--;
        }
    }
}
