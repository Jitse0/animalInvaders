package Enemies;

import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import Bullets.Milk;
import Items.Steak;
import nl.saxion.game.animalInvaders.game.Power;
import nl.saxion.gameapp.GameApp;
import nl.saxion.game.animalInvaders.game.GameSettings;

public class Cow {
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
    private float targetX, targetY;
    private boolean arrived = false;
    private float entrySpeed = 250f;




    public Cow(int healthpoints, int xPos, int yPos, int size, int speed, String direction, int fireRate, Game game) {
        this.healthpoints = 3;
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.speed = speed;
        this.direction = direction;
        this.fireRate =  (int) (fireRate / GameApp.getDeltaTime());
        this.timer = this.fireRate;
        this.hitbox = new Rectangle(xPos, yPos, 30 *3, 35 *3);
        this.game = game;
        this.width= 30;
        this.height = 35;

    }

    public void drawCow() {
        moveToTarget();     // eerst binnen vliegen

        if (arrived) {
            shoot();        // pas schieten als hij op plek is
            // moveChicken(speed);  // NIET meer bewegen na arrival
        }

        GameApp.startSpriteRendering();
        GameApp.drawAnimation("Cowmoving", xPos - 45, yPos - 54, (width * 3), (height * 3));
        GameApp.endSpriteRendering();

        hitbox.setPosition(xPos - 45, yPos - 54);
        if (GameApp.rectOverlap(hitbox, game.getShip().getHitbox())) {
            game.getShip().takeDamage(1);
        }
    }
    public void aim() {
        int reach = (int) (game.getWorldWidth()/8);
        if (game.getShip().getxPosShip() < xPos - reach) {
            direction = "l";
        }
        if (xPos - reach <= game.getShip().getxPosShip() && game.getShip().getxPosShip() <= xPos + reach ) {
            direction = "n";
        }
        if (game.getShip().getxPosShip() > xPos + reach) {
            direction = "r";
        }
    }

    public void shoot() {
        if (!arrived) return;

        if (this.timer <= 0) {

            if (game.tryEnemyShoot()) {
                aim();
                game.addBullet(new Milk(xPos, yPos, 15, 20,direction ,1, game));
            }
            this.timer = this.fireRate;
        } else {
            this.timer--;
        }
    }

    public void takeDamage(int damage) {
        healthpoints -= damage;
        game.addPoints(2000); // hier staat het aantal punten wat je er bij krijgt voor het raken van de enemy
        if (healthpoints <= 0) {
            Steak steak = new Steak(this.xPos, this.yPos, 5, this.game);
            game.addItem(steak);
            game.removeEnemy(this);
            Power power = new Power(this.xPos, this.yPos, 5, this.game);
            if (Math.random() < 0.1) {
                game.addPower(power);
            }
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("CowMooing", 1f);
            }
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
    public void setTarget(float tx, float ty) {
        targetX = tx;
        targetY = ty;
        arrived = false;
    }

    private void moveToTarget() {
        if (arrived) return;

        float dt = GameApp.getDeltaTime();

        float dx = targetX - xPos;
        float dy = targetY - yPos;

        float dist = (float)Math.sqrt(dx * dx + dy * dy);

        if (dist < 5f) {
            xPos = (int) targetX;
            yPos = (int) targetY;
            arrived = true;
            return;
        }

        float vx = (dx / dist) * entrySpeed;
        float vy = (dy / dist) * entrySpeed;

        xPos += vx * dt;
        yPos += vy * dt;
    }
}
