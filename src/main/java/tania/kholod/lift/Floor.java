package tania.kholod.lift;

import java.util.Random;

public class Floor {

    private int number;
    private Passenger[] passengers;
    private static final int MAX_NUMBER_PASSENGERS = 10;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }

    public static Floor[] toFillFloors () {
        Random r = new Random();
        Floor [] floors = new Floor[House.NUMBER_FLOORS];
        for (int i = 1; i <= House.NUMBER_FLOORS; i++) {
            Floor floor = new Floor();
            floor.setNumber(i);
            int numberPassengers = r.nextInt(MAX_NUMBER_PASSENGERS + 1);
            Passenger[] passengers = new Passenger[numberPassengers];
            floor.setPassengers(passengers);
            if (numberPassengers == 0)
                continue;
            for (int j = 1; j <= numberPassengers; j++) {
                passengers[j] = new Passenger(i);
            }
        }
        return floors;
    }

}
