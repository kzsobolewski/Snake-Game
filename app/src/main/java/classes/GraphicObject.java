package classes;

import java.util.ArrayList;
import java.util.List;

public class GraphicObject {
    protected List<Coordinate> coordinatesList = new ArrayList<>();

    public List<Coordinate> getCoordinatesList(){
        return coordinatesList;
    }

}
