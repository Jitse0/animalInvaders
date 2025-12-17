package Bullets;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
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

        GameApp.addSpriteSheet("Egg", "animations/bullet_egg.png",19,18);
        GameApp.addAnimationFromSpritesheet("EggThrow", "Egg", 0.075f, true);
    }


    public void drawEgg() {
        this.move();
        GameApp.startSpriteRendering();
        GameApp.drawAnimation("EggThrow", xPos-((width*4)/2), yPos-((height*4)/2), (width*4), (height*4));

        GameApp.endSpriteRendering();
        collideWithShip();



        //GameApp.startShapeRenderingFilled();
        //GameApp.setColor(100,100,100);
        //GameApp.drawCircle(xPos, yPos, radius);
        //GameApp.endShapeRendering();

    }

    public void move() {
        this.yPos -= speed * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
    }

    public void collideWithShip(){
        if (GameApp.rectCircleOverlap(game.getShip().getHitbox(), hitbox)){
            game.removeEgg(this);
            ship.takeDamage(damage);
        }
    }

    public void takeDamage() {
        game.removeEgg(this);
        game.addPoints(100);
    }

    public Circle getHitbox() {
        return hitbox;
    }
}
