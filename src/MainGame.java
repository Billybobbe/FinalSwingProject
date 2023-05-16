import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainGame {
    private static PhysicsAlt p;
    private static GraphicsWindow gw;

    private static int time = 0;


    public static void start() throws IOException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
        p = new PhysicsAlt(480, 480);

        gw = new GraphicsWindow(p);
        gw.setBounds(0,0,480,480);

        Player plr = new Player(gw);
        p.addSprite(plr);
        Projectile ball = new Projectile( 200, 200,  0.03, 0.05);
        p.addSprite(ball);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setUndecorated(true);
        f.add(gw);
        f.add(new JPanel()); //will add scoring n stuff here
        f.pack();
        f.setSize(640,480);
        f.setResizable(false);

        f.setVisible(true);
        GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //d.setFullScreenWindow(f); //This part is for cool boi fullscreen
        //d.setDisplayMode(new DisplayMode(640, 480, 32, 60));

        Enemy e = new Enemy(ImageIO.read(new URL("https://cdn.pixabay.com/photo/2013/07/12/15/29/ball-149922_1280.png")), 100, 300, 10, 10, 0.5, 0);
        p.addSprite(e);

        time = 0;
        //Clip c1 = playMusic("res/Zun1.wav");
        while (time/60.0/60<=1){
            spawnEnemies(0.01, 100);
            p.updatePhysics();
            gw.repaint();
            time++;
            Thread.sleep(17);
        }
        //c1.stop();

    }
    public static Clip playMusic(String fileName) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        Clip bgm = AudioSystem.getClip();
        AudioInputStream stream = AudioSystem.getAudioInputStream(new File(fileName));
        bgm.open(stream);
        bgm.start();
        return bgm;
    }

    public static PhysicsAlt returnGamePhysics(){
        return p;
    }

    public static void spawnEnemies(double frequency, int bulletsPerEnemy) {
        if (Math.random() < frequency) {
            p.addSprite(new Enemy(Resource.DEFAULT_ENEMY, (int)(Math.random()*480), -20, 40, 40, Math.random()*6-3, Math.random()*3, bulletsPerEnemy));
        }
    }
    public static int getTime(){
        return time;
    }

}

