package tania.kholod.lift.view;

import tania.kholod.lift.model.Building;
import tania.kholod.lift.model.Elevator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsolePrinter {

    public void printBuildingWithElevator(Building building, Elevator elevator, String action) throws InterruptedException {
        System.out.println(action);
        List<List<Integer>> floors = building.getFloors();
        for (int i = floors.size() - 1; i >= 0; i--) {
            String passengersOnElevator = Arrays.stream(elevator.getPassengers())
                    .map(p -> p == null ? " _" : String.format("%2s", p))
                    .collect(Collectors.joining(" "));
            String passengersOnFloor = floors.get(i).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            if (i == elevator.getCurrentFloor()) {
                String direction = elevator.getDirection() ? "\u2191" : "\u2193";
                System.out.printf("%s Elevator: %s      Floor %d: %s%n", direction, passengersOnElevator, i, passengersOnFloor);
            } else {
                System.out.printf("                                Floor %d: %s%n", i, passengersOnFloor);
            }
        }
        System.out.println("###################################################################");
        Thread.sleep(3000);
    }
}
