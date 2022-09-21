package tania.kholod.lift;

import tania.kholod.lift.model.Building;
import tania.kholod.lift.model.Elevator;
import tania.kholod.lift.service.BuildingService;
import tania.kholod.lift.service.ElevatorService;
import tania.kholod.lift.view.ConsolePrinter;

public class App {

    private final BuildingService buildingService;
    private final ConsolePrinter consolePrinter;
    private final ElevatorService elevatorService;
    private final Building building;
    private final Elevator elevator;

    public App() {
        this.buildingService = new BuildingService();
        this.consolePrinter = new ConsolePrinter();
        this.elevatorService = new ElevatorService();
        this.building = buildingService.generateBuilding();
        this.elevator = new Elevator();
    }

    public static void main(String[] args) throws InterruptedException {
        /*Random r = new Random();
        int low = 5;
        int high = 20;
        House.NUMBER_FLOORS = r.nextInt(high - low + 1) + low;

        System.out.println("number floors - " + House.NUMBER_FLOORS);
        Floor[] floors = House.toFillFloors();

        Lift lift = new Lift(floors);
        lift.start();*/

        App app = new App();
        while (true) {
            app.elevatorLoop();
        }
    }

    private void elevatorLoop() throws InterruptedException {
        consolePrinter.printBuildingWithElevator(building, elevator, "NEW FLOOR");

        if (elevatorService.passengersGetOff(building, elevator)) {
            consolePrinter.printBuildingWithElevator(building, elevator, "PASSENGERS GET OFF");
        }

        elevatorService.chooseNextDirection(building, elevator);

        if (elevatorService.passengersGetOn(building, elevator)) {
            consolePrinter.printBuildingWithElevator(building, elevator, "PASSENGERS GET ON");
        }
        
        elevatorService.moveToNextFloor(building, elevator);
    }
}
