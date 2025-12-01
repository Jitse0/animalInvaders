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
        GameApp.addColor("hudcolor", 30, 30, 30, 102);
        GameApp.drawRoundedRect(-10, -10, 205, 55, 10, "white");
        GameApp.drawRoundedRect(-10, -10, 200, 50, 10, "hudcolor");
        GameApp.endShapeRendering();
        GameApp.startSpriteRendering();
        GameApp.drawTextHorizontallyCentered("basic", "" +ship.getHealthPoints(), 40, 30, "white");
        GameApp.drawTexture("heart", 5, -5, 30, 60);
        GameApp.endSpriteRendering();

    }
}