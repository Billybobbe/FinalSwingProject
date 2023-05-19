import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;

public class Boss extends Sprite{
    protected boolean moving = false;
    private boolean attack;
    private int attackStatus;
    private double centralTime;
    private double centralHealth;
    private int attack1Health;
    private int attack2Health;
    private int attack3Health;
    private int attack4Health;
    private int attack5Health;
    private int attack1Time;
    private int attack2Time;
    private int attack3Time;
    private int attack4Time;
    private int attack5Time;
    private double targetX;

    private boolean isRunning;
    private Timer t;

    private double targetY;
    private double oldDist = Integer.MAX_VALUE;

    public Boss(Image i, double x, double y, int width, int height, double speedX, double speedY, int attack1Health, int attack1Time, int attack2Health, int attack2Time, int attack3Health, int attack3Time, int attack4Health, int attack4Time, int attack5Health, int attack5Time) {
        super(i, x, y, width, height, speedX, speedY);
        this.attack1Health = attack1Health;
        this.attack1Time = attack1Time;
        this.attack2Health = attack2Health;
        this.attack2Time = attack2Time;
        this.attack3Health = attack3Health;
        this.attack3Time = attack3Time;
        this.attack4Health = attack4Health;
        this.attack4Time = attack4Time;
        this.attack5Health = attack5Health;
        this.attack5Time = attack5Time;
    }


    public Timer attack1() {
        return new Timer();
    }
    public Timer attack2(){
        return new Timer();
    }

    public Timer attack3(){
        return new Timer();
    }

    public Timer attack4(){
        return new Timer();
    }
    public Timer attack5(){
        return new Timer();
    }

    public void move(){
        if(!moving) {
            oldDist = Integer.MAX_VALUE;
            double randx = Math.random() * 320 + 80;
            double randy = Math.random() * 100 + 30;
            double dist = Math.sqrt(Math.pow(randx-getX(),2)+Math.pow(randy-getY(),2));
            while(!(dist>100 && dist<200)){
                randx = Math.random() * 320 + 80;
                randy = Math.random() * 100 + 30;
                dist = Math.sqrt(Math.pow(randx-getX(),2)+Math.pow(randy-getY(),2));
            }
            targetX = randx;
            targetY = randy;
            speedX = targetX - getX();
            speedY = targetY - getY();
            if (Math.abs(Math.max(speedX, speedY)) > 1) { //so it doesn't move too fast
                double divisior = (1 / Math.max(Math.abs(speedX), Math.abs(speedY)));
                speedX *= divisior;
                speedY *= divisior;
            }
            moving = true;
        }
        double distance = Math.sqrt(Math.pow(targetX-getX(),2) + Math.pow(targetY-getY(),2));
            if(distance>oldDist){
                speedY = 0;
                speedX = 0;
                moving = false;
            }
            else if(distance<50){
                double x = 50-distance;
                double y = 2/(x-40);
                if(y<1 && y>0){
                    speedX = speedX/speedX*y;
                    speedY = speedY/speedX*y;
                }
            }
            oldDist = distance;
    }
    public void act() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        runAttackIfNotActive();
        incrementAttackTime();
        MainGame.returnGraphicsWindow().bar.setPercentage(centralHealth/1000);
        MainGame.returnGraphicsWindow().bar.setActive(true);

    }
    public void incrementAttackTime(){
        centralTime -= 1.0/1000/60;
        if(centralTime <= 0 || centralHealth <= 0){
            t.cancel();
            t.purge();
            moving = false;
            speedY = 0;
            speedX = 0;
            isRunning = false;
        }
    }
    public void move(double speed){
        if(!moving) {
            oldDist = Integer.MAX_VALUE;
            double randx = Math.random() * 320 + 80;
            double randy = Math.random() * 100 + 30;
            double dist = Math.sqrt(Math.pow(randx-getX(),2)+Math.pow(randy-getY(),2));
            while(!(dist>100 && dist<200)){
                randx = Math.random() * 320 + 80;
                randy = Math.random() * 100 + 30;
                dist = Math.sqrt(Math.pow(randx-getX(),2)+Math.pow(randy-getY(),2));
            }
            targetX = randx;
            targetY = randy;
            speedX = targetX - getX();
            speedY = targetY - getY();
            if (Math.abs(Math.max(speedX, speedY)) > speed) { //so it doesn't move too fast
                double divisior = (speed / Math.max(Math.abs(speedX), Math.abs(speedY)));
                speedX *= divisior;
                speedY *= divisior;
            }
            moving = true;
        }
        double distance = Math.sqrt(Math.pow(targetX-getX(),2) + Math.pow(targetY-getY(),2));
        System.out.println(speedX + " " + speedY + " " + distance);
        if(distance>oldDist){
            speedY = 0;
            speedX = 0;
            moving = false;
        }
        else if(distance<50){
            double x = 50-distance;
            double y = 2/(x-40);
            if(y<1 && y>0){
                speedX = speedX/speedX*y;
                speedY = speedY/speedX*y;
            }
        }
        oldDist = distance;
    }

    public void runAttackIfNotActive() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(!isRunning) {
            MainGame.returnGamePhysics().projectileSweep();
            if (attackStatus == 0) {
                isRunning = true;
                t = attack1();
                centralHealth = attack1Health;
                centralTime = attack1Time;
            }
            if (attackStatus == 1) {
                isRunning = true;
                t = attack2();
                centralHealth = attack2Health;
                centralTime = attack2Time;
            }
            if (attackStatus == 2) {
                isRunning = true;
                t = attack3();
                centralHealth = attack3Health;
                centralTime = attack3Time;
            }
            if (attackStatus == 3) {
                isRunning = true;
                t = attack4();
                centralHealth = attack4Health;
                centralTime = attack4Time;
            }
            if (attackStatus == 4) {
                isRunning = true;
                attack5();
                centralHealth = attack5Health;
                centralTime = attack5Time;
            }
            if (attackStatus == 5) {
                remove();
            }
            attackStatus++;

        }
    }
    public void decreaseHealth(int num){
        centralHealth = centralHealth - num;
    }
}
