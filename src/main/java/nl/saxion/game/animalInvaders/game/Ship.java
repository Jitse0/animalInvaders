package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.GameApp;

public class Ship {
    private int healthpoints;
    private int speed;
    private int xPos;
    private int yPos;

    public Ship(int healthpoints, int startingX, int startingY) {
        this.healthpoints = healthpoints;
        this.speed = 10;
        this.xPos = startingX;
        this.yPos = startingY;
    }

    public void drawShip() {
        GameApp.startShapeRenderingFilled();
        GameApp.setColor(255,0,0);
        GameApp.drawRoundedRectCentered(xPos, yPos, 70, 140, 30);
        GameApp.endShapeRendering();
    }
}
