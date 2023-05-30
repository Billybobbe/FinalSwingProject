import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SpellCard {
    private static Sprite boss;
    private static String attackName;

    private static float nameTransparency;

    private static float backgroundTransparency;

    private static int nameY;

    private static boolean isPlaying;

    private static final int textSize = 17;

    private static final int bossSize = 300;

    private static double rotation = 0;

    public static void set(Sprite sp, String str) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        boss = sp;
        attackName = str;
        boss.setX(480);
        boss.setY(300-bossSize/2.0);
        nameY = 350;
        boss.setWidth(boss.getWidth()/boss.getHeight()*bossSize);
        boss.setHeight(300);
        play();

    }
    public static void stop(){
        attackName = null;
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                while(backgroundTransparency > 0){
                    backgroundTransparency -= 0.1;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(backgroundTransparency<0){
                    backgroundTransparency = 0;
                }
            }
        };
        t.schedule(tt, 0);
    }

    private static void play() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MainGame.playMusic("res/SpellCard.wav");
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                isPlaying = true;
                while(boss.getX()>480-bossSize){
                    boss.setX(boss.getX()-1);
                    nameTransparency = (float)((Math.abs(boss.getX()-480))/(bossSize));
                    if(backgroundTransparency!=1){
                        backgroundTransparency = (float)((Math.abs(boss.getX()-480))/(bossSize));
                    }
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int oldBossWidth = boss.getWidth();
                int oldBossHeight = boss.getHeight();
                double oldBossX = boss.getX();
                double oldBossY = boss.getY();

                int oldNameY = nameY;

                while(nameY>10){
                    double multiplier = 1 + 3*((oldNameY - nameY) / (double)(oldNameY - 10));
                    float transparency = (float)(1*((oldNameY - nameY) / (double)(oldNameY - 10)));

                    nameY-=1;
                    boss.setHeight((int)(oldBossHeight*multiplier));
                    boss.setWidth((int)(oldBossWidth*multiplier));
                    boss.setX(oldBossX+oldBossWidth/2.0 - boss.getWidth()/2.0);
                    boss.setY(oldBossY+oldBossHeight/2.0 - boss.getHeight()/2.0);
                    boss.setTransparency(1-transparency);

                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                isPlaying = false;

            }
        };
        t.schedule(tt, 0);

    }
    public static int getNameY(){
        return nameY;
    }
    public static float getNameTransparency(){
        return nameTransparency;
    }
    public static Sprite getSprite(){
        return boss;
    }
    public static String getAttackName(){
        return attackName;
    }
    public static boolean isPlaying(){
        return isPlaying;
    }
    public static int getTextSize(){
        return textSize;
    }
    public static float getBackgroundTransparency(){
        return backgroundTransparency;
    }
    public static void updateSpinny(){
        rotation += 0.01;
    }
    public static double getRotation(){
        return rotation;
    }


}
