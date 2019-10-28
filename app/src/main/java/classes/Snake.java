package classes;

import java.util.ArrayList;
import java.util.List;

import enums.Direction;

public class Snake extends GraphicObject {
    private Direction currentDurection;
    private boolean increaseTail;

    public Snake() {
        currentDurection = Direction.Down;
        increaseTail = false;
    }

    public Direction getCurrentDurection(){
        return currentDurection;
    }

    public Coordinate getSnakeHead() {
        return coordinatesList.get(0);
    }

    public void add() {
            coordinatesList.clear();

        coordinatesList.add(new Coordinate(7,7));
        coordinatesList.add(new Coordinate(6,7));
        coordinatesList.add(new Coordinate(5,7));
    }

    public void update(int x, int y) {
        int newX = coordinatesList.get(coordinatesList.size() - 1).getX();
        int newY = coordinatesList.get(coordinatesList.size() - 1).getY();

        for (int i = coordinatesList.size() - 1; i > 0; i--) {
            coordinatesList.get(i).setX(coordinatesList.get(i - 1).getX());
            coordinatesList.get(i).setY(coordinatesList.get(i - 1).getY());
        }

        if (increaseTail) {
            coordinatesList.add(new Coordinate(newX, newY));
            increaseTail = false;
        }

        coordinatesList.get(0).setX(coordinatesList.get(0).getX() + x);
        coordinatesList.get(0).setY(coordinatesList.get(0).getY() + y);
    }

    public void updateDirection(Direction direction) {
        if (Math.abs(direction.ordinal() - currentDurection.ordinal()) % 2 == 1)
            currentDurection = direction;
    }

    public void setincreaseTail(boolean value){
        increaseTail = value;
    }
}