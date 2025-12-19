package Bullets;

import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.game.animalInvaders.game.Ship;
import nl.saxion.gameapp.GameApp;

public class Milk {
    private int xPos;
    private int yPos;
    private int radius;
    private int speed;
    private int damage;
    private Rectangle hitbox;
    private Game game;
    private Ship ship;


    public Milk(int xPos, int yPos, int radius, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Rectangle(xPos, yPos, 16 *2, 19*2);
        this.game = game;
        this.ship = game.getShip();
    }

    public void drawMilk() {
        this.move();
        collideWithShip();
        GameApp.addTexture("Milk", "Photos/Milk_bottle.png");
        GameApp.startSpriteRendering();
        GameApp.drawTexture("Milk", xPos,yPos, (int)(756*0.04), (int)(1568*0.04));
        GameApp.endSpriteRendering();

    }

    public void move() {
        this.yPos -= speed * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
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
