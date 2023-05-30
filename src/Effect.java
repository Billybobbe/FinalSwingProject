import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Effect extends Sprite{
    private final int duration;
    private int startTime = 0;
    private int fadeStart;


    public Effect(Image i, double x, double y, int width, int height, double speedX, double speedY, int duration, int fadeStart) {
        super(i, x, y, width, height, speedX, speedY);
        this.duration = duration;
        this.fadeStart = fadeStart;
    }

    @Override
    public void act(){

        if(startTime >= fadeStart){
            setTransparency((float)(1-(startTime-fadeStart)/(double)(duration-fadeStart)));
        }
        startTime++;
        if(startTime>=duration){
            MainGame.returnGamePhysics().remove(this);
        }
    }
}
