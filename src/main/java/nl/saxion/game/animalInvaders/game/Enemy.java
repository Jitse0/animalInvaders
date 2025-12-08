package nl.saxion.game.animalInvaders.game;

import com.badlogic.gdx.math.Circle;
import nl.saxion.gameapp.GameApp;

import java.util.ArrayList;

public class Enemy {
    private int healthpoints;
    private int xPos;
    private int yPos;
    private int size;
    private int speed;
    private String direction;
    private int fireRate;
    private int timer;
    private Circle hitbox;
    private Game game;

    public Enemy(int healthpoints, int xPos, int yPos, int size, int speed, String direction, int fireRate, Game game) {
        this.healthpoints = healthpoints;
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.speed = speed;
        this.direction = direction;
        this.fireRate = (int) (fireRate / GameApp.getDeltaTime());
        this.timer = this.fireRate;
        this.hitbox = new Circle(xPos, yPos, size);
        this.game = game;
    }

    public void drawEnemy() {
        moveEnemy(speed);
        shoot();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(0,255,0);
        GameApp.drawCircle(xPos, yPos, size);
        GameApp.endShapeRendering();
    }

    public void moveEnemy(int speed) {
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
            game.addBullet(new Bullet(xPos, yPos, 15, 20, 1, game));
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
            Item item = new Item(this.xPos, this.yPos, 5, this.game);
            game.addItem(item);
            game.removeEnemy(this);
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

    public Circle getHitbox() {
        return hitbox;
    }
}
