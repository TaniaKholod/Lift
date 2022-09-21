package tania.kholod.lift.service;

import tania.kholod.lift.model.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingService {

    private static final int MAX_FLOOR_NUMBER = 20;
    private static final int MIN_FLOOR_NUMBER = 5;
    private static final int MIN_PASSENGER_ON_FLOOR_NUMBER = 0;
    private static final int MAX_PASSENGER_ON_FLOOR_NUMBER = 10;

    private final RandomGeneratorService randomGeneratorService;

    public BuildingService() {
        randomGeneratorService = new RandomGeneratorService();
    }

    public Building generateBuilding() {
        int floorNumber = randomGeneratorService.randomNumber(MIN_FLOOR_NUMBER, MAX_FLOOR_NUMBER);
        List<List<Integer>> floors = new ArrayList<>();
        for (int i = 0; i < floorNumber; i++) {
            int passengerNumber = randomGeneratorService.randomNumber(MIN_PASSENGER_ON_FLOOR_NUMBER, MAX_PASSENGER_ON_FLOOR_NUMBER);
            List<Integer> passengers = new ArrayList<>();
            for (int j = 0; j < passengerNumber; j++) {
                passengers.add(randomGeneratorService.randomDestinationFloor(i, floorNumber - 1));
            }
            floors.add(passengers);
        }
        Building building = new Building();
        building.setFloors(floors);
        return building;
    }
}
