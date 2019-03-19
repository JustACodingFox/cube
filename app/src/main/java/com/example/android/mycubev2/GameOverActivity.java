package com.example.android.mycubev2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    private int score;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score = getIntent().getIntExtra(GameActivity.SCORE, 0);

        Button returnButton = findViewById(R.id.return_button);
        Button playAgainButton = findViewById(R.id.play_again_button);
        TextView textView = findViewById(R.id.scoreView);

        textView.setText(Integer.toString(score));

        returnButton.setOnClickListener(this::returnStart);
        playAgainButton.setOnClickListener(this::playAgain);
    }

    public void playAgain(View view) {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void returnStart(View view) {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}
