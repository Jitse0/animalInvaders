package nl.saxion.game.animalInvaders.game;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class Projectile {
    private int xPos;
    private int yPos;
    private int speed;
    private int damage;
    private Game game;


    public Projectile(int xPos, int yPos, int speed, int damage, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.damage = damage;
        this.game = game;
    }

    public void drawProjectile() {
        moveProjectile();
        hitEnemy();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(255, 0, 0);
        GameApp.drawRoundedRectCentered(xPos, yPos, 10, 20, 5);
        GameApp.endShapeRendering();
    }

    public void moveProjectile() {
        yPos += speed;
    }

    public void hitEnemy(){
        for (Enemy enemy : game.getEnemies()) {
            if (GameApp.rectCircleOverlap(xPos, yPos, 10, 20, enemy.getPosX(), enemy.getPosY(), enemy.getSize())) {
                game.removeProjectile(this);
                enemy.takeDamage(damage);
                break;
            }
        }
    }
}