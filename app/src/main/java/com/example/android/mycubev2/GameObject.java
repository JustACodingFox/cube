package com.example.android.mycubev2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class GameObject {

    protected Rect rectangle;
    protected Paint paint;

    protected int xVelocity;
    protected int yVelocity;

    public abstract void update();

    public abstract void render(Canvas canvas);
}
