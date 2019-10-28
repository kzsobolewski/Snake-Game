package com.example.snake.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import enums.TileType;

public class SnakeView extends View {
    private Paint mPaint = new Paint();
    private TileType snakeViewMap[][];

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setSnakeViewMap(TileType[][] map){
        this.snakeViewMap = map;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(snakeViewMap == null) return;
        float tileSizeX = canvas.getWidth() / snakeViewMap.length;
        float tileSizeY = canvas.getHeight() / snakeViewMap[0].length;
        float circleRadius = Math.min(tileSizeX,tileSizeY) / 2 ;

        for(int i=0; i<snakeViewMap.length; i++){
        for(int j =0; j<snakeViewMap[0].length; j++){
            switch (snakeViewMap[i][j]){
            case Nothing:
                mPaint.setColor(Color.WHITE);
                break;
            case Wall:
                mPaint.setColor(Color.GRAY);
                break;
            case SnakeHead:
                mPaint.setColor(Color.BLUE);
                break;
            case SnakeTail:
                mPaint.setColor(Color.CYAN);
                break;
            case Apple:
                mPaint.setColor(Color.RED);
                break;
            }


            canvas.drawCircle(i * tileSizeX + tileSizeX / 2f + circleRadius /2 ,
                   j * tileSizeY + tileSizeY / 2f + circleRadius / 2, circleRadius, mPaint );
        }
        }



    }
}

