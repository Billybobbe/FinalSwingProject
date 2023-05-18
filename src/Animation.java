import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Animation {
    double time;
    Image[] frames;
    Sprite sp;
    public Animation(double time, Image[] f, Sprite sp) throws IOException {
        this.time = time; // in ms
        this.sp = sp;
        this.frames = f;

    }

    public void play(){

        Timer t = new Timer();
        double waitPerImage = time/frames.length;
        TimerTask tt = new TimerTask() {
            int starttime = 0;
            @Override
            public void run() {
                sp.setImage(frames[starttime]);
                starttime++;
                if(starttime>=time/waitPerImage){
                    t.cancel();
                }
            }
        };
        t.schedule(tt, 0L, (long) waitPerImage);
    }
    public void playReverse(){
        Timer t = new Timer();
        double waitPerImage = time/frames.length;
        TimerTask tt = new TimerTask() {
            int starttime = 0;
            @Override
            public void run() {
                sp.setImage(frames[frames.length-starttime-1]);
                starttime++;
                if(starttime>=time/waitPerImage){
                    t.cancel();
                }
            }
        };
        t.schedule(tt, 0L, (long) waitPerImage);
    }
}
