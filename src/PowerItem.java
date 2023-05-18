import java.awt.*;
import java.io.IOException;

public class PowerItem extends Sprite{
    private int timeExisting;
    private final int power;

    public PowerItem(double x, double y, int power) {
        super(Resource.POWERITEM, x, y, 15, 15, 0, 0);
        timeExisting = 0;
        this.power = power;
    }
    public PowerItem(double x, double y, int width, int height, int power) {
        super(Resource.POWERITEM, x, y, width, height, 0, 0);
        timeExisting = 0;
        this.power = power;
    }

    @Override
    public void act() throws IOException {
        timeExisting++;
        this.speedY = -1 + Math.pow(this.timeExisting, 2)/2000;
        if(speedY>2){
            speedY = 2;
        }
    }
    public int returnPower(){
        return power;
    }
}
