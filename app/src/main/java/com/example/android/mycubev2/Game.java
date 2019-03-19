package com.example.android.mycubev2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static int GAME_HEIGHT;
    private static int GAME_WIDTH;

    public List<GameObject> mGameObjects;
    private Player player;
    private GameThread thread;
    private UIElements uiElements;
    private Random random;
    private int score;
    private int lastEnemySpawn;

    public Game(Rect world, GameThread thread) {
        GAME_HEIGHT = world.bottom;
        GAME_WIDTH = world.right;

        this.thread = thread;

        //liste mit game objekten
        mGameObjects = new ArrayList<>();

        //random Generator
        random = new Random();

        //ui elements
        uiElements = new UIElements();

        //am beginn des spiels wurden noch nie gegner gespawnt
        lastEnemySpawn = -1;

        //spieler kreiren
        int playerX = world.centerX();
        int playerY = world.centerY();
        player = new Player(playerX - Player.WIDTH / 2, playerY - Player.WIDTH / 2, playerX + Player.WIDTH / 2, playerY + Player.WIDTH / 2, GamePaints.PLAYER_PAINT);

        //setPaints
        GamePaints.setPaints();
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public void update() {
        score++;
        //spawn enemys
        addEnemy();

        //update player
        player.update();
        //update enemys
        for (GameObject gameObject : mGameObjects) {
            gameObject.update();

            if (gameObject.rectangle.intersects(player.rectangle.left, player.rectangle.top, player.rectangle.right, player.rectangle.bottom)) {
                player.reduceHealth();

                if (player.getHealth() == 0) {
                    //player died
                    thread.playerDeath(score);
                }
            }

        }
        //update ui elements
        uiElements.update(score, player.getHealth());
    }

    public void render(Canvas canvas) {
        //black background
        canvas.drawColor(Color.BLACK);

        //render enemys
        for (GameObject gameObject : mGameObjects) {
            gameObject.render(canvas);
        }

        //render player
        player.render(canvas);

        //render ui elements
        uiElements.render(canvas);
    }

    private void addEnemy() {
        int seconds = score / 60;

        //wenn seconds eine primzahl ist add gegner
        if (Utility.isPrime(seconds) && seconds != lastEnemySpawn) {
            lastEnemySpawn = seconds;

            int left = 0;
            int top = 0;

            int offset = 2;

            int side = random.nextInt(4);
            //0 left
            //1 top
            //2 right
            //3 bottom
            switch (side) {
                case 0:
                    top = random.nextInt(Game.GAME_HEIGHT - BasicEnemy.WIDTH);
                    left += offset;
                    break;
                case 1:
                    left = random.nextInt(GAME_WIDTH - BasicEnemy.WIDTH);
                    top += 2;
                    break;
                case 2:
                    top = random.nextInt(Game.GAME_HEIGHT);
                    left = GAME_WIDTH - BasicEnemy.WIDTH;
                    left -= offset;
                    break;
                case 3:
                    top = GAME_HEIGHT - BasicEnemy.WIDTH;
                    left = random.nextInt(GAME_WIDTH - BasicEnemy.WIDTH);
                    top -= offset;
                    break;
            }

            Rect rect = new Rect(left, top, left + BasicEnemy.WIDTH, top + BasicEnemy.WIDTH);
            BasicEnemy enemy = new BasicEnemy(rect, GamePaints.BASIC_ENEMY_PAINT, BasicEnemy.VELOCITY, BasicEnemy.VELOCITY);
            mGameObjects.add(enemy);
        }
    }

    public Player getPlayer() {
        return player;
    }
}
