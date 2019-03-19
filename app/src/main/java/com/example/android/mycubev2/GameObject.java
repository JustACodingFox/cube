package com.example.android.mycubev2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

abstract class GameObject {

    Rect rectangle;
    Paint paint;

    int xVelocity;
    int yVelocity;

    public abstract void update();

    public abstract void render(Canvas canvas);
}
