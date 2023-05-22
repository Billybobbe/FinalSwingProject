import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Ethan extends Boss{
    private Animation anim;

    public Ethan() throws IOException {
        super(Resource.ETHAN_IDLE, 200, -100, 60, 70, 0, 1, 1000, 40,
                1000, 30, 1000, 40, 1000, 50, 1000, 70, Resource.ethanAnimRight, Resource.ethanAnimLeft);
        anim = new Animation(this);
        anim.setAnimation(100, Resource.ethanAttackAnim);
    }

    @Override
    public Timer attack1() {
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                try {
                    attack1Cycle();
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };
        t.scheduleAtFixedRate(tt, 0, 1);
        return t;
    }

    @Override
    public Timer attack2() {
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                try {
                    attack2Cycle();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t.scheduleAtFixedRate(tt, 0, 1);
        return t;
    }
    public Timer attack3(){
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                try {
                    attack3Cycle();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t.scheduleAtFixedRate(tt, 0, 1);
        return t;
    }
    public void attack1Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        anim.play();
        Thread.sleep(100);
        attack(10, 5);
        Thread.sleep(500);
        attack(10, 5);
        Thread.sleep(1000);
        attack(25, 1);
        anim.playReverse();
        Thread.sleep(300);
        move();
        System.out.println(moving);
        while(moving){
            move();
            Thread.sleep((long)16.66666);
        }
        Thread.sleep(1000);
    }
    public void attack2Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Thread.sleep(1000);
        anim.play();
        phoneAttack(200, 2);
        anim.playReverse();
        Thread.sleep(200);
        move();
        while (moving){
            move(5);
        }
    }
    public void attack3Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        move(5);
        while(moving){
            move(5);
        }
        attack(5, 5);
        Thread.sleep(10);
        move(5);
        while(moving){
            move(5);
        }
        attack(5, 5);
        Thread.sleep(10);
        move(5);
        while(moving){
            move(5);
        }
        attack(5, 5);
        Thread.sleep(500);
    }

    public void attack(int numOfPj, int speed) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        PhysicsAlt p = MainGame.returnGamePhysics();
        double x = getX()+getWidth()/4.0;
        double y = getY()+getHeight()/4.0;
        for(int i = 0; i < numOfPj; i++){
            double speedX = Math.cos(2*Math.PI*i/numOfPj);
            double speedY = Math.sin(2*Math.PI*i/numOfPj);
            Projectile pj = new Projectile(Resource.PROJECTILE_1, x, y, 20, 20, speedX*speed, speedY*speed);
            MainGame.playMusic("res/projectileSound.wav");
            p.addSprite(pj);
        }
    }
    public void phoneAttack(int delay, int stage) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        int rand = (int)(Math.random()*30) + 15;
        for(int i = 0; i<rand; i++){
            double x = Math.random()*50-25;
            if(stage()!=stage){
                break;
            }
            PhoneProjectile p = new PhoneProjectile(getX()+ x, getY()-10, x/12, 1, 1);
            MainGame.returnGamePhysics().addSprite(p);
            Thread.sleep(delay);
        }
    }
}
