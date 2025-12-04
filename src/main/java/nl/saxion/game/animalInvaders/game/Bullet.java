package nl.saxion.game.animalInvaders.game;

import com.badlogic.gdx.math.Circle;
import nl.saxion.gameapp.GameApp;

public class Bullet {
    private int xPos;
    private int yPos;
    private int radius;
    private int speed;
    private int damage;
    private Circle hitbox;
    private Game game;
    private Ship ship;

    public Bullet(int xPos, int yPos, int radius, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = 10;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Circle(xPos, yPos, radius);
        this.game = game;
        this.ship = game.getShip();
    }

    public void drawBullet() {
        this.move();
        collideWithShip();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(100,100,100);
        GameApp.drawCircle(xPos, yPos, radius);
        GameApp.endShapeRendering();
    }

    public void move() {
        this.yPos -= speed * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
    }

    public void collideWithShip(){
        if (GameApp.rectCircleOverlap(game.getShip().getHitbox(), hitbox)){
            game.removeBullet(this);
            ship.takeDamage(damage);
        }
    }

    public void takeDamage() {
        game.removeBullet(this);
        //TODO implement score
    }

    public Circle getHitbox() {
        return hitbox;
    }
}
