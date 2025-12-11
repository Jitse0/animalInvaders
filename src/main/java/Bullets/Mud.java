package Bullets;

import com.badlogic.gdx.math.Circle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.game.animalInvaders.game.Ship;
import nl.saxion.gameapp.GameApp;

public class Mud {
    private int xPos;
    private int yPos;
    private int radius;
    private int speed;
    private int damage;
    private Circle hitbox;
    private Game game;
    private Ship ship;

    public Mud(int xPos, int yPos, int radius, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Circle(xPos, yPos, radius);
        this.game = game;
        this.ship = game.getShip();
    }

    public void drawMud() {
        this.move();
        collideWithShip();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(300,300,300);
        GameApp.drawCircle(xPos, yPos, radius);
        GameApp.endShapeRendering();
    }

    public void move() {
        this.yPos -= speed * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
    }

    public void collideWithShip(){
        if (GameApp.rectCircleOverlap(game.getShip().getHitbox(), hitbox)){
            game.removeMud(this);
            ship.takeDamage(damage);
        }
    }

    public void takeDamage() {
        game.removeMud(this);
        game.addPoints(100);
    }

    public Circle getHitbox() {
        return hitbox;
    }
}

