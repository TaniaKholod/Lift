package tania.kholod.lift.model;

import java.util.ArrayList;
import java.util.List;

public class Lift {

    private List<Passenger> passengers;
    private int currentFloor;
    private int goalFloor;

    public Lift() {
        passengers = new ArrayList<>();
        currentFloor = 1;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getGoalFloor() {
        return goalFloor;
    }

    public void setGoalFloor(int goalFloor) {
        this.goalFloor = goalFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

}
