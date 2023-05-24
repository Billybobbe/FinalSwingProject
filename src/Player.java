import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;

public class Player extends Sprite implements KeyListener {
    private final double speed = 4;
    private double tempSpeed = speed;
    Animation anim;
    private boolean isShooting = false;

    private double respawnTime = 0;
    public Player(double respawnTimeInSeconds, GraphicsWindow gw) throws IOException {
        super(Resource.DEFAULT_PLAYER, 200, 370, 40, 94, 0, 0);
        gw.addKeyListener(this);
        gw.setFocusable(true);
        gw.requestFocus();
        this.anim = new Animation(this);
        this.respawnTime = respawnTimeInSeconds;
    }
    public Player(GraphicsWindow gw) throws IOException {
        super(Resource.DEFAULT_PLAYER, 200, 370, 40, 94, 0, 0);
        gw.addKeyListener(this);
        gw.setFocusable(true);
        gw.requestFocus();
        this.anim = new Animation(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void act() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if(respawnTime>0){
            respawnTime -= 1/60.0;
        }
        if(isShooting){
            if(MainGame.getTime() % 5 == 0)
                shoot();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 37){
            if(getSpeedX() == 0){
                anim.setAnimation(100, Resource.playerAnimLeft);
                anim.play();
            }
            setSpeedX(-tempSpeed);

        }
        if(e.getKeyCode() == 39){
            if(getSpeedX() == 0){
                anim.setAnimation(100, Resource.playerAnimRight);
                anim.play();
            }
            setSpeedX(tempSpeed);

        }
        if(e.getKeyCode() == 38){
            setSpeedY(-tempSpeed);
        }
        if(e.getKeyCode() == 40){
            setSpeedY(tempSpeed);
        }
        if(e.getKeyCode() == 90){
            isShooting = true;
        }
        if(e.getKeyCode() == 16){
            tempSpeed = speed/3;
            if(getSpeedX()<0){
                setSpeedX(-tempSpeed);
            }
            if(getSpeedX()>0){
                setSpeedX(tempSpeed);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37) {
            setSpeedX(0);
            anim.setAnimation(100, Resource.playerAnimLeft);
            anim.playReverse();
        }
        if(e.getKeyCode()==39){
            anim.setAnimation(100, Resource.playerAnimRight);
            anim.playReverse();
            setSpeedX(0);
        }
        if(e.getKeyCode()==38 || e.getKeyCode()==40){
            setSpeedY(0);
        }
        if(e.getKeyCode()==90){
            isShooting = false;
        }
        if(e.getKeyCode() == 16){
            tempSpeed = speed;
            if(getSpeedX()<0){
                setSpeedX(-tempSpeed);
            }
            if(getSpeedX()>0){
                setSpeedX(tempSpeed);
            }
        }


    }

    @Override
    public void remove(){
        if(respawnTime<=0){
            java.util.Timer t = new java.util.Timer();
            TimerTask tt = new TimerTask() {
                int startTime = 0;
                @Override
                public void run() {
                    MainGame.returnGamePhysics().addSprite(new EnemyDeathEffect(Resource.DEFAULT_ENEMY_DEATH, getX()+getWidth()/4.0, getY()+getHeight()/4.0, 0, 0, 0, 0));
                    startTime++;
                    if(startTime>=6){
                        t.cancel();
                    }
                }
            };
            t.schedule(tt, (long)0, (long)16.666);

            java.util.Timer t2 = new java.util.Timer();
            TimerTask tt2 = new TimerTask() {
                @Override
                public void run() {
                    try {
                        MainGame.returnGamePhysics().addSprite(new Player(3, MainGame.returnGraphicsWindow()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            t2.schedule(tt2, 1000);
            MainGame.returnGamePhysics().remove(this);
            MainGame.setLives(MainGame.getLives()-1);
        }
    }

    public void shoot() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        MainGame.returnGamePhysics().addSprite(new PlayerProjectile(Resource.DEFAULT_PLAYER_PROJECTILE, getX()+getWidth()/4.0, getY()+10, 10, 10, 0, -10, (MainGame.getNumPower()/127)*5+5));
        MainGame.returnGamePhysics().addSprite(new PlayerProjectile(Resource.DEFAULT_PLAYER_PROJECTILE, getX()+getWidth()/2.0, getY()+10, 10, 10, 0, -10, (MainGame.getNumPower()/127)*5+5));
    }
}
