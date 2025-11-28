package nl.saxion.game.animalInvaders.game;

import com.badlogic.gdx.Input;
import nl.saxion.gameapp.GameApp;

public class Projectile {
    private int xPos;
    private int yPos;
    private int speed;


    public Projectile(int xPos, int yPos, int speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
    }

    public void drawProjectile() {
        moveProjectile();
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(255, 0, 0);
        GameApp.drawRoundedRectCentered(xPos, yPos, 10, 20, 5);
        GameApp.endShapeRendering();
    }

    public void moveProjectile() {
        yPos += speed;
    }
}