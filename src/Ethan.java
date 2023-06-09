import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Ethan extends Boss{
    private Animation anim;

    public Ethan() throws IOException {
        super(Resource.ETHAN_IDLE, 200, -100, 60, 70, 0, 1, 1000, 40,
                1000, 30, 1000, 40, 1000, 50, 1000,
                70, Resource.ethanAnimRight, Resource.ethanAnimLeft, "Ethan Lund");
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
    public Timer attack2() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SpellCard.set(new Sprite(Resource.ETHAN_IDLE, 0, 0, 100, 100, 0, 0), "Douche Sign \"No case\"");
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if(!SpellCard.isPlaying()){
                    try {
                        attack2Cycle();
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t.scheduleAtFixedRate(tt, 0, 1);
        return t;
    }
    public Timer attack3(){
        anim.clear();
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
    public Timer attack4() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SpellCard.set(new Sprite(Resource.ETHAN_IDLE, 0, 0, 100, 100, 0, 0), "Douche Sign \"Dropped it\"");
        anim.play();

        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if(!SpellCard.isPlaying()){
                    try {
                        attack4Cycle();
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t.scheduleAtFixedRate(tt, 0, 1);
        return t;
    }
    public Timer attack5() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SpellCard.set(new Sprite(Resource.ETHAN_IDLE, 0, 0, 100, 100, 0, 0), "Douche Sign \"Broken Phone\"");
        anim.play();

        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if(!SpellCard.isPlaying()){
                    try {
                        attack5Cycle();
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
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
        while(isMoving()){
            move();
            Thread.sleep((long)16.66666);
        }
        Thread.sleep(1000);
    }
    public void attack2Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Thread.sleep(1000);
        anim.play();
        phoneAttack(200, 30, 15);
        anim.playReverse();
        Thread.sleep(200);
        move();
        while (isMoving()){
            move(5);
        }
    }
    public void attack3Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Thread.sleep(1000);
        move(5);
        while(isMoving()){
            move(5);
        }
        attack(5, 5);
        Thread.sleep(10);
        move(5);
        while(isMoving()){
            move(5);
        }
        attack(5, 5);
        Thread.sleep(10);
        move(5);
        while(isMoving()){
            move(5);
        }
        attack(5, 5);
        Thread.sleep(500);
    }
    public void attack4Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Thread.sleep(1000);
        attack(50, 1);
        Thread.sleep(2000);
        phoneAttack(3, 50, 30);
    }
    public void attack5Cycle() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        attack(30, 3);
        move();
        int i = 0;
        while(isMoving()){
            move();
            if(i%10==0){
                phoneAttack(0, 10, 10);
            }
            Thread.sleep(100);
            i++;

        }
    }

    public void attack(int numOfPj, double speed) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        PhysicsAlt p = MainGame.returnGamePhysics();

        double x = getX()+getWidth()/4.0;
        double y = getY()+getHeight()/4.0;
        for(int i = 0; i < numOfPj; i++){
            if(!isAttackRunning()){
                break;
            }
            double speedX = Math.cos(2*Math.PI*i/numOfPj);
            double speedY = Math.sin(2*Math.PI*i/numOfPj);
            Projectile pj = new Projectile(Resource.PROJECTILE_1, x, y, 20, 20, speedX*speed, speedY*speed);
            MainGame.playMusic("res/projectileSound.wav");
            p.addSprite(pj);
        }
    }
    public void phoneAttack(int delay, int randNumMax, int randNumMin) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        int rand = (int)(Math.random()*(randNumMax-randNumMin)) + randNumMin;
        for(int i = 0; i<rand; i++){
            double x = Math.random()*50-25;
            if(!isAttackRunning()){
                break;
            }
            MainGame.playMusic("res/projectileSound.wav");
            PhoneProjectile p = new PhoneProjectile(getX()+ x, getY()-10, x/12, 1, 1);
            MainGame.returnGamePhysics().addSprite(p);
            Thread.sleep(delay);
        }
    }
}
