package Enemies;

import Bullets.Egg;
import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;



public class Boss {
    private int health = 20;
    private int xPos;
    private int yPos;
    private int height = 196;
    private int width = 512;
    private int speed = 20;
    private String direction = "right";
    private Game game;
    private int fireRate = (int) (1 / GameApp.getDeltaTime());
    private int timer = fireRate;
    private Rectangle hitbox = new Rectangle(xPos, yPos, width/3, 10);

    public Boss (int xPos, int yPos, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.game = game;
        GameApp.addTexture("Mech", "Photos/mech.png");
    }

    public void drawBoss() {
        moveBoss(speed);
        shoot();

        GameApp.startSpriteRendering();
        GameApp.drawTexture("Mech", xPos - width/2, yPos - height/2, width, height);
        GameApp.endSpriteRendering();
    }

    public void moveBoss(int speed) {
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
        hitbox.setPosition(xPos- width/6, yPos);

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

    public Rectangle getHitbox () {
        return hitbox;
    }

    public void takeDamage(int damage) {
        health -= damage;

        if (health <=0) {
            game.removeEnemy(this);
        }
    }
}
