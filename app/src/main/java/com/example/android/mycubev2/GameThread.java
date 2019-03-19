package com.example.android.mycubev2;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    public static Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private int targetFPS = 60;
    private double averageFPS;
    private Game game;

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

        Rect world = new Rect(0, 0, gameView.getScreenWidth(), gameView.getScreenHeight());
        game = new Game(world, this);
    }

    @Override
    public void run() {

        Log.i("GameThread", "run Method");

        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / targetFPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    game.update();
                    game.render(canvas);
                }
            } catch (Exception e) {
                Log.e("GameThread", "Exception ", e);
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                sleep(waitTime);
            } catch (Exception e) {
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == targetFPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                Log.i("FPS", Double.toString(averageFPS));
            }
        }

    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }

    public boolean touchEvent(MotionEvent event) {
        return game.getPlayer().handleTouchEvent(event);
    }

    public void playerDeath(int score) {
        gameView.getActivity().gameOver(score);
    }

}


