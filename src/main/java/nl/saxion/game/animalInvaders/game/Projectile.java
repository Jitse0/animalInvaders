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
        this.hitbox = new Rectangle(xPos, yPos, 5, height);
        this.game = game;

        GameApp.addTexture("Laser", "Photos/laser.png" );
    }

    public void drawProjectile() {
        moveProjectile();
        hitEnemy();

        GameApp.startSpriteRendering();
        GameApp.drawTexture("Laser", xPos - width/2, yPos, 32, 32);
        GameApp.endSpriteRendering();

//        GameApp.startShapeRenderingFilled();
//        GameApp.setColor(255,0,0);
//        GameApp.drawRect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(),hitbox.getHeight());
//        GameApp.endShapeRendering();
    }

    public void moveProjectile() {
        yPos += speed * GameApp.getDeltaTime();
        hitbox.setPosition(xPos - 2, yPos);
    }

    public void hitEnemy(){
        //Hit Chicken
        for (Chicken chicken : game.getChickens()) {
            if (GameApp.rectOverlap(hitbox, chicken.getHitbox())) {
                game.removeProjectile(this);
                chicken.takeDamage(damage);
                break;
            }
        }
        //Hit Pig
        for (Pig pig : game.getPigs()) {
            if (GameApp.rectOverlap(hitbox, pig.getHitbox())) {
                game.removeProjectile(this);
                pig.takeDamage(damage);
                break;
            }
        }
        //Hit Cow
        for (Cow cow : game.getCows()) {
            if (GameApp.rectOverlap(hitbox, cow.getHitbox())) {
                game.removeProjectile(this);
                cow.takeDamage(damage);
                break;
            }
        }
        //Hit Egg
        for (Egg egg : game.getEggs()) {
            if (GameApp.rectCircleOverlap(hitbox, egg.getHitbox())) {
                game.removeProjectile(this);
                egg.takeDamage();
                break;
            }
        }
        //Hit Milk
        for (Milk milk : game.getMilks()) {
            if (GameApp.rectOverlap(hitbox, milk.getHitbox())) {
                game.removeProjectile(this);
                milk.takeDamage();
                break;
            }
        }
        //Hit Mud
        for (Mud mud : game.getMuds()) {
            if (GameApp.rectOverlap(hitbox, mud.getHitbox())) {
                game.removeProjectile(this);
                mud.takeDamage();
                break;
            }
        }
        //Hit Boss
        if (game.getBoss() != null && GameApp.rectOverlap(hitbox, game.getBoss().getHitbox())) {
            game.removeProjectile(this);
            game.getBoss().takeDamage(damage);
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}