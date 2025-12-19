package Enemies;

import Bullets.Egg;
import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;



public class Boss {
    private int health = 20;
    private int xPos;
    private int yPos;
    private int height = 256;
    private int width = 512;
    private int speed = 20;
    private String direction = "right";
    private Game game;
    private int fireRate = (int) (1 / GameApp.getDeltaTime());
    private int timer = fireRate;
    private int fireAnim = 300;
    private boolean firing = false;
    private Rectangle hitbox = new Rectangle(xPos, yPos, width/3, 10);

    public Boss (int xPos, int yPos, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.game = game;
        GameApp.addTexture("Mech", "Photos/mech.png");
        GameApp.addSpriteSheet("mechFireCenter", "animations/mechFiringCenter.png", 128, 64);
        GameApp.addAnimationFromSpritesheet("mechFireCenterAnim", "mechFireCenter", 0.05f, true);
    }

    public void drawBoss() {
        moveBoss(speed);
        GameApp.startSpriteRendering();
        GameApp.drawTexture("Mech", xPos - width/2, yPos - height/2, width, height);
        GameApp.endSpriteRendering();
        GameApp.startSpriteRendering();
        GameApp.drawAnimation("mechFireCenterAnim", xPos - width/2, yPos - height/2, width, height);
        GameApp.endSpriteRendering();
        shoot();
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
            while (firing) {
            GameApp.startSpriteRendering();
            GameApp.drawAnimation("mechFireCenterAnim", xPos - width/2, yPos - height/2, width, height);
            GameApp.endSpriteRendering();
            fireAnim--;
            if (fireAnim <= 0) {
                firing = false;
                fireAnim = 300;
            }
        }
        if (this.timer <= 0) {
            //Center Cannon
            game.addBullet(new Egg(xPos, yPos - height/2, 30, 20, 1, game));
            this.timer = this.fireRate;
            firing = true;

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
