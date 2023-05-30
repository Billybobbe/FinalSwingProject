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
    Timer t;
    public Animation(Sprite sp) throws IOException {
        this.sp = sp;
        this.t = new Timer();

    }
    public void setAnimation(double time, Image[] frames){
        this.time = time;
        this.frames = frames;
    }

    public void clear(){
        t.cancel();
        t.purge();
    }

    public void play(){
        t.cancel();
        t.purge();
        if(frames != null){
            t = new Timer();
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
    }
    public void playReverse() {
        t.cancel();
        t.purge();
        if (frames != null) {
            t = new Timer();
            double waitPerImage = time / frames.length;
            TimerTask tt = new TimerTask() {
                int starttime = 0;

                @Override
                public void run() {
                    sp.setImage(frames[frames.length - starttime - 1]);
                    starttime++;
                    if (starttime >= time / waitPerImage) {
                        t.cancel();
                    }
                }
            };
            t.schedule(tt, 0L, (long) waitPerImage);
        }
    }
}
