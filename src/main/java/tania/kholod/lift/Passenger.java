package tania.kholod.lift;

import java.util.Random;

public class Passenger {

    private int currentFloor;
    private int goalFloor;

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getGoalFloor() {
        return goalFloor;
    }

    public Passenger(int currentFloor) {
        Random random = new Random();
        this.currentFloor = currentFloor;
        goalFloor = Floor.getRandomFloor(currentFloor);
    }

    public void changeGoalFloor() {
        currentFloor = goalFloor;
        goalFloor = Floor.getRandomFloor(currentFloor);
    }


}
