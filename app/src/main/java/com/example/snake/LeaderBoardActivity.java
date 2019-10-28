package com.example.snake;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        TextView previousScore = findViewById(R.id.prevScore_ldr);
        TextView previousName =  findViewById(R.id.prev_name_ldr);
        TextView bestScore =  findViewById(R.id.best_score_ldr);
        TextView bestName =  findViewById(R.id.best_name_text_leaderboard);

        SharedPreferences pref = getSharedPreferences("Score_Table", MODE_PRIVATE);
        String bestPlayerVal = pref.getString("best_name","Nobody");
        Integer bestScoreVal = pref.getInt("best_score",0);

        String prevPlayerVal = pref.getString("prev_name","Nobody");
        Integer prevScoreVal = pref.getInt("prev_score",0);

        previousScore.setText(prevScoreVal.toString());
        previousName.setText(prevPlayerVal);

        bestName.setText(bestPlayerVal);
        bestScore.setText(bestScoreVal.toString());
    }
}
