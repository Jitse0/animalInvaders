package nl.saxion.game.animalInvaders.game;

import Bullets.Egg;
import Bullets.Milk;
import Enemies.Chicken;
import Enemies.Cow;
import Bullets.Mud;
import Enemies.Pig;
import com.badlogic.gdx.math.Rectangle;
import nl.saxion.gameapp.GameApp;

public class Projectile {
    private int xPos;
    private int yPos;
    private int height;
    private int width;
    private int speed;
    private int damage;
    private Rectangle hitbox;
    private Game game;


    public Projectile(int xPos, int yPos, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = 32;
        this.height = 32;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Rectangle(xPos, yPos, width, height);
        this.game = game;

        GameApp.addTexture("Laser", "Photos/laser.png" );
    }

    public void drawProjectile() {
        moveProjectile();
        hitEnemy();
        hitEgg();
        hitMilk();
        hitMud();
//        GameApp.startShapeRenderingFilled();
//        GameApp.setColor(255, 0, 0);
//        GameApp.drawRoundedRectCentered(xPos, yPos, width, height, 5);
//        GameApp.endShapeRendering();

        GameApp.startSpriteRendering();
        GameApp.drawTexture("Laser", xPos - width/2, yPos, 32, 32);
        GameApp.endSpriteRendering();
    }

    public void moveProjectile() {
        yPos += speed * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
    }

    public void hitEnemy(){
        for (Chicken chicken : game.getEnemies()) {
            if (GameApp.rectCircleOverlap(hitbox, chicken.getHitbox())) {
                game.removeProjectile(this);
                chicken.takeDamage(damage);
                break;
            }
        }
        for (Pig pig : game.getPigs()) {
            if (GameApp.rectCircleOverlap(hitbox, pig.getHitbox())) {
                game.removeProjectile(this);
                pig.takeDamage(damage);
                break;
            }
        }
        for (Cow cow : game.getCows()) {
            if (GameApp.rectCircleOverlap(hitbox, cow.getHitbox())) {
                game.removeProjectile(this);
                cow.takeDamage(damage);
                break;
            }
        }
    }

    public void hitEgg() {
        for (Egg egg : game.getEggs()) {
            if (GameApp.rectCircleOverlap(hitbox, egg.getHitbox())) {
                game.removeProjectile(this);
                egg.takeDamage();
                break;
            }
        }
    }
    public void hitMilk() {
        for (Milk milk : game.getMilks()) {
            if (GameApp.rectCircleOverlap(hitbox, milk.getHitbox())) {
                game.removeProjectile(this);
                milk.takeDamage();
                break;
            }
        }
    }
    public void hitMud() {
        for (Mud mud : game.getMuds()) {
            if (GameApp.rectCircleOverlap(hitbox, mud.getHitbox())) {
                game.removeProjectile(this);
                mud.takeDamage();
                break;
            }
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}