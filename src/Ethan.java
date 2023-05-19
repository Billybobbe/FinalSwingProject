import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Ethan extends Boss{

    public Ethan() throws IOException {
        super(Resource.ETHAN_IDLE, 200, -10, 60, 70, 0, 1, 1000, 2,
                1000, 2, 1000, 2, 1000, 2, 1000, 2, Resource.ethanAnimLeft, Resource.ethanAnimLeft);
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
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t.scheduleAtFixedRate(tt, 0, (long)16.6666);
        return t;
    }
    public void attack1Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        System.out.println("test");
        attack(10, 5);
        Thread.sleep(500);
        attack(10, 5);
        Thread.sleep(1000);
        attack(25, 1);
        move();
        System.out.println(moving);
        while(moving){
            move();
            Thread.sleep((long)16.66666);
        }
        Thread.sleep(1000);
    }
    public void attack2Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
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
}
