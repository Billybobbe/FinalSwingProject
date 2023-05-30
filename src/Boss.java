import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;

public class Boss extends Sprite{
    private boolean moving = false;
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

    Animation anim;

    Image[] rightAnim;
    Image[] leftAnim;

    public Boss(Image i, double x, double y, int width, int height, double speedX, double speedY,
                int attack1Health, int attack1Time, int attack2Health, int attack2Time, int attack3Health,
                int attack3Time, int attack4Health, int attack4Time, int attack5Health, int attack5Time,
                Image[] rightAnim, Image[] leftAnim, String bossName) throws IOException {
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
        anim = new Animation(this);
        this.rightAnim = rightAnim;
        this.leftAnim = leftAnim;
        MainGame.returnGraphicsWindow().bar.setActive(true);
        MainGame.returnGraphicsWindow().bar.setBossName(bossName);
        MainGame.returnGamePhysics().projectileSweep();
    }


    public Timer attack1() {
        return new Timer();
    }
    public Timer attack2() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new Timer();
    }

    public Timer attack3(){
        return new Timer();
    }

    public Timer attack4() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new Timer();
    }
    public Timer attack5() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new Timer();
    }

    public void move(){
        move(1);
    }
    public void move(int speed){
        move(speed, -1, -1);
    }
    public void act() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        runAttackIfNotActive();
        incrementAttackTime();


    }
    public void incrementAttackTime() throws InterruptedException {
        centralTime -= 1.0/60;
        if(isRunning){
            MainGame.returnGraphicsWindow().bar.setTimeLeft((int)centralTime);
            MainGame.returnGraphicsWindow().bar.setPercentage(centralHealth/1000);
        }
        if(centralTime <= 0 || centralHealth <= 0){
            if(isRunning){
                if(SpellCard.getAttackName()!=null){
                    SpellCard.stop();
                }
                centralTime = 0;
                t.cancel();
                t.purge();

                isRunning = false;
            }
            if(centralTime<-0.5){ //so it clears without any new projectiles carrying over
                MainGame.returnGamePhysics().projectileSweep();
            }
            if(attackStatus!=5){
                MainGame.returnGraphicsWindow().bar.setPercentage(MainGame.returnGraphicsWindow().bar.getPercentage()+0.01);
            }
        }
    }
    public void move(double speed, double tarX, double tarY){
        if(!moving) {
            oldDist = Integer.MAX_VALUE;
            if(tarX == -1){
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
            }
            else{
                targetX = tarX;
                targetY = tarY;
            }
            double potentialSpeedX = targetX - getX();
            double potentialSpeedY = targetY - getY();
            if (Math.max(Math.abs(potentialSpeedX), Math.abs(potentialSpeedY)) > speed) { //so it doesn't move too fast
                double divisior = (speed / Math.max(Math.abs(potentialSpeedX), Math.abs(potentialSpeedY)));
                setSpeedX(potentialSpeedX*divisior);
                setSpeedY(potentialSpeedY*divisior);
            }
            else{
                setSpeedX(potentialSpeedX);
                setSpeedY(potentialSpeedY);
            }
            if(getSpeedX()<0){
                anim.setAnimation(200, leftAnim);
            }
            else{
                anim.setAnimation(200, rightAnim);
            }
            anim.play();
            moving = true;
        }
        double distance = Math.sqrt(Math.pow(targetX-getX(),2) + Math.pow(targetY-getY(),2));
        if(distance>oldDist || getSpeedX() == 0){
            setSpeedX(0);
            setSpeedY(0);
            anim.playReverse();
            moving = false;
        }
        else if(distance<50){
            double x = 50-distance;
            double y = 2/(x-40);
            if(y<1 && y>0){
                setSpeedX(y);
                setSpeedY(getSpeedY()/getSpeedX()*y);
            }
        }
        oldDist = distance;
    }

    public void runAttackIfNotActive() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(!isRunning && (MainGame.returnGraphicsWindow().bar.getPercentage() >= 0.99 || attackStatus==5)) {
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
                t = attack5();
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

    @Override
    public void remove() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MainGame.returnGamePhysics().projectileSweep();
        MainGame.returnGraphicsWindow().bar.setActive(false);

        for(int i = 0; i<500; i++){
            MainGame.returnGamePhysics().addSprite(new Effect(Resource.DEFAULT_ENEMY_DEATH, getX()+getWidth()/2.0-25, getY()+getHeight()/2.0-25, 50, 50, Math.random()*4-2, Math.random()*4-2, 1000, 700));
        }

        MainGame.returnGamePhysics().remove(this);
        t.cancel();
        t.purge();
        moving = false;
    }
    public boolean isAttackRunning(){
        return isRunning;
    }
    public boolean isMoving(){
        return moving;
    }
}
