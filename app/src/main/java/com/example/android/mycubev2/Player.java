package com.example.android.mycubev2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Player extends GameObject {

    public static final int WIDTH = 100;
    public static final int VELOCITY = 5;

    private int health;

    public Player(int left, int top, int right, int bottom, Paint paint) {
        this.rectangle = new Rect(left, top, right, bottom);
        this.paint = paint;
        this.xVelocity = 0;
        this.yVelocity = 0;
        health = 100;
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

    //handle touch input
    public boolean handleTouchEvent(MotionEvent event) {
        //maybe change input
        if (event.getX() > this.rectangle.centerX()) {
            this.xVelocity = VELOCITY;
        } else {
            this.xVelocity = -VELOCITY;
        }

        if (event.getY() > this.rectangle.centerY()) {
            this.yVelocity = VELOCITY;
        } else {
            this.yVelocity = -VELOCITY;
        }
        return true;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth() {
        health--;
    }
}
