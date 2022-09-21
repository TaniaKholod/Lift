package tania.kholod.lift.model;

import java.util.List;

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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPassengersUp(List<Passenger> passengersUp) {
        this.passengersUp = passengersUp;
    }

    public void setPassengersDown(List<Passenger> passengersDown) {
        this.passengersDown = passengersDown;
    }

    public Floor() {
    }
}
