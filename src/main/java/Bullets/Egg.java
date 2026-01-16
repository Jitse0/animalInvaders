package Bullets;

import com.badlogic.gdx.math.Circle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.game.animalInvaders.game.Ship;
import nl.saxion.gameapp.GameApp;

public class Egg {
    private int xPos;
    private int yPos;
    private int radius;
    private int speed;
    private int damage;
    private Circle hitbox;
    private Game game;
    private Ship ship;
    private int width;
    private int height;

    public Egg(int xPos, int yPos, int radius, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Circle(xPos, yPos, radius);
        this.game = game;
        this.ship = game.getShip();
        this.width = 19;
        this.height = 18;

    }


    public void drawEgg() {
        this.move();
        GameApp.startSpriteRendering();
        GameApp.drawAnimation("EggThrow", xPos-width, yPos-height, (width*2), (height*2));

        GameApp.endSpriteRendering();
        collideWithShip();

    }

    public void move() {
        this.yPos -= speed ;
        if (this.yPos < 1) {
            game.removeBullet(this);
        }
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
        game.addPoints(100);
    }

    public Circle getHitbox() {
        return hitbox;
    }
}
