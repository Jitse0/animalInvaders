package nl.saxion.game.animalInvaders.game;

import Screens.Highscorescreen;
import Screens.Scoreinputscreen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import nl.saxion.gameapp.GameApp;

public class Ship {
    private int healthpoints;
    private int speed;
    private int xPos;
    private int yPos;
    private int height;
    private int width;
    private Rectangle hitbox;
    private Game game;
    private Scoreinputscreen scoreinputscreen = new Scoreinputscreen();
    private float blinkTimer = 0f;
    private boolean visible = true;
    private float blinkSwitchTimer = 0f;
    private int deathTimer = 60;

    public Ship(int healthpoints, int startingX, int startingY, Game game) {
        this.healthpoints = healthpoints;
        this.speed = 250; //Niet te laag maken, want dan kan hij niet meer naar boven en naar rechts
        this.xPos = startingX;
        this.yPos = startingY;
        this.height = 40;
        this.width = 20;
        this.hitbox = new Rectangle(xPos - ((21*4) / 2), yPos - (25 / 2), 21*4, 25);
        this.game = game;
        GameApp.addSpriteSheet("Ship", "animations/ship.png",21,25);
        GameApp.addAnimationFromSpritesheet("ShipFly", "Ship", 0.1f, true);
        GameApp.addSpriteSheet("ShipExplosion", "animations/Ship_explode.png", 30, 32);
        GameApp.addAnimationFromSpritesheet("ShipExplodes", "ShipExplosion", 0.3f, false);
        GameApp.addSound("Laser", "audio/laser.mp3");
        GameApp.addSound("explotion", "audio/explosion.mp3");
    }

    public void drawShip() {
        moveShip(speed);
        shoot();
        damageTest();
        if (blinkTimer > 0) {
            blinkTimer -= GameApp.getDeltaTime();
            blinkSwitchTimer -= GameApp.getDeltaTime();

            if (blinkSwitchTimer <= 0) {
                visible = !visible;
                blinkSwitchTimer = 0.15f;
            }
        } else {
            visible = true;
        }
        if (healthpoints <= 0) {
            visible = false;
        }
        if (visible) {
            GameApp.startSpriteRendering();
            GameApp.drawAnimation("ShipFly", xPos - 21*3, yPos - 25*3, 21 * 6, 25 * 6);
            GameApp.endSpriteRendering();
        }
        death();
    }

    //Snelheid naar rechts en naar boven is lager dan naar links en naar beneden vanwege float > int casting, weet niet wat ik daar aan kan doen
    private void moveShip(int speed) {
        if (GameApp.isKeyPressed(Input.Keys.LEFT) && xPos > width / 2 + 10) {
            this.xPos -= (speed * GameApp.getDeltaTime());
        }
        if (GameApp.isKeyPressed(Input.Keys.RIGHT) && xPos < GameApp.getWorldWidth() - 10 - width / 2) {
            this.xPos += (speed * GameApp.getDeltaTime());
        }
        if (GameApp.isKeyPressed(Input.Keys.UP) && yPos < 710 - height / 2) {
            this.yPos += (speed * GameApp.getDeltaTime());
        }
        if (GameApp.isKeyPressed(Input.Keys.DOWN) && yPos > 10 + height / 2) {
            this.yPos -= (speed * GameApp.getDeltaTime());
        }
        hitbox.setPosition(xPos  - ((21*4) / 2), yPos - (25 / 2));
    }

    public void damageTest() {
        if (GameApp.isKeyJustPressed(Input.Keys.U)) {
            takeDamage(1);

        }
    }


    public void takeDamage(int damage) {
        this.healthpoints -= damage;
        blinkTimer = 2f;
    }

    private void death() {
        if (this.healthpoints <= 0) {
            speed = 0;
            scoreinputscreen.setHighScore(game.getHighscore());
            scoreinputscreen.setHighscoreScreen(game.getHighscorescreen());
            GameApp.startSpriteRendering();
            GameApp.drawAnimation("ShipExplodes", xPos - 30*3, yPos - 32*3, 30 * 6, 32 * 6);
            GameApp.playSound("explotion", 1f);
            GameApp.endSpriteRendering();
            deathTimer--;
            if (deathTimer <= 0) {
                GameApp.addScreen("Scoreinputscreen", scoreinputscreen);
                GameApp.switchScreen("Scoreinputscreen");
            }
        }
    }

    private float shootCooldown = 0.3f;
    private float timeSinceShot = 0;
    private boolean overheated = false;
    private float overheatTimer = 0;
    private float overheatLevel = 0f;
    private final float HEAT_PER_SECOND = 0.10f;
    private final float COOL_PER_SECOND = 0.20f;
    private final float OVERHEAT_DURATION = 5f;

    private void shoot() {
        if (healthpoints <=0) {
            return;
        }

        float delta = GameApp.getDeltaTime();

        if (!overheated && GameApp.isKeyPressed(Input.Keys.SPACE)) {
            overheatLevel += HEAT_PER_SECOND * delta;
            if (overheatLevel > 1f) {
                overheatLevel = 1f;
                overheated = true;
                overheatTimer = 0;
            }
        }

        if (!GameApp.isKeyPressed(Input.Keys.SPACE) || overheated) {
            overheatLevel -= COOL_PER_SECOND * delta;
            if (overheatLevel < 0f) overheatLevel = 0f;
        }

        if (overheated) {
            overheatTimer += delta;
            if (overheatTimer >= OVERHEAT_DURATION) {
                overheated = false;
            }
            return;
        }

        timeSinceShot += delta;

        if (GameApp.isKeyJustPressed(Input.Keys.SPACE)) {
            game.addProjectile(new Projectile(xPos, yPos, 600, 1, this.game));
            GameApp.playSound("Laser", 1f);
            timeSinceShot = 0;
        }

        if (GameApp.isKeyPressed(Input.Keys.SPACE) && timeSinceShot >= shootCooldown) {
            game.addProjectile(new Projectile(xPos, yPos, 600, 1, this.game));
            GameApp.playSound("Laser", 1f);
            timeSinceShot = 0;
        }
    }

    public float getOverheatLevel() {
        return overheatLevel;
    }

    public int getHealthPoints() {
        return healthpoints;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getxPosShip() {
        return xPos;
    }

    public int getyPosShip() {
        return yPos;
    }

    public int getWidthShip() {
        return width;
    }

    public int getHeightShip() {
        return height;
    }

}
