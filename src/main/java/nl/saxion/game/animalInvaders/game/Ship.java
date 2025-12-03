package nl.saxion.game.animalInvaders.game;

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

    public Ship(int healthpoints, int startingX, int startingY, Game game) {
        this.healthpoints = healthpoints;
        this.speed = 250; //Niet te laag maken, want dan kan hij niet meer naar boven en naar rechts
        this.xPos = startingX;
        this.yPos = startingY;
        this.height = 40;
        this.width = 20;
        this.hitbox = new Rectangle(xPos-width/2, yPos-height/2, width, height);
        this.game = game;
    }

    public void drawShip() {
        moveShip(speed);
        shoot();
        damageTest();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(255,0,0);
        GameApp.drawRoundedRectCentered(xPos, yPos, 20, 40, 5);
        GameApp.endShapeRendering();
    }

    //Snelheid naar rechts en naar boven is lager dan naar links en naar beneden vanwege float > int casting, weet niet wat ik daar aan kan doen
    private void moveShip(int speed) {
        if (GameApp.isKeyPressed(Input.Keys.LEFT) && xPos > width/2 + 10) {
            this.xPos -= (speed * GameApp.getDeltaTime());
        }
        if (GameApp.isKeyPressed(Input.Keys.RIGHT) && xPos < GameApp.getWorldWidth() - 10 - width/2) {
            this.xPos += (speed * GameApp.getDeltaTime());
        }
        if (GameApp.isKeyPressed(Input.Keys.UP) && yPos < 710 - height/2) {
            this.yPos += (speed * GameApp.getDeltaTime());
        }
        if (GameApp.isKeyPressed(Input.Keys.DOWN) && yPos > 10 + height/2) {
            this.yPos -= (speed * GameApp.getDeltaTime());
        }
        hitbox.setPosition(xPos, yPos);
    }

    public void damageTest() {
        if (GameApp.isKeyJustPressed(Input.Keys.U)) {
            takeDamage(1);
        }
    }

    public void takeDamage(int damage) {
        this.healthpoints -= damage;

        if (this.healthpoints <= 0) {
            GameApp.switchScreen("Levelscreen");
        }
    }

    private void shoot(){
        if (GameApp.isKeyJustPressed(Input.Keys.SPACE)){
        Projectile projectile = new Projectile(xPos,yPos,300, 1,this.game);
        game.addProjectile(projectile);
        }
    }
    public int getHealthPoints() {
        return healthpoints;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    public int getxPosShip(){
        return xPos;
    }
    public int getyPosShip(){
        return yPos;
    }
    public int getWidthShip(){
        return width;
    }
    public int getHeightShip(){
        return height;
    }

}
