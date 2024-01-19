package com.example.inversekinematics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inversekinematics.R;

/**
 * Main menu screen
 *
 * @author Pavel Mikulas
 * @version %I%, %G%
 */
public class MenuActivity extends AppCompatActivity {

    /**
     * Create main menu with a play button
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //Play button
        Button play = findViewById(R.id.playButton);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, GameActivity.class));
            }
        });
    }
}
