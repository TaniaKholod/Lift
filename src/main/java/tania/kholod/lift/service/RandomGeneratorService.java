package tania.kholod.lift.service;

public class RandomGeneratorService {

    public int randomDestinationFloor(int currentFloor, int lastFloor) {
        while (true) {
            int destinationFloor = generateRandomNumber(0, lastFloor);
            if (destinationFloor != currentFloor) {
                return destinationFloor;
            }
        }
    }

    public int randomNumber(int minNumber, int maxNumber) {
        return generateRandomNumber(minNumber, maxNumber);
    }

    // Generate random integer from min (including) to max (including)
    private int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
