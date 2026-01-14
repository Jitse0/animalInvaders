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
    private int laserCount = 1;





    public Ship(int healthpoints, int startingX, int startingY, Game game) {
        this.healthpoints = healthpoints;
        this.speed = 250; //Niet te laag maken, want dan kan hij niet meer naar boven en naar rechts
        this.xPos = startingX;
        this.yPos = startingY;
        this.height = 40;
        this.width = 20;
        this.hitbox = new Rectangle(xPos - ((21*4) / 2), yPos - (25 / 2), 21*4, 25);
        this.game = game;

    }

    public void drawShip() {
        moveShip(speed);
        shoot();
        float delta = GameApp.getDeltaTime();
        if (overheatVisual < overheatLevel) {
            overheatVisual += OVERHEAT_VISUAL_SPEED * delta;
            if (overheatVisual > overheatLevel) overheatVisual = overheatLevel;
        } else if (overheatVisual > overheatLevel) {
            overheatVisual -= OVERHEAT_VISUAL_SPEED * delta;
            if (overheatVisual < overheatLevel) overheatVisual = overheatLevel;
        }
        if (overheatVisual < 0f) overheatVisual = 0f;
        if (overheatVisual > 1f) overheatVisual = 1f;
        if (lifeChangeTimer > 0) {
            lifeChangeTimer -= GameApp.getDeltaTime();
            if (lifeChangeTimer <= 0) {
                lifeChange = 0;
            }
        }
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




    public void takeDamage(int damage) {
        if (blinkTimer > 0f) return;
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
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("explotion", 1f);
            }
            GameApp.endSpriteRendering();
            deathTimer--;
            if (deathTimer <= 0) {
                GameApp.addScreen("Scoreinputscreen", scoreinputscreen);
                GameApp.switchScreen("Scoreinputscreen");
            }
        }
    }

    private final float BASE_COOLDOWN = 0.3f;
    private float shootCooldown = BASE_COOLDOWN;
    private float timeSinceShot = BASE_COOLDOWN;
    private boolean overheated = false;
    private float overheatTimer = 0;
    private float overheatLevel = 0f;
    private final float HEAT_PER_SECOND = 0.10f;
    private final float COOL_PER_SECOND = 0.20f;
    private final float OVERHEAT_DURATION = 5f;
    private float overheatVisual = 0f;
    private final float FAST_COOLDOWN = 0.15f;
    private final float SLOW_COOLDOWN = 0.8f;
    private final float OVERHEAT_VISUAL_SPEED = 0.7f;
    private float timeSinceShootInput = 999f;
    private int lifeChange = 0;
    private float lifeChangeTimer = 0;
    private enum FireMode { NORMAL, FAST, SLOW }
    private FireMode fireMode = FireMode.NORMAL;




    private void shoot() {
        if (healthpoints <=0) {
            return;
        }
        float delta = GameApp.getDeltaTime();
        boolean wantsToShoot = GameApp.isKeyPressed(Input.Keys.SPACE) || GameApp.isKeyJustPressed(Input.Keys.SPACE);
        if (wantsToShoot) {
            timeSinceShootInput = 0f;
        } else {
            timeSinceShootInput += delta;
        }
        boolean activelyShooting = GameApp.isKeyPressed(Input.Keys.SPACE) || timeSinceShootInput < 0.12f;
        if (!overheated && activelyShooting) {
            overheatLevel += HEAT_PER_SECOND * delta;
        } else {
            overheatLevel -= COOL_PER_SECOND * delta;
        }
        if (overheatLevel < 0f) overheatLevel = 0f;
        if (overheatLevel > 1f) overheatLevel = 1f;
        if (!overheated && overheatLevel >= 1f) {
            overheated = true;
            overheatTimer = 0f;
        }
        if (overheated) {
            overheatTimer += delta;
            if (overheatTimer >= OVERHEAT_DURATION) {
                overheated = false;
                overheatLevel = 0f;
            }
            return;
        }

        timeSinceShot += delta;

        if (wantsToShoot && timeSinceShot >= shootCooldown) {
            shootLasers();
            if (!GameSettings.sfxMuted) {
                GameApp.playSound("Laser", 1f);
            }
            timeSinceShot = 0;
        }
        if (fireMode == FireMode.FAST) {
            shootCooldown = FAST_COOLDOWN;
        }
        else if (fireMode == FireMode.SLOW) {
            shootCooldown = SLOW_COOLDOWN;
        } else {
            shootCooldown = BASE_COOLDOWN;
        }

    }

    public void applyPower() {
        int r = (int) (Math.random() * 100);

        if (r < 30) {
            healthpoints++;
            lifeChange = +1;
            lifeChangeTimer = 1f;
        } else if (r < 60) {
            if (fireMode == FireMode.SLOW) {
                fireMode = FireMode.NORMAL;
            } else if (laserCount < 3) {
                laserCount++;
            } else if (fireMode == FireMode.NORMAL) {
                fireMode = FireMode.FAST;
            }

        } else if (r < 80) {
            healthpoints--;
            lifeChange = -1;
            lifeChangeTimer = 1f;
        } else if (r < 100) {
            if (fireMode == FireMode.FAST) {
                fireMode = FireMode.NORMAL;
            } else if (laserCount > 1) {
                laserCount--;
            } else if (fireMode == FireMode.NORMAL) {
                fireMode = FireMode.SLOW;
            }
        }

        if (healthpoints < 0) healthpoints = 0;
    }

    private void shootLasers() {
        int offset = 50;

        if (laserCount == 1) {
            game.addProjectile(new Projectile(xPos, yPos, 600, 1, game));
        }
        if (laserCount == 2) {
            game.addProjectile(new Projectile(xPos - offset, yPos, 600, 1, game));
            game.addProjectile(new Projectile(xPos + offset, yPos, 600, 1, game));
        }
        if (laserCount >= 3) {
            game.addProjectile(new Projectile(xPos, yPos, 600, 1, game));
            game.addProjectile(new Projectile(xPos - offset, yPos, 600, 1, game));
            game.addProjectile(new Projectile(xPos + offset, yPos, 600, 1, game));
        }
    }

    public float getOverheatLevel() {
        return overheatLevel;
    }
    public float getOverheatVisual() {
        return overheatVisual;
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
    public int getLifeChange() {
        return lifeChange;
    }

    public float getLifeChangeTimer() {
        return lifeChangeTimer;
    }


}
