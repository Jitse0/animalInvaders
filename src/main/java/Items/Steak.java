package Items;

import com.badlogic.gdx.math.Rectangle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;

public class Steak {
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int size;
    private Rectangle hitbox;
    private Game game;

    public Steak(int xPos, int yPos, int size, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.game = game;
        this.size = size;
        this.hitbox = new Rectangle(xPos, yPos, 37, 21);
        this.yVel = -30;
        this.xVel = (int ) (-15 + Math.random() * 30);
        GameApp.addTexture("Steak", "Photos/Steak.png");
    }

    public void drawSteak() {
        pickUpSteak();
        moveSteak();
        GameApp.startSpriteRendering();
        GameApp.drawTexture("Steak", xPos, yPos, (37), (21));
        GameApp.endSpriteRendering();
    }

    public void moveSteak () {
        this.xPos += xVel * GameApp.getDeltaTime();
        this.yPos += yVel * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
    }

    public void pickUpSteak() {
        if (GameApp.rectOverlap(game.getShip().getHitbox(), hitbox)) {
            //add effect

            game.removeItem(this);
        }
    }
}
