package nl.saxion.game.animalInvaders.game;

import nl.saxion.gameapp.GameApp;

public class HUD {
    private Ship ship;
    private Game game;
    public HUD (Ship ship, Game game) {
        this.ship = ship;
        this.game = game;
        GameApp.addFont("basic", "fonts/basic.ttf", 20);
        GameApp.addFont("basic1", "fonts/basic.ttf", 40);
        GameApp.addFont("basic2", "fonts/basic.ttf", 70);

    }

    public void draw() {


        GameApp.startShapeRenderingFilled();
        GameApp.addColor("hudcolor", 30, 30, 30);
        GameApp.addColor("white", 255, 255, 255);
        GameApp.drawRoundedRect(-10, -10, 205, 55, 10, "white");
        GameApp.drawRoundedRect(-10, -10, 200, 50, 10, "hudcolor");
        GameApp.drawRoundedRect(-10, 675, 350, 100, 10, "hudcolor");

        float level = ship.getOverheatVisual();
        int barX = 130;
        int barY = 700;
        float barWidth = 200f;
        float barHeight = 10f;
        float filledWidth = barWidth * level;
        GameApp.addColor("red", 255, 0, 0 );
        GameApp.addColor("green", 0, 255, 0 );
        GameApp.drawRoundedRect(barX, barY, barWidth, barHeight, 0, "white");
        GameApp.drawRoundedRect(barX, barY, filledWidth, barHeight, 0, "red");

        GameApp.endShapeRendering();

        GameApp.startSpriteRendering();

        GameApp.drawTextHorizontallyCentered("basic", "Score: " + game.getHighscore(), 70, 710, "white");
        GameApp.drawTextHorizontallyCentered("basic", "" + ship.getHealthPoints(), 40, 30, "white");
        GameApp.drawTexture("heart", 5, -5, 30, 60);
        GameApp.drawTexture("meat", 40, -5, 30, 60);
        if (ship.getLifeChangeTimer() > 0) {
            if (ship.getLifeChange() > 0) {
                GameApp.drawTextHorizontallyCentered("basic1", "+", 20, 80, "green");
            } else if (ship.getLifeChange() < 0) {
                GameApp.drawTextHorizontallyCentered("basic2", "-", 20, 95, "red");
            }
        }
        GameApp.endSpriteRendering();
    }

}