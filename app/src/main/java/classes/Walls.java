package classes;

public class Walls extends GraphicObject {


    public void add(int boardWidth, int boardHeight) {
        for (int i = 0; i < boardWidth; i++) {
            coordinatesList.add(new Coordinate(i, 0));
            coordinatesList.add(new Coordinate(i, boardHeight - 1));
        }
        for (int i = 1; i < boardHeight; i++) {
            coordinatesList.add(new Coordinate(0, i));
            coordinatesList.add(new Coordinate(boardWidth - 1, i));
        }
    }
}
