import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Effect extends Sprite{
    private int duration;
    private Sprite sp;
    public Effect(Image i, double x, double y, int width, int height, int duration) {
        super(i, x, y, width, height, 0, 0);
        sp = this;

        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            int startTime = 0;
            @Override
            public void run() {
                startTime++;
                if(startTime>=duration){
                    MainGame.returnGamePhysics().remove(sp);
                    t.cancel();
                }
            }
        };
        t.scheduleAtFixedRate(tt, 0, 1);
    }

}
