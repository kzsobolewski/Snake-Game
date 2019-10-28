package engine;
import android.content.SharedPreferences;
import android.widget.TextView;

import classes.Apple;
import classes.Coordinate;
import classes.Snake;
import classes.Walls;
import enums.Direction;
import enums.GameState;
import enums.TileType;

import static android.content.Context.MODE_PRIVATE;

public class GameEngine {
    private int gameWidth;
    private int gameHeight;
    private Integer score = 0;

    private Snake snake;
    private Walls walls;
    private Apple apples;
    private GameState currentGameState = GameState.Running;

    public Integer getScore(){
        return score;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public GameEngine(int width, int height){
        gameHeight = height;
        gameWidth = width;
        snake = new Snake();
        walls = new Walls();
        apples = new Apple();
    }
    public void initGame(){
        walls.add(gameWidth, gameHeight);
        snake.add();
        apples.add(gameWidth, gameHeight, snake);
    }

    public TileType[][] getMap(){
        TileType[][] map = new TileType[gameWidth][gameHeight];

        for(int i = 1; i< gameWidth -1 ; i++)
            for(int j = 1; j< gameHeight -1 ; j++)
                map[i][j] = TileType.Nothing;

        for(Coordinate wall: walls.getCoordinatesList())
            map[wall.getX()][wall.getY()] = TileType.Wall;

        for(Coordinate s : snake.getCoordinatesList())
            map[ s.getX() ][ s.getY() ] = TileType.SnakeTail;

        for(Coordinate a : apples.getCoordinatesList())
            map[ a.getX()][a.getY()] = TileType.Apple;

        map[ snake.getSnakeHead().getX() ][ snake.getSnakeHead().getY() ] = TileType.SnakeHead;

        return map;
    }

    public void UpdateDirection(Direction direction){
        snake.updateDirection(direction);
    }

    public  void Lose(){
        currentGameState = GameState.Lost;
    }

    public void Update(){
        if(currentGameState == GameState.Lost) return;
        switch (snake.getCurrentDurection()) {
            case Up:
                snake.update(0, -1);
                break;
            case Right:
                snake.update(1, 0);
                break;
            case Down:
                snake.update(0, 1);
                break;
            case Left:
                snake.update(-1, 0);
                break;
        }

        // Walls collision
        for( Coordinate w : walls.getCoordinatesList()){
            if(snake.getSnakeHead().equals(w)){
                Lose();
            }
        }

        // self collision
        for( int i = 1 ; i < snake.getCoordinatesList().size(); i++) {
            if(snake.getSnakeHead().equals(snake.getCoordinatesList().get(i))){
                Lose();
                return;
            }
        }
        // collecting apples
        Coordinate collectedApple = null;
        for( Coordinate a : apples.getCoordinatesList() ){
            if( snake.getSnakeHead().equals(a) ) {
                collectedApple = a;
                snake.setincreaseTail(true);
            }
        }
        if(collectedApple != null){
            apples.getCoordinatesList().remove(collectedApple);
            score++;

            apples.add(gameWidth, gameHeight, snake);
        }
    }


}
