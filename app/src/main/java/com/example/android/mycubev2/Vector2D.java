package com.example.android.mycubev2;

public class Vector2D {
    private float x;
    private float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D toAdd) {
        this.x += toAdd.x;
        this.y += toAdd.y;
    }

    public void sub(Vector2D toAdd) {
        this.x -= toAdd.x;
        this.y -= toAdd.y;
    }

    public void scale(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void norm() {
        float length = this.length();
        this.x = (this.x / length);
        this.y = (this.y / length);
    }

    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
