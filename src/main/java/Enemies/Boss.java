package Enemies;

import Bullets.Egg;
import Bullets.Milk;
import Bullets.Mud;
import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.game.animalInvaders.game.Projectile;
import nl.saxion.gameapp.GameApp;
import nl.saxion.game.animalInvaders.game.GameSettings;



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
    private Rectangle hitbox ;

    public Boss (int xPos, int yPos, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.game = game;
        GameApp.addSpriteSheet("Mech", "Photos/mech.png", 128, 64);
        this.hitbox = new Rectangle(xPos - width/2, yPos - height/2, width, 10);

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
        GameApp.drawAnimationCentered("pilotChicken", xPos, yPos + height/4);
        GameApp.drawAnimationCentered("pilotCow", xPos-width/9, yPos + height/4);
        GameApp.drawAnimationCentered("pilotPig", xPos + width/9, yPos + height/4);

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
        hitbox.setPosition(xPos- width/2, yPos);

    }

    public void shoot() {
       if(timer <= 0) {
           GameApp.addAnimationFromSpritesheet("mechFireCenterAnim", "mechFireCenter", 0.04f, false);
           if (game.getLevel() == 1) {
               game.addBullet(new Egg(xPos, yPos - height / 3, 30, 20, 2, game));
           }
           if (game.getLevel() == 2) {
               game.addBullet(new Egg(xPos, yPos - height/3, 30, 20, 2, game));
               game.addBullet(new Milk(xPos - width/2 + width/20, yPos - height/3, 30, 20,"n", 1, game));
               game.addBullet(new Milk(xPos + width/2 - width/20, yPos - height/3, 30, 20, "n", 1, game));
           }
           if (game.getLevel() == 3) {
               game.addBullet(new Egg(xPos, yPos - height/3, 30, 20, 2, game));
               game.addBullet(new Mud(xPos - width/2 + width/20, yPos - height/3, 30, 80, 1, game));
               game.addBullet(new Mud(xPos + width/2 - width/20, yPos - height/3, 30, 80, 1, game));
               game.addBullet(new Milk(xPos, yPos - height/3, 30, 20,"l" , 1, game));
               game.addBullet(new Milk(xPos, yPos - height/3, 30, 20, "r", 1, game));
           }
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
            game.addPoints(200000);
            game.removeEnemy(this);
        }
    }
}
