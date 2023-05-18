import javax.imageio.ImageIO;
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
    private final double speed = 3;
    private int power = 0;
    private boolean isShooting = false;
    public Player(GraphicsWindow gw) throws IOException {
        super(Resource.DEFAULT_PLAYER, 240, 410, 60, 140, 0, 0);
        gw.addKeyListener(this);
        gw.setFocusable(true);
        gw.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void act() throws IOException {
        if(isShooting){
            if(MainGame.getTime() % 5 == 0)
                shoot();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 37){
            if(speedX == 0){
                Animation anim = null;
                try {
                    anim = new Animation(500, Resource.playerAnimLeft, this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                anim.play();
            }
            speedX = -speed;

        }
        if(e.getKeyCode() == 39){
            if(speedX == 0){
                Animation anim;
                try {
                    anim = new Animation(500, Resource.playerAnimRight, this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                anim.play();
            }
            speedX = speed;

        }
        if(e.getKeyCode() == 38){
            speedY = -speed;
        }
        if(e.getKeyCode() == 40){
            speedY = speed;
        }
        if(e.getKeyCode() == 90){
            isShooting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37) {
            speedX = 0;
            Animation anim;
            try {
                anim = new Animation(500, Resource.playerAnimLeft, this);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            anim.playReverse();
        }
        if(e.getKeyCode()==39){
            Animation anim;
            try {
                anim = new Animation(500, Resource.playerAnimRight, this);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            anim.playReverse();
            speedX = 0;
        }
        if(e.getKeyCode()==38 || e.getKeyCode()==40){
            speedY = 0;
        }
        if(e.getKeyCode()==90){
            isShooting = false;
        }

    }

    @Override
    public void remove(){
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
        MainGame.returnGamePhysics().remove(this);
    }

    public void shoot() throws IOException {
        MainGame.returnGamePhysics().addSprite(new PlayerProjectile(Resource.DEFAULT_PLAYER_PROJECTILE, getX(), getY(), 10, 10, 0, -10, 10));
        MainGame.returnGamePhysics().addSprite(new PlayerProjectile(Resource.DEFAULT_PLAYER_PROJECTILE, getX()+getWidth()/2.0, getY(), 10, 10, 0, -10, 10));
    }
}
