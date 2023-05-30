import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class MessageThing {
    private static String[] message;
    private static String messageDisplayed = " ";
    private static double y = 480;
    private static double x1 = 0;
    private static double x2 = 480;
    private static int messageIncrementor;

    private static Sprite p1;
    private static Sprite p2;


    private static boolean isDisplay;

    private static Timer t;

    private static int i;


    public static void setKeyListener(GraphicsWindow g){
        g.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 90){
                    try {
                        if(message!=null && isDisplay){
                            next();
                        }
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void setMessage(String[] msg, Sprite s1, Sprite s2){
        i = 0;
        p1 = s1;
        p2 = s2;
        p1.setY(250);
        p2.setY(250);
        p1.setX(-p1.getWidth());
        p2.setX(480);

        messageIncrementor = 0;
        message = msg;
    }
    public static String getMessage(){
        return messageDisplayed;
    }
    public static double getY(){
        return y;
    }

    public static void start(){
        p1.setTransparency(0.5f);
        p2.setWidth((int)(p2.getWidth()*1.2));
        p2.setHeight((int)(p2.getHeight()*1.2));

        isDisplay = true;
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                y -= 2;
                p1.setX(p1.getX()+1.7);
                p2.setX(p2.getX()-1.7);
                if (y <= 350) {
                    t.cancel();
                    messageDisplayed = message[0];
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
                p1.setX(p1.getX()-1.7);
                p2.setX(p2.getX()+1.7);
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

    public static void next() throws InterruptedException {
        i++;
        if(i>=message.length){
            stop();
            return;
        }
        int p1OldWidth = p1.getWidth();
        int p1OldHeight = p1.getHeight();
        int p2OldWidth = p2.getWidth();
        int p2OldHeight = p2.getHeight();

        if((i+1)%2 == 0){
            Timer t1 = new Timer();
            TimerTask tt1 = new TimerTask() {
                @Override
                public void run() {
                    int incrementer = 1;
                    while(incrementer <= 100){
                        double divisor = 1 + 0.2*incrementer/100;
                        double transparency = 0.5*incrementer/100;
                        p2.setWidth((int)(p2OldWidth/(divisor)));
                        p2.setHeight((int)(p2OldHeight/divisor));
                        p2.setTransparency((float)(1-transparency));

                        p1.setWidth((int)(p1OldWidth*divisor));
                        p1.setHeight((int)(p1OldHeight*divisor));
                        p1.setTransparency((float)(0.5+transparency));
                        incrementer++;
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            t1.schedule(tt1, 0);


        }
        else{
            Timer t1 = new Timer();
            TimerTask tt1 = new TimerTask() {
                @Override
                public void run() {
                    int incrementer = 1;
                    while(incrementer <= 100){
                        double divisor = 1 + 0.2*incrementer/100;
                        double transparency = 0.5*incrementer/100;
                        p1.setWidth((int)(p1OldWidth/(divisor)));
                        p1.setHeight((int)(p1OldHeight/divisor));
                        p1.setTransparency((float)(1-transparency));

                        p2.setWidth((int)(p2OldWidth*divisor));
                        p2.setHeight((int)(p2OldHeight*divisor));
                        p2.setTransparency((float) (0.5+transparency));
                        incrementer++;
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            t1.schedule(tt1, 0);
        }
        messageDisplayed = message[i];
    }
    public static Sprite getP1(){
        return p1;
    }
    public static Sprite getP2(){
        return p2;
    }
}
