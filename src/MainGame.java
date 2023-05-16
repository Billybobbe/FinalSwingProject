import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainGame {
    private static Physics p;
    private static GraphicsWindow gw;

    public static void start() throws IOException, InterruptedException {
        p = new Physics(480, 480);

        gw = new GraphicsWindow(p);
        gw.setBounds(0,0,480,480);

        Player plr = new Player(gw);
        p.addSprite(plr);
        Projectile ball = new Projectile( 200, 200,  0.03, 0.05);
        p.addSprite(ball);

        Frame f = new Frame();
        f.add(gw);
        f.add(new JPanel()); //will add scoring n stuff here
        f.pack();
        f.setSize(640,480);
        f.setResizable(false);
        f.setVisible(true);
        while (true){

            p.updatePhysics();
            gw.repaint();
            Thread.sleep(5);
        }
    }
    public static Physics returnGamePhysics(){
        return p;
    }

}
