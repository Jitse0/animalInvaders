package Enemies;

import Bullets.Egg;
import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.game.animalInvaders.game.Projectile;
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
    private int fireAnim = 8;
    private boolean firing = false;
    private Rectangle hitbox = new Rectangle(xPos, yPos, width/3, 10);

    public Boss (int xPos, int yPos, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.game = game;
        GameApp.addSpriteSheet("Mech", "Photos/mech.png", 128, 64);
        GameApp.addSpriteSheet("mechFireCenter", "animations/mechFiringCenter.png", 128, 64);
    }

    public void drawBoss() {
        moveBoss(speed);
        GameApp.startSpriteRendering();
        if (firing) {
            GameApp.drawAnimationCentered("mechFireCenterAnim", xPos, yPos, width, height);
            GameApp.updateAnimation("mechFireCenterAnim");
        }
        else {
            GameApp.addAnimationFromSpritesheet("MechStatic", "Mech", 0.05f, false);
            GameApp.drawAnimationCentered("MechStatic", xPos, yPos, width, height);
        }

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
       if(timer <= 0) {
           GameApp.addAnimationFromSpritesheet("mechFireCenterAnim", "mechFireCenter", 0.05f, false);
           game.addBullet(new Egg(xPos, yPos - height/3, 30, 20, 1, game));
           timer = fireRate;
           firing = true;
       }
       else {
           timer--;
       }
       if (firing) {
            fireAnim--;
       }
       if (fireAnim <= 0) {
           firing = false;
           fireAnim = 8;
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
