package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.GameApp;

public class Enemy {
    private int healthpoints;
    private int posX;
    private int posY;
    private int speed;
    private String direction;
    private int fireRate;

    public Enemy(int healthpoints, int posX, int posY, int speed, String direction, int fireRate) {
        this.healthpoints = healthpoints;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.direction = direction;
        this.fireRate = fireRate;
    }

    public void drawEnemy() {
        moveEnemy(speed);
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(0,255,0);
        GameApp.drawCircle(posX, posY, 20);
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
        if (fireRate <= 0) {
            //Schieten
        }
    }
}
