package tania.kholod.lift;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Random r = new Random();
        int low = 5;
        int high = 20;
        House.NUMBER_FLOORS = r.nextInt(high - low + 1) + low;


    }
}
