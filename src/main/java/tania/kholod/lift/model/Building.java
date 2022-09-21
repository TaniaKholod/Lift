package tania.kholod.lift.model;

import java.util.ArrayList;
import java.util.List;

public class Building {

    // List of floors. On every floor there is a list of passengers (maybe empty).
    // Every passenger wants to go to some other floor (integer number).
    private List<List<Integer>> floors;

    public List<List<Integer>> getFloors() {
        return floors;
    }

    public void setFloors(List<List<Integer>> floors) {
        this.floors = floors;
    }
}
