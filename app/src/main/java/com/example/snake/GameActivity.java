package com.example.snake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snake.views.SnakeView;

import java.util.concurrent.TimeUnit;

import engine.GameEngine;
import enums.Direction;
import enums.GameState;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener {

    private GameEngine gameEngine;
    private SnakeView snakeView;
    private float previousX, previousY;
    private int updateDelay = 150;
    private final Handler handler = new Handler();
    private TextView score;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.sconre_number_textview_game);

        Intent intent = getIntent();
        String speed = intent.getStringExtra("speed");
        username = intent.getStringExtra("name");
        String size = intent.getStringExtra("size");

        int width = 30;
        int height =50;

        switch(size){
            case "Small":
                width = 14;
                height = 27;
                break;
            case "Medium":
                width = 20;
                height = 38;
                break;
            case "Big":
                width = 27;
                height = 51;
                break;
        }

        switch (speed){
            case "Slow":
                updateDelay = 200;
                break;
            case "Normal":
                updateDelay = 150;
                break;
            case "Fast":
                updateDelay = 100;
                break;
        }

        gameEngine = new GameEngine(width,height);
        gameEngine.initGame();

        snakeView = findViewById(R.id.snakeView);
        snakeView.setOnTouchListener(this);
        startUpdateHandler();

        }

    private void startUpdateHandler(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.Update();
                if(gameEngine.getCurrentGameState()== GameState.Running){
                    handler.postDelayed(this, updateDelay);
                }
                if( gameEngine.getCurrentGameState() ==  GameState.Lost){
                    OnGameLost();
                }
                snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.invalidate();
                score.setText( gameEngine.getScore().toString());
            }
        }, updateDelay);
    }

    private void OnGameLost(){
        Toast.makeText(this, "You Lost!", Toast.LENGTH_SHORT).show();
        SharedPreferences pref = getSharedPreferences("Score_Table", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("prev_score",gameEngine.getScore());
        editor.putString("prev_name", username);


        String bestPlayer = pref.getString("best_player","Nobody");
        Integer bestScore = pref.getInt("best_score",0);

        if(gameEngine.getScore() > bestScore){
            editor.putInt("best_score",gameEngine.getScore());
            editor.putString("best_name", username);
        }
        editor.apply();
        finish();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                previousX = event.getX();
                previousY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                float newX = event.getX();
                float newY = event.getY();
                if(Math.abs(newX - previousX) > Math.abs(newY - previousY) ){
                    // horizontal swipe
                    if(newX > previousX)
                        gameEngine.UpdateDirection(Direction.Right);
                    else
                        gameEngine.UpdateDirection(Direction.Left);

                }else{
                    // vertical swipe
                    if(newY > previousY)
                        gameEngine.UpdateDirection(Direction.Down);
                    else
                        gameEngine.UpdateDirection(Direction.Up);

                }
                break;
        }
        return true;
    }
}


