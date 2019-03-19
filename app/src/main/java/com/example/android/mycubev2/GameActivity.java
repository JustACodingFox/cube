package com.example.android.mycubev2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class GameActivity extends Activity {

    private static final int REQUESTCODE = 2;
    public static final String SCORE = "Score";

    private GameView mGameView;
    private int score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.score = 0;

        mGameView = new GameView(this, this);

        mGameView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mGameView.setZOrderOnTop(true);
        setContentView(mGameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameView.resume();
    }

    public void gameOver(int score) {
        if (score > this.score) {
            this.score = score;
        }
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(SCORE, score);
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE:
                switch (resultCode) {
                    case RESULT_OK:
                        mGameView = new GameView(this, this);
                        mGameView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                        mGameView.setZOrderOnTop(true);
                        setContentView(mGameView);
                        break;
                    case RESULT_CANCELED:
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(SCORE, score);
                        setResult(RESULT_OK, returnIntent);
                        finish();
                }
        }
    }
}
