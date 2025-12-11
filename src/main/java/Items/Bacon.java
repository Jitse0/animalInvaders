package Items;

import com.badlogic.gdx.math.Circle;
import nl.saxion.game.animalInvaders.game.Game;
import nl.saxion.gameapp.GameApp;

public class Bacon {
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int size;
    private Circle hitbox;
    private Game game;

    public Bacon(int xPos, int yPos, int size, Game game) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.game = game;
        this.size = size;
        this.hitbox = new Circle(xPos, yPos, size);
        this.yVel = -30;
        this.xVel = (int ) (-15 + Math.random() * 30);
    }

    public void drawBacon() {
        pickUpBacon();
        moveBacon();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(255,255,0);
        GameApp.drawCircle(xPos, yPos, size);
        GameApp.endShapeRendering();
    }

    public void moveBacon () {
        this.xPos += xVel * GameApp.getDeltaTime();
        this.yPos += yVel * GameApp.getDeltaTime();
        hitbox.setPosition(xPos, yPos);
    }

    public void pickUpBacon() {
        if (GameApp.rectCircleOverlap(game.getShip().getHitbox(), hitbox)) {
            //add effect

            game.removeBacon(this);
        }
    }
}
