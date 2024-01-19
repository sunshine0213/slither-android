package com.example.inversekinematics.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.inversekinematics.R;
import com.example.inversekinematics.engine.GameEngine;
import com.example.inversekinematics.enums.GameState;

/**
 * Displays player's score and reset button
 *
 * @author Pavel Mikulas
 * @version %I%, %G%
 */
public class GameOverPopUp extends Activity {

    /**
     * Set the game over message and display player's score and menu and restart buttons
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gameoverpopup);

        getWindow().setLayout(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300,
                        getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500,
                        getResources().getDisplayMetrics())
        );

        // Set game over message
        if (GameEngine.getCurrentState() == GameState.TimedOut) {
            TextView gameOverText = findViewById(R.id.end_message);
            gameOverText.setText(R.string.game_ended);
        } else if (GameEngine.getCurrentState() == GameState.Lost) {
            TextView gameOverText = findViewById(R.id.end_message);
            gameOverText.setText(R.string.game_lost);
        }

        // Get current layout
        FrameLayout layout = findViewById(android.R.id.content);

        // Display score
        TextView score = new TextView(layout.getContext());
        score.setText(String.valueOf(GameEngine.getScore()));
        score.setTextSize(80);
        score.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        score.setTextColor(Color.BLACK);

        // Place score text
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 430, 0, 0);

        // Add score text to layout
        layout.addView(score, params);

        // Restart button
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverPopUp.this, GameActivity.class));
            }
        });

        // Menu button
        Button menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverPopUp.this, MenuActivity.class));
            }
        });
    }
}
