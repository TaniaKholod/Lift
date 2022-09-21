package tania.kholod.lift;

import java.util.Random;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        Random r = new Random();
        int low = 5;
        int high = 20;
        House.NUMBER_FLOORS = r.nextInt(high - low + 1) + low;

        System.out.println("number floors - " + House.NUMBER_FLOORS);
        Floor[] floors = House.toFillFloors();

        Lift lift = new Lift(floors);
        lift.start();

    }


}
