package com.example.android.mycubev2;

import android.graphics.Paint;

public class GamePaints {

    //game
    public static final Paint PLAYER_PAINT = new Paint();
    public static final Paint BASIC_ENEMY_PAINT = new Paint();

    //ui elements
    public static final Paint WHITE_PAINT = new Paint();
    public static final Paint GREEN_PAINT = new Paint();
    public static final Paint TEXT_PAINT = new Paint();

    public static void setPaints() {
        PLAYER_PAINT.setARGB(255, 0, 0, 255);
        BASIC_ENEMY_PAINT.setARGB(255, 255, 0, 0);

        WHITE_PAINT.setARGB(255, 255, 255, 255);
        GREEN_PAINT.setARGB(255, 0, 255, 0);

        TEXT_PAINT.setARGB(255, 255, 255, 255);
        TEXT_PAINT.setTextAlign(Paint.Align.LEFT);
        TEXT_PAINT.setTextSize(50);
    }
}
