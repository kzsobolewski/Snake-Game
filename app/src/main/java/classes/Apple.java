package classes;

import java.util.Random;

public class Apple extends GraphicObject {
    private Random random = new Random();

    public void add(int boardWidth, int boardHeight, Snake snake){
        Coordinate coordinate = null;
        boolean added = false;
        while( !added ){
            int x = 1 + random.nextInt( boardWidth - 2 );
            int y = 1 + random.nextInt( boardHeight - 2 );

            coordinate = new Coordinate(x, y);
            boolean collision = false;
            for(Coordinate s : snake.coordinatesList){
                if(s.equals(coordinate)){
                    collision = true;
                    break;
                }
            }
            if(collision) continue;
            for( Coordinate a : coordinatesList){
                if(a.equals(coordinate)) {
                    collision = true;
                    break;
                }
            }
            added = !collision;
        }
        coordinatesList.add(coordinate);
    }
}
