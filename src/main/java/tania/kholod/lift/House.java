package tania.kholod.lift;

import java.util.Random;

public class House {

    public static int NUMBER_FLOORS;

    public static Floor[] toFillFloors () {
        Floor [] floors = new Floor[NUMBER_FLOORS];
        for (int i = 0; i < NUMBER_FLOORS; i++) {
            floors[i] = new Floor(i + 1);
        }
        return floors;
    }

}
