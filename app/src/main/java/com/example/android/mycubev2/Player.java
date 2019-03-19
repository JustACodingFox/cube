package com.example.android.mycubev2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Player extends GameObject {

    public static final int WIDTH = 100;
    private static final int VELOCITY = 5;

    //player input
    private Vector2D firstPress;
    private Vector2D secondPress;
    private Vector2D currentSwipe;
    private float minSwipeLength = 200;
    private float firstSwipeLength = 500;

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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //save began touch 2d point
                firstPress = new Vector2D(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                //save ended touch 2d point
                secondPress = new Vector2D(event.getX(), event.getY());
                //create vector from the two points
                currentSwipe = new Vector2D(secondPress.getX() - firstPress.getX(), secondPress.getY() - firstPress.getY());
                //normalize the 2d vector

                if (currentSwipe.length() < minSwipeLength) {
                    return false;
                }

                if (currentSwipe.length() < firstSwipeLength) {
                    currentSwipe.norm();

                    //swipe upwards
                    if (currentSwipe.getY() > 0 && currentSwipe.getX() > -0.5f && currentSwipe.getX() < 0.5f) {
                        yVelocity = VELOCITY;
                    }
                    //swipe down
                    if (currentSwipe.getY() < 0 && currentSwipe.getX() > -0.5f && currentSwipe.getX() < 0.5f) {
                        yVelocity = -VELOCITY;
                    }
                    //swipe left
                    if (currentSwipe.getX() < 0 && currentSwipe.getY() > -0.5f && currentSwipe.getY() < 0.5f) {
                        xVelocity = -VELOCITY;
                    }
                    //swipe right
                    if (currentSwipe.getX() > 0 && currentSwipe.getY() > -0.5f && currentSwipe.getY() < 0.5f) {
                        xVelocity = VELOCITY;
                    }
                } else {
                    currentSwipe.norm();

                    //swipe upwards
                    if (currentSwipe.getY() > 0 && currentSwipe.getX() > -0.5f && currentSwipe.getX() < 0.5f) {
                        yVelocity = VELOCITY;
                        xVelocity = 0;
                    }
                    //swipe down
                    if (currentSwipe.getY() < 0 && currentSwipe.getX() > -0.5f && currentSwipe.getX() < 0.5f) {
                        yVelocity = -VELOCITY;
                        xVelocity = 0;
                    }
                    //swipe left
                    if (currentSwipe.getX() < 0 && currentSwipe.getY() > -0.5f && currentSwipe.getY() < 0.5f) {
                        xVelocity = -VELOCITY;
                        yVelocity = 0;
                    }
                    //swipe right
                    if (currentSwipe.getX() > 0 && currentSwipe.getY() > -0.5f && currentSwipe.getY() < 0.5f) {
                        xVelocity = VELOCITY;
                        yVelocity = 0;
                    }
                }

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
