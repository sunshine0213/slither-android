package com.example.inversekinematics.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inversekinematics.R;
import com.example.inversekinematics.engine.GameEngine;
import com.example.inversekinematics.enums.GameState;
import com.example.inversekinematics.views.MainView;

/**
 * Main game loop activity, handles user input
 * <p>
 * Will end after a set period of time and display the GameOverPopUp
 *
 * @author Pavel Mikulas
 * @version %I%, %G%
 */
public class GameActivity extends AppCompatActivity implements View.OnTouchListener {
    private final Handler handler = new Handler();
    /**
     * Frame rate
     */
    private final int UPDATE_DELAY = 50;
    /**
     * Reference to the game engine
     */
    private GameEngine gameEngine;
    /**
     * Reference to the main view
     */
    private MainView view;

    /**
     * Start the game main activity and initialize the game engine and touch listener
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.MainView);

        gameEngine = new GameEngine();
        gameEngine.init();

        view.setOnTouchListener(this);

        startUpdateHandler();
    }

    /**
     * Main game loop, updates the game engine while the game is running and starts game over activity
     * when the game is over
     */
    private void startUpdateHandler() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.update();

                if (GameEngine.getCurrentState() == GameState.Running) {
                    handler.postDelayed(this, UPDATE_DELAY);
                } else if (GameEngine.getCurrentState() == GameState.Lost) {
                    onGameEnded();
                } else if (GameEngine.getCurrentState() == GameState.TimedOut) {
                    onGameEnded();
                }

                view.setView(gameEngine.getPlayerSnake(), gameEngine.getEnemySnakes(), gameEngine.getFood());
                view.invalidate();
            }
        }, UPDATE_DELAY);
    }

    /**
     * User input handle
     */
    @Override
    public boolean onTouch(View view, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gameEngine.calculateFollowPoint(e.getX(), e.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                gameEngine.calculateFollowPoint(e.getX(), e.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * Start the GameOverPopUp activity after the game is over and end the game
     */
    private void onGameEnded() {
        startActivity(new Intent(GameActivity.this, GameOverPopUp.class));
    }
}
