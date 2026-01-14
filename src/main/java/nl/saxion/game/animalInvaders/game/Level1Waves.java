package nl.saxion.game.animalInvaders.game;

import Enemies.Boss;
import Enemies.Chicken;
import Enemies.Cow;
import Enemies.Pig;
import nl.saxion.gameapp.GameApp;

public class Level1Waves {

    private final Game game;
    private int level;

    private int wave = 0;
    private int group = 0;
    private boolean bossFight = false;

    private boolean waiting = false;
    private float timer = 0f;

    private static final float PAUSE_BETWEEN_GROUPS = 1.0f;
    private static final int LAST_WAVE = 9;

    private final int WORLD_W;
    private final int WORLD_H;

    // HUD safe zone
    private static final int MAX_Y = 610;
    private static final int MIN_Y = 360;
    private static final int MIN_X = 140;
    private static final int MAX_X_MARGIN = 140;

    // VEILIGE AFSTAND (groter dan grootste enemy)
    private static final int SAFE_W = 160;
    private static final int SAFE_H = 140;

    private float waveTextTimer = 0f;
    private String waveText = "";

    public Level1Waves(Game game, int level) {
        this.game = game;
        this.level = level;
        this.WORLD_W = (int) game.getWorldWidth();
        this.WORLD_H = (int) game.getWorldHeight();
    }

    // ---------------------------------------------------

    public void start() {
        wave = 1;
        group = 0;
        bossFight = false;
        waiting = false;
        timer = 0f;
        showWaveText(wave);
        spawnNextGroup();
    }

    public void update() {
        float dt = GameApp.getDeltaTime();

        if (waveTextTimer > 0f) waveTextTimer -= dt;

        if (bossFight) {
            if (game.getBoss() == null)
                game.finishLevel();
            GameApp.stopMusic("finalboss");
            return;
        }

        boolean cleared =
                game.getChickens().isEmpty() &&
                        game.getPigs().isEmpty() &&
                        game.getCows().isEmpty() &&
                        game.getBoss() == null;

        if (cleared && !waiting) {
            waiting = true;
            timer = PAUSE_BETWEEN_GROUPS;
        }

        if (waiting) {
            timer -= dt;
            if (timer <= 0f) {
                waiting = false;
                if (!spawnNextGroup()) {
                    wave++;
                    if (wave <= LAST_WAVE) {
                        group = 0;
                        showWaveText(wave);
                        spawnNextGroup();
                    } else {
                        startBoss();
                    }
                }
            }
        }
    }

    public void drawWaveText() {
        if (waveTextTimer <= 0f) return;
        GameApp.startSpriteRendering();
        GameApp.drawTextHorizontallyCentered("basic1", waveText, WORLD_W / 2, WORLD_H / 2, "white");
        GameApp.endSpriteRendering();
    }

    // ---------------------------------------------------

    private boolean spawnNextGroup() {
        group++;
        //Level 1
        if (this.level == 1) {
            switch (wave) {
                case 1:
                    if (group == 1) {
                        spawnGrid(3, 6);
                        return true;
                    }
                    if (group == 2) {
                        spawnCircle(10);
                        return true;
                    }
                    return false;

                case 2:
                    if (group == 1) {
                        spawnV(11);
                        return true;
                    }
                    if (group == 2) {
                        spawnGrid(4, 5);
                        return true;
                    }
                    return false;

                case 3:
                    if (group == 1) {
                        spawnSideColumns(5);
                        return true;
                    }
                    if (group == 2) {
                        spawnScatter(10);
                        return true;
                    }
                    return false;

                case 4:
                    if (group == 1) {
                        spawnArc(12);
                        return true;
                    }
                    if (group == 2) {
                        spawnGrid(4, 6);
                        return true;
                    }
                    return false;

                case 5:
                    if (group == 1) {
                        spawnCircle(12);
                        return true;
                    }
                    if (group == 2) {
                        spawnGrid(5, 5);
                        return true;
                    }
                    return false;

                case 6:
                    if (group == 1) {
                        spawnZigZag(4, 6);
                        return true;
                    }
                    if (group == 2) {
                        spawnSideColumns(6);
                        return true;
                    }
                    return false;

                case 7:
                    if (group == 1) {
                        spawnWaves(6);
                        return true;
                    }
                    if (group == 2) {
                        spawnCircle(14);
                        return true;
                    }
                    return false;

                case 8:
                    if (group == 1) {
                        spawnGrid(5, 6);
                        return true;
                    }
                    if (group == 2) {
                        spawnArc(14);
                        return true;
                    }
                    return false;

                case 9:
                    if (group == 1) {
                        GameApp.addMusic("finalboss","audio/boss_time.mp3");
                        GameApp.stopMusic("GameMusic8-bit");
                        GameApp.playMusic("finalboss", true, 1f);
                        spawnFinal();
                        spawnBoss();
                        return true;
                    }
                    return false;
            }
        }

        //Level 2
        if (this.level == 2) {
            switch (wave) {
                case 1:
                    if (group == 1) {
                        spawnGrid(2, 8);
                        return true;
                    }
                    if (group == 2) {
                        spawnScatter(12);
                        return true;
                    }
                    return false;

                case 2:
                    if (group == 1) {
                        spawnGrid(5, 4);
                        return true;
                    }
                    if (group == 2) {
                        spawnV(8);
                        return true;
                    }
                    return false;

                case 3:
                    if (group == 1) {
                        spawnSideColumns(8);
                        return true;
                    }
                    if (group == 2) {
                        spawnArc(16);
                        return true;
                    }
                    return false;

                case 4:
                    if (group == 1) {
                        spawnZigZag(3, 3);
                        return true;
                    }
                    if (group == 2) {
                        spawnV(12);
                        return true;
                    }
                    return false;

                case 5:
                    if (group == 1) {
                        spawnCircle(10);
                        return true;
                    }
                    if (group == 2) {
                        spawnGrid(5, 5);
                        return true;
                    }
                    return false;

                case 6:
                    if (group == 1) {
                        spawnZigZag(4, 6);
                        return true;
                    }
                    if (group == 2) {
                        spawnSideColumns(6);
                        return true;
                    }
                    return false;

                case 7:
                    if (group == 1) {
                        spawnWaves(6);
                        return true;
                    }
                    if (group == 2) {
                        spawnCircle(14);
                        return true;
                    }
                    return false;

                case 8:
                    if (group == 1) {
                        spawnGrid(5, 6);
                        return true;
                    }
                    if (group == 2) {
                        spawnArc(16);
                        return true;
                    }
                    return false;

                case 9:
                    if (group == 1) {
                        spawnBoss();
                        return true;
                    }
                    if (group == 2) {
                        spawnSideColumns(8);
                        spawnBoss();
                        return true;
                    }
                    return false;

            }
        }
        return false;
    }

    private void startBoss() {
        bossFight = true;
    }
    private void spawnBoss() {
        game.addEnemy(new Boss(WORLD_W / 2, 520, game));
    }

    private void showWaveText(int w) {
        waveText = "WAVE " + w;
        waveTextTimer = 1.2f;
    }

    // ---------------------------------------------------
    // SPAWN HELPERS

    private void spawnEnemy(int startX, int startY, int tx, int ty, int type) {
        ty = clampY(ty);
        tx = clampX(tx);

        int t = type % 3;
        if (t == 0) {
            Chicken e = new Chicken(1, startX, startY, 20, 100, "r", 1, game);
            e.setTarget(tx, ty);
            game.addEnemy(e);
        } else if (t == 1) {
            Pig e = new Pig(1, startX, startY, 20, 100, "r", 1, game);
            e.setTarget(tx, ty);
            game.addEnemy(e);
        } else {
            Cow e = new Cow(1, startX, startY, 20, 100, "r", 1, game);
            e.setTarget(tx, ty);
            game.addEnemy(e);
        }
    }

    private int offTop() { return WORLD_H + 300; }
    private int offLeft() { return -300; }
    private int offRight() { return WORLD_W + 300; }

    private int clampX(int x) {
        return Math.max(MIN_X, Math.min(x, WORLD_W - MAX_X_MARGIN));
    }

    private int clampY(int y) {
        return Math.max(MIN_Y, Math.min(y, MAX_Y));
    }

    // ---------------------------------------------------
    // PATTERNS (ALLEMAAL VEILIG)

    private void spawnGrid(int rows, int cols) {
        int startX = (WORLD_W - (cols - 1) * SAFE_W) / 2;
        int startY = MAX_Y;

        int seed = wave * 10 + group * 7;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int tx = startX + c * SAFE_W;
                int ty = startY - r * SAFE_H;
                spawnEnemy(tx, offTop(), tx, ty, seed + r + c);
            }
        }
    }

    private void spawnCircle(int amount) {
        int cx = WORLD_W / 2;
        int cy = 520;
        int radius = 260;

        for (int i = 0; i < amount; i++) {
            double a = Math.PI * 2 * i / amount;
            int tx = (int)(cx + Math.cos(a) * radius);
            int ty = (int)(cy + Math.sin(a) * radius) - (i % 3) * SAFE_H;
            spawnEnemy(tx, offTop(), tx, ty, i);
        }
    }

    private void spawnArc(int amount) {
        int cx = WORLD_W / 2;
        int cy = 520;
        int radius = 320;

        for (int i = 0; i < amount; i++) {
            double a = Math.PI * i / (amount - 1);
            int tx = (int)(cx + Math.cos(a) * radius);
            int ty = (int)(cy + Math.sin(a) * 180) - (i % 2) * SAFE_H;
            spawnEnemy(tx, offTop(), tx, ty, i);
        }
    }

    private void spawnSideColumns(int count) {
        for (int i = 0; i < count; i++) {
            int ty = MAX_Y - i * SAFE_H;
            spawnEnemy(offLeft(), ty, 240, ty, i);
            spawnEnemy(offRight(), ty, WORLD_W - 240, ty, i + 10);
        }
    }

    private void spawnScatter(int amount) {
        for (int i = 0; i < amount; i++) {
            int tx = MIN_X + (i % 5) * SAFE_W;
            int ty = MAX_Y - (i / 5) * SAFE_H;
            spawnEnemy(tx, offTop(), tx, ty, i);
        }
    }

    private void spawnV(int amount) {
        int cx = WORLD_W / 2;
        for (int i = 0; i < amount; i++) {
            int offset = i - amount / 2;
            int tx = cx + offset * SAFE_W / 2;
            int ty = MAX_Y - Math.abs(offset) * SAFE_H / 2;
            spawnEnemy(offRight(), ty, tx, ty, i);
        }
    }

    private void spawnZigZag(int rows, int cols) {
        int startX = (WORLD_W - (cols - 1) * SAFE_W) / 2;
        for (int r = 0; r < rows; r++) {
            int offset = (r % 2) * SAFE_W / 2;
            for (int c = 0; c < cols; c++) {
                int tx = startX + c * SAFE_W + offset;
                int ty = MAX_Y - r * SAFE_H;
                spawnEnemy(tx, offTop(), tx, ty, r + c);
            }
        }
    }

    private void spawnWaves(int cols) {
        int startX = (WORLD_W - (cols - 1) * SAFE_W) / 2;
        for (int c = 0; c < cols; c++) {
            int tx = startX + c * SAFE_W;
            spawnEnemy(tx, offTop(), tx, MAX_Y, c);
            spawnEnemy(tx, offTop(), tx, MAX_Y - SAFE_H, c + 10);
        }
    }

    private void spawnFinal() {
        spawnGrid(4, 6);
        spawnCircle(10);
        spawnSideColumns(4);
    }
}