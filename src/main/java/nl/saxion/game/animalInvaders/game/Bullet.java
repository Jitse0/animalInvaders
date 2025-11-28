package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.GameApp;

public class Bullet {
    private int xPos;
    private int yPos;
    private int speed;
    private int damage;

    public Bullet(int xPos, int yPos, int speed, int damage) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.damage = damage;
    }

    public void drawBullet() {
        this.move();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(100,100,100);
        GameApp.drawCircle(xPos, yPos, 10);
        GameApp.endShapeRendering();
    }

    public void move() {
        this.yPos -= speed;
    }
}
