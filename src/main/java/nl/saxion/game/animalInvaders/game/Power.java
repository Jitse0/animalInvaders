package nl.saxion.game.animalInvaders.game;

import com.badlogic.gdx.math.Circle;
import nl.saxion.gameapp.GameApp;

public class Power {
    private int xPos;
    private int yPos;
    private int size;
    private int xVel;
    private int yVel;
    private Circle hitbox;
    private Game game;

    public Power(int xPos, int yPos, int size, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.yVel = -30;
        this.xVel = (int) (+15 + Math.random() * 30);
        this.game = game;
        this.size = size;
        this.hitbox = new Circle(xPos, yPos, size);

    }

    public void drawPower() {
        pickUpPower();
        this.move();
        GameApp.startSpriteRendering();
        GameApp.drawTexture("Power", xPos, yPos, 16 * 2, 16 * 2);
        GameApp.endSpriteRendering();
    }

    public void move() {
        this.xPos += xVel * GameApp.getDeltaTime();
        this.yPos += yVel * GameApp.getDeltaTime();
        if(xPos < 1 || xPos > game.getWorldWidth() - 1 || yPos < 1) {
            game.removePower(this);
        }
        hitbox.setPosition(xPos, yPos);
        hitbox.setPosition(xPos, yPos);
    }

    private void pickUpPower() {
        if (GameApp.rectCircleOverlap(game.getShip().getHitbox(), hitbox)){
            game.getShip().applyPower();
            game.removePower(this);
        }
    }
}

