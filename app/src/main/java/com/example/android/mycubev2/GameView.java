package com.example.android.mycubev2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private GameActivity activity;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public GameView(Context context, GameActivity activity) {
        super(context);

        this.activity = activity;

        //neuer thread
        thread = new GameThread(getHolder(), this);

        setFocusable(true);
        getHolder().addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        resume();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        pause();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        return thread.touchEvent(event);
    }

    public void resume() {
        if (!thread.isAlive()) {
            thread.setRunning(true);
            thread.start();
        }
    }

    public void pause() {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public GameActivity getActivity() {
        return activity;
    }
}
