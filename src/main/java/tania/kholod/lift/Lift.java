package tania.kholod.lift;

import java.util.ArrayList;
import java.util.List;

public class Lift {

    public static final int MAX_PASSENGER = 5;
    private List<Passenger> passengers;
    private Floor[] floors;
    private int currentFloor;
    private int goalFloor = 0;

    public Lift(Floor[] floors) {
        this.floors = floors;
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

    public void toFillLift(List<Passenger> passengersFloor) {

        while (passengersFloor.size() > 0 && passengers.size() < MAX_PASSENGER) {
            Passenger passenger = passengersFloor.get(0);
            passengersFloor.remove(passenger);
            passengers.add(passenger);
            if (passenger.getGoalFloor() > goalFloor)
                goalFloor = passenger.getGoalFloor();
        }
    }

    public void start() throws InterruptedException {

        goalFloor = 0;
        Floor floorWithPassengers = chooseFloorWithPassengers(floors);
        if (floorWithPassengers == null)
            throw new NullPointerException();
        if (floorWithPassengers.getPassengersUp().size() >= floorWithPassengers.getPassengersDown().size()) {
            toFillLift(floorWithPassengers.getPassengersUp());
        } else {
            toFillLift(floorWithPassengers.getPassengersDown());
        }
        currentFloor = floorWithPassengers.getNumber();

        String massage = "start floor - " + currentFloor + ", entered - " + passengers.size();
        System.out.println("##################");
        System.out.println(massage);

        if (currentFloor > goalFloor)
            moveLiftDown();
        else
            moveLiftUp();

    }

    private void moveLiftUp() throws InterruptedException {

        int i = currentFloor;
        while (i < House.NUMBER_FLOORS) {
            moveLiftToFloor(floors[i], true);
            i++;
        }

    }

    private void moveLiftDown() throws InterruptedException {

        int i = currentFloor - 2;
        while (i >= 0) {
            moveLiftToFloor(floors[i], false);
            i--;
        }

    }

    private void moveLiftToFloor(Floor floor, boolean moveUp) throws InterruptedException {

        Thread.sleep(1000);
        currentFloor = floor.getNumber();

        String massage = "floor - " + currentFloor + ",  start - " + passengers.size();
        boolean showMassage = false;

        List<Passenger> leavePassenger = new ArrayList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getGoalFloor() == floor.getNumber())
                leavePassenger.add(passenger);
        }
        if (leavePassenger.size() > 0) {
            for (Passenger passenger : leavePassenger) {
                passengers.remove(passenger);
                passenger.changeGoalFloor();
                if (passenger.getCurrentFloor() > passenger.getGoalFloor())
                    floor.getPassengersDown().add(passenger);
                else
                    floor.getPassengersUp().add(passenger);
            }
            massage = massage + ", come out - " + leavePassenger.size();
            showMassage = true;
        }

        int afterLeave = passengers.size();
        if (passengers.size() < Lift.MAX_PASSENGER) {
            if (moveUp)
                toFillLift(floor.getPassengersUp());
            else
                toFillLift(floor.getPassengersDown());
            if (afterLeave != passengers.size()) {
                massage = massage + ", come in - " + (passengers.size() - afterLeave);
                showMassage = true;
            }

        }

        massage = massage + ", result - " + passengers.size();
        if (showMassage)
            System.out.println(massage);

        if (passengers.size() == 0) {
            start();
        }

    }

    private Floor chooseFloorWithPassengers(Floor[] floors) {

        Floor floor = null;
        for (int i = currentFloor - 1; i < House.NUMBER_FLOORS; i++) {
            floor = floors[i];
            if (floor.hasPassengers())
                return floor;
        }
        for (int i = currentFloor - 2; i >= 0; i--) {
            floor = floors[i];
            if (floor.hasPassengers())
                return floor;
        }
        return floor;

    }




}
