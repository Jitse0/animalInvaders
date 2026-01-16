package Items;

import com.badlogic.gdx.math.Circle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;

public class Burger {
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int size;
    private Circle hitbox;
    private Game game;


    public Burger(int xPos, int yPos, int size, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.game = game;
        this.size = size;
        this.hitbox = new Circle(xPos, yPos, size);
        this.yVel = -30;
        this.xVel = (int ) (-15 + Math.random() * 30);
    }

    public void drawBurger() {
        pickUpBurger();
        moveBurger();


        GameApp.startSpriteRendering();
        GameApp.drawTexture("ChickenLeg", xPos,yPos, 16*3, 16*3);
        GameApp.endSpriteRendering();
    }

    public void moveBurger () {
        this.xPos += xVel * GameApp.getDeltaTime();
        this.yPos += yVel * GameApp.getDeltaTime();
        if(xPos < 1 || xPos > game.getWorldWidth() - 1 || yPos < 1) {
            game.removeItem(this);
        }
        hitbox.setPosition(xPos, yPos);
    }

    public void pickUpBurger() {
        if (GameApp.rectCircleOverlap(game.getShip().getHitbox(), hitbox)) {
            game.addMeat(1);
            game.addPoints(50);
            game.removeItem(this);
        }
    }
}
