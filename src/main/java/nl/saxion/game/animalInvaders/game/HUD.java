package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.GameApp;

public class HUD {
    private Ship ship;

    public HUD (Ship ship) {
        this.ship = ship;
    }

    public void draw() {
        GameApp.addFont("basic", "fonts/basic.ttf", 20);
        GameApp.addTexture("heart", "Photos/heart.png");

        GameApp.startShapeRenderingFilled();
        GameApp.addColor("hudcolor", 30, 30, 30);
        GameApp.addColor("white", 255, 255, 255);
        GameApp.drawRoundedRect(-10, -10, 205, 55, 10, "white");
        GameApp.drawRoundedRect(-10, -10, 200, 50, 10, "hudcolor");
        GameApp.drawRoundedRect(-10, 625, 340, 60 , 10, "white");
        GameApp.drawRoundedRect(-10, 625, 350, 100, 10, "hudcolor");

        float level = ship.getOverheatLevel();
        int barX = 125;
        int barY = 700;
        float barWidth = 200f;
        float barHeight = 10f;
        float filledWidth = barWidth * level;
        GameApp.addColor("red", 255, 0, 0 );
        GameApp.drawRoundedRect(barX, barY, barWidth, barHeight, 0, "white");
        GameApp.drawRoundedRect(barX, barY, filledWidth, barHeight, 0, "red");

        GameApp.endShapeRendering();

        GameApp.startSpriteRendering();

        GameApp.drawTextHorizontallyCentered("basic", "" + ship.getHealthPoints(), 40, 30, "white");
        GameApp.drawTexture("heart", 5, -5, 30, 60);

        GameApp.endSpriteRendering();



    }
    
}