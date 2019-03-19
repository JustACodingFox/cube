package com.example.android.mycubev2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class BasicEnemy extends GameObject {

    public static final int WIDTH = 50;
    public static final int VELOCITY = 5;

    public BasicEnemy(int left, int top, int right, int bottom, Paint paint, int xVel, int yVel) {
        this.rectangle = new Rect(left, top, right, bottom);
        this.paint = paint;
        this.xVelocity = xVel;
        this.yVelocity = yVel;
    }

    public BasicEnemy(Rect rect, Paint paint, int xVel, int yVel) {
        this.rectangle = rect;
        this.paint = paint;
        this.xVelocity = xVel;
        this.yVelocity = yVel;
    }


    @Override
    public void update() {
        this.rectangle.offset(this.xVelocity, this.yVelocity);
        if (this.rectangle.left <= 0 || this.rectangle.right >= Game.getGameWidth()) {
            xVelocity *= -1;
        }
        if (this.rectangle.top <= 0 || this.rectangle.bottom >= Game.getGameHeight()) {
            yVelocity *= -1;
        }
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawRect(this.rectangle, this.paint);
    }
}
