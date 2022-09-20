package tania.kholod.lift;

import java.util.Random;

public class Passenger {

    private int currentFloor;
    private int goalFloor;
    private static Random random = new Random();

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getGoalFloor() {
        return goalFloor;
    }

    public Passenger(int currentFloor) {
        this.currentFloor = currentFloor;
        goalFloor = currentFloor;
        while (goalFloor == currentFloor) {
            goalFloor = random.nextInt(House.NUMBER_FLOORS + 1);
        }
    }
}
