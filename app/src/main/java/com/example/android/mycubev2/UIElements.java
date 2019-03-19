package com.example.android.mycubev2;

import android.graphics.Canvas;
import android.graphics.Rect;

class UIElements {

    private final static String scoreString = "Score ";

    private final Rect healthbarBackground;
    private final Rect healthbar;
    private int score;

    public UIElements() {
        healthbarBackground = new Rect(8, 8, 312, 112);
        healthbar = new Rect(10, 10, 310, 110);
    }

    public void update(int score, int hp) {
        int right = 10;
        if (hp > 0) {
            right += 3 * hp;
        }

        healthbar.set(10, 10, right, 110);
        this.score = score;
    }

    public void render(Canvas canvas) {
        canvas.drawRect(healthbarBackground, GamePaints.WHITE_PAINT);
        canvas.drawRect(healthbar, GamePaints.GREEN_PAINT);

        canvas.drawText(scoreString + score, 50, Game.getGameHeight() - 50, GamePaints.TEXT_PAINT);
    }
}
