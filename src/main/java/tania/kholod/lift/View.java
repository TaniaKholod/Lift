package tania.kholod.lift;

public class View {

    private int floor;
    private int passengerInLiftStart;
    private int passengerComeOut;
    private int passengerComeIn;
    private int passengerInLiftFinish;

    public View() {
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setPassengerInLiftStart(int passengerInLift) {
        this.passengerInLiftStart = passengerInLift;
    }

    public void setPassengerComeOut(int passengerComeOut) {
        this.passengerComeOut = passengerComeOut;
    }

    public void setPassengerComeIn(int passengerComeIn) {
        this.passengerComeIn = passengerComeIn;
    }

    public void setPassengerInLiftFinish(int passengerInLiftFinish) {
        this.passengerInLiftFinish = passengerInLiftFinish;
    }

    public void print() {

        if (passengerComeOut == 0 && passengerComeIn == 0)
            return;

        StringBuilder massage = new StringBuilder();
        massage.append("floor - ");
        massage.append(floor);
        massage.append(", start - ");
        massage.append(passengerInLiftStart);
        massage.append(", come out - " );
        massage.append(passengerComeOut);
        massage.append(", come in - ");
        massage.append(passengerComeIn);
        massage.append(", final - ");
        massage.append(passengerInLiftFinish);

        System.out.println(massage.toString());
    }
}
