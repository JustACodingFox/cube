package com.example.android.mycubev2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "GamePrefs";
    private static final String SCORE_KEY = "Score";
    private static final int REQUESTCODE = 1;
    private SharedPreferences prefs;
    private TextView scoreNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = findViewById(R.id.start_button);
        imageButton.setOnClickListener(this::startGame);

        scoreNumber = findViewById(R.id.scoreNumber);

        //falls score in den SharedPreferences gespeichert wurde setze den aktuellen Score
        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String scoreString = prefs.getString(SCORE_KEY, "0");

        //falls score im Bundle gespeichert wurde setze den aktuellen score
        if (savedInstanceState != null) {
            scoreString = savedInstanceState.getString(SCORE_KEY);
        }
        scoreNumber.setText(scoreString);
    }

    private void startGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivityForResult(intent, REQUESTCODE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUESTCODE:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data != null) {
                            int score = data.getIntExtra(GameActivity.SCORE, 0);

                            //wenn der neue score der neue highscore ist aktualisere highscore
                            if (score > Integer.valueOf(this.scoreNumber.getText().toString())) {
                                scoreNumber.setText(Integer.toString(score));
                                //speicher den score in sharedpreferences auch wenn die app geschlossen wird und danach neu geöffnet wird
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString(SCORE_KEY, Integer.toString(score));
                                editor.apply();
                            }
                        }

                }
        }
    }

    //speicher den score wenn die activity zerstört wird, z.B beim drehen
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SCORE_KEY, scoreNumber.getText().toString());
    }
}
