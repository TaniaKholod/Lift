package tania.kholod.lift;

public class App
{
    public static void main( String[] args ) throws InterruptedException {

        LiftService.toFillFloors();
        LiftService.start();

        //System.out.println("number floors in house - " + LiftService.NUMBER_FLOORS);


    }


}
