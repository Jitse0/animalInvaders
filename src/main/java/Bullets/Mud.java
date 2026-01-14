package Bullets;

import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.game.animalInvaders.game.Ship;
import nl.saxion.gameapp.GameApp;

public class Mud {
    private int xPos;
    private int yPos;
    private int radius;
    private int speed;
    private int damage;
    private Rectangle hitbox;
    private Game game;
    private Ship ship;

    public Mud(int xPos, int yPos, int radius, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Rectangle(xPos, yPos, 37, 21);
        this.game = game;
        this.ship = game.getShip();
    }

    public void drawMud() {
        this.move();
        collideWithShip();
        GameApp.startSpriteRendering();
        GameApp.drawTexture("Mud", xPos - 18, yPos - 10, (37), (21));
        GameApp.endSpriteRendering();
    }

    public void move() {
        this.yPos -= speed * GameApp.getDeltaTime();
        if (this.yPos < 1) {
            game.removeBullet(this);
        }
        hitbox.setPosition(xPos - 18, yPos - 10);
    }

    public void collideWithShip(){
        if (GameApp.rectOverlap(game.getShip().getHitbox(), hitbox)){
            game.removeBullet(this);
            ship.takeDamage(damage);
        }
    }

    public void takeDamage() {
        game.removeBullet(this);
        game.addPoints(100);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}

