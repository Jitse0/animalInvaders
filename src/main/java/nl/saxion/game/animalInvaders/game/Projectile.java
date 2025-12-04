package nl.saxion.game.animalInvaders.game;

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
        this.width = 10;
        this.height = 20;
        this.speed = speed;
        this.damage = damage;
        this.hitbox = new Rectangle(xPos, yPos, width, height);
        this.game = game;
    }

    public void drawProjectile() {
        moveProjectile();
        hitEnemy();
        hitBullet();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(255, 0, 0);
        GameApp.drawRoundedRectCentered(xPos, yPos, width, height, 5);
        GameApp.endShapeRendering();
    }

    public void moveProjectile() {
        yPos += speed * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
    }

    public void hitEnemy(){
        for (Enemy enemy : game.getEnemies()) {
            if (GameApp.rectCircleOverlap(hitbox, enemy.getHitbox())) {
                game.removeProjectile(this);
                enemy.takeDamage(damage);
                break;
            }
        }
    }

    public void hitBullet() {
        for (Bullet bullet : game.getBullets()) {
            if (GameApp.rectCircleOverlap(hitbox, bullet.getHitbox())) {
                game.removeProjectile(this);
                bullet.takeDamage();
                break;
            }
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}