package tania.kholod.lift;

import tania.kholod.lift.model.Floor;
import tania.kholod.lift.model.Lift;
import tania.kholod.lift.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LiftService {

    private static final int MAX_PASSENGER_ON_LIFT = 5;
    private static final int MAX_PASSENGERS_ON_FLOOR = 10;
    private static final int MIN_FLOORS = 5;
    private static final int MAX_FLOORS = 20;
    private static int NUMBER_FLOORS;
    private static Floor [] FLOORS;
    private static Lift lift = new Lift();
    private static Random random = new Random();

    public static void toFillFloors() {
        NUMBER_FLOORS = random.nextInt(MAX_FLOORS - MIN_FLOORS + 1) + MIN_FLOORS;
        FLOORS = new Floor[NUMBER_FLOORS];
        for (int i = 0; i < NUMBER_FLOORS; i++) {
            FLOORS[i] = createAndFillFloor(i + 1);
        }
        System.out.println("There are " + NUMBER_FLOORS + " floors.");
        System.out.println();
    }

    public static Floor createAndFillFloor(int numberFloor) {

        Floor floor = new Floor();
        floor.setNumber(numberFloor);
        List<Passenger> passengersUp = new ArrayList<>();
        List<Passenger> passengersDown = new ArrayList<>();

        int counter = random.nextInt(MAX_PASSENGERS_ON_FLOOR + 1);
        while (counter > 0) {
            counter--;
            Passenger passenger = new Passenger(numberFloor);
            if (passenger.getGoalFloor() > passenger.getCurrentFloor())
                passengersUp.add(passenger);
            else
                passengersDown.add(passenger);
        }

        floor.setPassengersUp(passengersUp);
        floor.setPassengersDown(passengersDown);

        return floor;

    }

    public static boolean floorHasPassengers(Floor floor) {
        if (floor.getPassengersUp().size() + floor.getPassengersDown().size() > 0)
            return true;
        else
            return false;
    }

    public static int getRandomFloor(int floor) {
        Random random = new Random();
        int newFloor = floor;
        while (newFloor == floor) {
            newFloor = random.nextInt(NUMBER_FLOORS) + 1;
        }
        return newFloor;
    }

    public static Passenger changeGoalFloorPassenger(Passenger passenger) {
        passenger.setCurrentFloor(passenger.getGoalFloor());
        passenger.setGoalFloor(getRandomFloor(passenger.getCurrentFloor()));
        return passenger;
    }

    public static void toFillLift(List<Passenger> passengersOnFloor) {

        List<Passenger> passengersOnLift = lift.getPassengers();
        while (passengersOnFloor.size() > 0 && passengersOnLift.size() < MAX_PASSENGER_ON_LIFT) {
            Passenger passenger = passengersOnFloor.get(0);
            passengersOnFloor.remove(passenger);
            passengersOnLift.add(passenger);
            if (passenger.getGoalFloor() > lift.getGoalFloor())
                lift.setGoalFloor(passenger.getGoalFloor());
        }
    }

    public static void start() throws InterruptedException {

        lift.setGoalFloor(0);
        Floor floor = chooseFloorWithPassengers();
        if (floor == null)
            throw new NullPointerException();
        if (floor.getPassengersUp().size() >= floor.getPassengersDown().size()) {
            toFillLift(floor.getPassengersUp());
        } else {
            toFillLift(floor.getPassengersDown());
        }
        lift.setCurrentFloor(floor.getNumber());

        if (lift.getCurrentFloor() > lift.getGoalFloor())
            moveLiftDown();
        else
            moveLiftUp();

    }

    private static void moveLiftUp() throws InterruptedException {

        int i = lift.getCurrentFloor();
        while (i < NUMBER_FLOORS) {
            moveLiftToFloor(FLOORS[i], true);
            i++;
        }

    }

    private static void moveLiftDown() throws InterruptedException {

        int i = lift.getCurrentFloor() - 2;
        while (i >= 0) {
            moveLiftToFloor(FLOORS[i], false);
            i--;
        }

    }

    private static void moveLiftToFloor(Floor floor, boolean moveUp) throws InterruptedException {

        Thread.sleep(1000);
        lift.setCurrentFloor(floor.getNumber());

        View view = new View();
        view.setFloor(floor.getNumber());
        view.setPassengerInLiftStart(lift.getPassengers().size());

        List<Passenger> leavePassenger = new ArrayList<>();
        for (Passenger passenger : lift.getPassengers()) {
            if (passenger.getGoalFloor() == floor.getNumber())
                leavePassenger.add(passenger);
        }
        if (leavePassenger.size() > 0) {
            for (Passenger passenger : leavePassenger) {
                lift.getPassengers().remove(passenger);
                changeGoalFloorPassenger(passenger);
                if (passenger.getCurrentFloor() > passenger.getGoalFloor())
                    floor.getPassengersDown().add(passenger);
                else
                    floor.getPassengersUp().add(passenger);
            }
            view.setPassengerComeOut(leavePassenger.size());
        }

        if (lift.getPassengers().size() < MAX_PASSENGER_ON_LIFT) {
            int currentNumber = lift.getPassengers().size();
            if (moveUp)
                toFillLift(floor.getPassengersUp());
            else
                toFillLift(floor.getPassengersDown());
            view.setPassengerComeIn(lift.getPassengers().size() - currentNumber);
        }
        view.setPassengerInLiftFinish(lift.getPassengers().size());
        view.print();

        if (lift.getPassengers().size() == 0) {
            start();
        }

    }

    private static Floor chooseFloorWithPassengers() {

        Floor floor = null;
        for (int i = lift.getCurrentFloor() - 1; i < NUMBER_FLOORS; i++) {
            floor = FLOORS[i];
            if (floorHasPassengers(floor))
                return floor;
        }
        for (int i = lift.getCurrentFloor() - 2; i >= 0; i--) {
            floor = FLOORS[i];
            if (floorHasPassengers(floor))
                return floor;
        }
        return floor;

    }



}
