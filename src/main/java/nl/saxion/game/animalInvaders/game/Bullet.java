package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.GameApp;

public class Bullet {
    private int xPos;
    private int yPos;
    private int radius;
    private int speed;
    private int damage;
    private Game game;
    private Ship ship;

    public Bullet(int xPos, int yPos, int radius, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = 10;
        this.speed = speed;
        this.damage = damage;
        this.game = game;
        this.ship = ship;
    }

    public void drawBullet() {
        this.move();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(100,100,100);
        GameApp.drawCircle(xPos, yPos, radius);
        GameApp.endShapeRendering();
    }

    public void move() {
        this.yPos -= speed;
    }

    public void collideWithShip(){
        game.getBullets();
        for (Bullet bullet : game.getBullets()){
            if (GameApp.rectCircleOverlap(ship.getxPosShip(), ship.getyPosShip(), ship.getWidthShip(), ship.getHeightShip(), bullet.xPos, bullet.yPos, bullet.radius)){
                ship.takeDamage(1);
            }
        }
    }
}
