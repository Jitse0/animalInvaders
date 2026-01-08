package Enemies;

import Bullets.Egg;
import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import Items.Burger;
import nl.saxion.game.animalInvaders.game.Power;
import nl.saxion.gameapp.GameApp;

public class Chicken {
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
    private int width;
    private int height;

    public Chicken(int healthpoints, int xPos, int yPos, int size, int speed, String direction, int fireRate, Game game) {
        this.healthpoints = healthpoints;
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.speed = speed;
        this.direction = direction;
        this.fireRate =  (int) (fireRate / GameApp.getDeltaTime());
        this.timer = this.fireRate;
        this.hitbox = new Rectangle(xPos, yPos, 36 * 4, 24 * 4);
        this.game = game;
        this.width = 36;
        this.height = 24;

        GameApp.addSpriteSheet("Chicken", "animations/pixilart-sprite.png",36,24);
        GameApp.addAnimationFromSpritesheet("ChickenFly", "Chicken", 0.1f, true);
        GameApp.addSound("ChickenNoise", "audio/chicken-noise.mp3");
    }

    public void drawChicken() {
        moveChicken(speed);
        shoot();

        GameApp.startSpriteRendering();
        GameApp.drawAnimation("ChickenFly", xPos, yPos, (width*4), (height*4));
        GameApp.endSpriteRendering();
    }

    public void moveChicken(int speed) {
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
            game.addBullet(new Egg(xPos, yPos, 30, 20, 1, game));
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
            Burger burger = new Burger(this.xPos, this.yPos, 5, this.game);
            game.addItem(burger);
            game.removeEnemy(this);
            Power power = new Power(this.xPos, this.yPos, 5, this.game);
            if (Math.random() < 0.15) {
                game.addPower(power);
            }
            GameApp.playSound("ChickenNoise", 1f);

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
