package tania.kholod.lift.model;

import tania.kholod.lift.LiftService;

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

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setGoalFloor(int goalFloor) {
        this.goalFloor = goalFloor;
    }

    public Passenger(int currentFloor) {
        Random random = new Random();
        this.currentFloor = currentFloor;
        goalFloor = LiftService.getRandomFloor(currentFloor);
    }

    public Passenger() {
    }

}
