package Bullets;

import com.badlogic.gdx.math.Circle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.game.animalInvaders.game.Ship;
import nl.saxion.gameapp.GameApp;

public class Milk {
    private int xPos;
    private int yPos;
    private int radius;
    private int speed;
    private int damage;
    private Circle hitbox;
    private Game game;
    private Ship ship;


    public Milk(int xPos, int yPos, int radius, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Circle(xPos, yPos, radius);
        this.game = game;
        this.ship = game.getShip();
    }

    public void drawMilk() {
        this.move();
        collideWithShip();
        GameApp.addTexture("Milk", "Photos/Pak_milk.png");
        GameApp.startSpriteRendering();
        GameApp.drawTexture("Milk", xPos,yPos, 16*4, 19*4);
        GameApp.endSpriteRendering();

    }

    public void move() {
        this.yPos -= speed * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
    }

    public void collideWithShip(){
        if (GameApp.rectCircleOverlap(game.getShip().getHitbox(), hitbox)){
            game.removeMilk(this);
            ship.takeDamage(damage);
        }
    }

    public void takeDamage() {
        game.removeMilk(this);
        game.addPoints(100);
    }

    public Circle getHitbox() {
        return hitbox;
    }
}
