package nl.saxion.game.animalInvaders.game;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class Ship {
    private int healthpoints;
    private int speed;
    private int xPos;
    private int yPos;
    private int height;
    private int width;

    public Ship(int healthpoints, int startingX, int startingY) {
        this.healthpoints = healthpoints;
        this.speed = 5;
        this.xPos = startingX;
        this.yPos = startingY;
        this.height = 40;
        this.width = 20;
    }

    public void drawShip() {
        moveship(speed);
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(255,0,0);
        GameApp.drawRoundedRectCentered(xPos, yPos, 20, 40, 5);
        GameApp.endShapeRendering();
    }

    private void moveship(int speed) {
        if (GameApp.isKeyPressed(Input.Keys.LEFT) && xPos > width/2 + 10) {
            this.xPos -= speed;
        }
        if (GameApp.isKeyPressed(Input.Keys.RIGHT) && xPos < GameApp.getWorldWidth() - 10 - width/2) {
            this.xPos += speed;
        }
        if (GameApp.isKeyPressed(Input.Keys.UP) && yPos < 710 - height/2) {
            this.yPos += speed;
        }
        if (GameApp.isKeyPressed(Input.Keys.DOWN) && yPos > 10 + height/2) {
            this.yPos -= speed;
        }
    }

    public void takeDamage(int damage) {
        this.healthpoints -= damage;
    }

    private void shoot(){
        //Voeg hier de code toe om te schieten
    }
}
