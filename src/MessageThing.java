import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class MessageThing {
    private static String message;
    private static String messageDisplayed = " ";
    private static double y = 480;
    private static boolean isDisplay;

    private static Timer t;

    public static void setKeyListener(GraphicsWindow g){
        g.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                t.cancel();
                t.purge();
                stop();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 90){
                    stop();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void setMessage(String msg){
        message = msg;
    }
    public static String getMessage(){
        return messageDisplayed;
    }
    public static double getY(){
        return y;
    }
    public static void start(){

        isDisplay = true;
        t = new Timer();
        TimerTask tt = new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                y -= 2;
                if(y<=350){
                    messageDisplayed += message.substring(i, i+1);
                    if(i>=message.length()-1){
                        t.cancel();
                    }
                    i++;
                }
            }
        };
        t.scheduleAtFixedRate(tt, 0, 8);
    }
    public static void stop() {
        messageDisplayed = " ";
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                y += 2;
                if (y >= 480) {
                    isDisplay = false;
                    t.cancel();
                }
            }
        };
        t.scheduleAtFixedRate(tt, 0, 8);
    }
    public static boolean isDisplay(){
        return isDisplay;
    }
}
