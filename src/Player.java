import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Player extends Sprite implements KeyListener {
    private final double speed = 3;
    private int power = 0;
    private boolean isShooting = false;
    public Player(GraphicsWindow gw) throws IOException {
        super(ImageIO.read(new File("res/player.png")), 30, 50, 30, 70, 0, 0);
        gw.addKeyListener(this);
        gw.setFocusable(true);
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
            speedX = -speed;
        }
        if(e.getKeyCode() == 39){
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
        if(e.getKeyCode()==37 || e.getKeyCode()==39){
            speedX = 0;
        }
        if(e.getKeyCode()==38 || e.getKeyCode()==40){
            speedY = 0;
        }
        if(e.getKeyCode()==90){
            isShooting = false;
        }

    }

    public void shoot() throws IOException {
        MainGame.returnGamePhysics().addSprite(new PlayerProjectile(getX(), getY(), 0, -10, 10));
        MainGame.returnGamePhysics().addSprite(new PlayerProjectile(getX()+getWidth()/2.0, getY(), 0, -10, 10));
    }
}
