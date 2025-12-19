package Enemies;

import Bullets.Mud;
import Items.Bacon;
import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;

public class Pig {
    private int healthpoints;
    private int xPos;
    private int yPos;
    private int size;
    private int speed;
    private String direction;
    private int fireRate;
    private int timer;
    private Rectangle hitbox;
    private Game game;

    public Pig(int healthpoints, int xPos, int yPos, int size, int speed, String direction, int fireRate, Game game) {
        this.healthpoints = healthpoints;
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.speed = speed;
        this.direction = direction;
        this.fireRate =  (int) (fireRate / GameApp.getDeltaTime());
        this.timer = this.fireRate;
        this.hitbox = new Rectangle(xPos, yPos, 25 *4, 37 *4);
        this.game = game;

        GameApp.addSpriteSheet("Pig", "animations/Pig.png",25,37);
        GameApp.addAnimationFromSpritesheet("Pigmoving", "Pig", 0.1f, true);
        GameApp.addSound("PigOink", "audio/pig-oink.mp3");
    }

    public void drawPig() {
        movePig(speed);
        shoot();
        GameApp.startSpriteRendering();
        GameApp.drawAnimation("Pigmoving", xPos - 25*2, yPos - 37*2, (25 * 4), (37 *4));
        GameApp.endSpriteRendering();
    }

    public void movePig(int speed) {
        if (direction.equals("right")) {
            if (xPos > GameApp.getWorldWidth() - 10) {
                direction = "left";
            }
            xPos += speed * GameApp.getDeltaTime();
        }
        else if (direction.equals("left")) {
            if (xPos < 10) {
                direction = "right";
            }
            xPos -= speed * GameApp.getDeltaTime();
        }
        hitbox.setPosition(xPos, yPos);
    }

    public void shoot() {
        if (this.timer <= 0) {
            //Schieten
            game.addBullet(new Mud(xPos, yPos, 15, 20, 1, game));
            this.timer = this.fireRate;
        }
        else {
            this.timer--;
        }
    }

    public void takeDamage(int damage) {
        healthpoints -= damage;
        game.addPoints(1500); // hier staat het aantal punten wat je er bij krijgt voor het raken van de enemy
        if (healthpoints <= 0) {
            Bacon bacon = new Bacon(this.xPos, this.yPos, 5, this.game);
            game.addItem(bacon);
            game.removeEnemy(this);
            GameApp.playSound("PigOink", 1f);
        }
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getSize() {
        return size;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}


