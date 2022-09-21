package tania.kholod.lift;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Floor {

    private int number;
    private List<Passenger> passengersUp;
    private List<Passenger> passengersDown;
    private static final int MAX_NUMBER_PASSENGERS = 10;

    public int getNumber() {
        return number;
    }

    public List<Passenger> getPassengersUp() {
        return passengersUp;
    }

    public List<Passenger> getPassengersDown() {
        return passengersDown;
    }

    public Floor(int number) {

        this.number = number;
        passengersUp = new ArrayList<>();
        passengersDown = new ArrayList<>();

        Random r = new Random();
        int counter = r.nextInt(MAX_NUMBER_PASSENGERS + 1);
        while (counter > 0) {
            counter--;
            Passenger passenger = new Passenger(number);
            if (passenger.getGoalFloor() > passenger.getCurrentFloor())
                passengersUp.add(passenger);
            else
                passengersDown.add(passenger);
        }

    }

    public boolean hasPassengers() {
        if (passengersUp.size() + passengersDown.size() > 0)
            return true;
        else
            return false;
    }

    public static int getRandomFloor(int currentFloor) {
        Random random = new Random();
        int goalFloor = currentFloor;
        while (goalFloor == currentFloor) {
            goalFloor = random.nextInt(House.NUMBER_FLOORS) + 1;
        }
        return goalFloor;
    }


}
