package com.example.snake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    private Spinner speedSpinner;
    private Spinner sizeSpinner;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        speedSpinner = findViewById(R.id.game_speed_spinner_menu);
        sizeSpinner = findViewById(R.id.board_size_spinner_menu);
        name = findViewById(R.id.name_edit_text_menu);

        exitGame();
        startGame();
        leaderBoard();
    }

    private void leaderBoard(){
        Button leaderButton = findViewById(R.id.leader_board_button_menu);
        leaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LeaderBoardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void exitGame(){
        Button exitButton = findViewById(R.id.exit_button_menu);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    private void startGame(){
        Button startButton =  findViewById(R.id.start_button_menu);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedSpeed = speedSpinner.getSelectedItem().toString();
                String selectedSize = sizeSpinner.getSelectedItem().toString() ;
                String selectedName = name.getText().toString();

                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                Log.d("start", "selected speed:" + selectedSpeed);
                Log.d("start", "selected size:" + selectedSize);
                Log.d("start", "selected name:" + selectedName);
                intent.putExtra("speed",selectedSpeed);
                intent.putExtra("size", selectedSize);
                intent.putExtra("name", selectedName);

                startActivity(intent);
            }
        });

    }
}
