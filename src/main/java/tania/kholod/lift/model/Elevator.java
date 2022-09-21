package tania.kholod.lift.model;

public class Elevator {

    private static final int MAX_PASSENGERS = 5;

    private int currentFloor;
    // true == up, false == down
    private boolean direction;
    private Integer[] passengers;

    public Elevator() {
        currentFloor = 0;
        direction = true;
        passengers = new Integer[MAX_PASSENGERS];
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public Integer[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer[] passengers) {
        this.passengers = passengers;
    }
}
