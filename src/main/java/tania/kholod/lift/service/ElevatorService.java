package tania.kholod.lift.service;

import tania.kholod.lift.model.Building;
import tania.kholod.lift.model.Elevator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ElevatorService {

    private final RandomGeneratorService randomGeneratorService;

    public ElevatorService() {
        this.randomGeneratorService = new RandomGeneratorService();
    }

    public boolean passengersGetOn(Building building, Elevator elevator) {
        boolean result = false;
        List<Integer> passengersOnFloor = building.getFloors().get(elevator.getCurrentFloor());
        Integer[] passengersInElevator = elevator.getPassengers();
        for (int i = 0; i < passengersInElevator.length; i++) {
            if (passengersInElevator[i] == null) {
                for (int j = 0; j < passengersOnFloor.size(); ) {
                    if (passengersOnFloor.get(j) > elevator.getCurrentFloor() && elevator.getDirection()
                            || passengersOnFloor.get(j) < elevator.getCurrentFloor() && !elevator.getDirection()) {
                        passengersInElevator[i] = passengersOnFloor.remove(j);
                        result = true;
                        break;
                    } else {
                        j++;
                    }
                }
            }
        }
        return result;
    }

    public boolean passengersGetOff(Building building, Elevator elevator) {
        boolean result = false;
        List<Integer> passengersOnFloor = building.getFloors().get(elevator.getCurrentFloor());
        Integer[] passengersInElevator = elevator.getPassengers();
        for (int i = 0; i < passengersInElevator.length; i++) {
            if (passengersInElevator[i] != null && passengersInElevator[i] == elevator.getCurrentFloor()) {
                passengersOnFloor.add(randomGeneratorService.randomDestinationFloor(elevator.getCurrentFloor(), building.getFloors().size() - 1));
                passengersInElevator[i] = null;
                result = true;
            }
        }
        return result;
    }

    public void moveToNextFloor(Building building, Elevator elevator) {
        int nextFloor;
        int currentFloor = elevator.getCurrentFloor();

        if (elevator.getDirection()) {
            nextFloor = currentFloor + 1;
        } else {
            nextFloor = currentFloor - 1;
        }
        elevator.setCurrentFloor(nextFloor);
    }

    public void chooseNextDirection(Building building, Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        int lastFloor = building.getFloors().size() - 1;
        Integer[] passengersOnElevator = elevator.getPassengers();
        List<Integer> passengersOnFloor = building.getFloors().get(currentFloor);

        if (currentFloor == 0) {
            elevator.setDirection(true);
            return;
        }

        if (currentFloor == lastFloor) {
            elevator.setDirection(false);
            return;
        }

        if (Arrays.stream(passengersOnElevator).anyMatch(Objects::nonNull)) {
            return;
        }

        if (passengersOnFloor.size() > 0) {
            long goUp = passengersOnFloor.stream().filter(p -> p > currentFloor).count();
            long goDown = passengersOnFloor.stream().filter(p -> p < currentFloor).count();
            elevator.setDirection(goUp >= goDown);
        }
    }
}
