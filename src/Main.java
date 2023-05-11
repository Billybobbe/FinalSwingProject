import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Physics p = new Physics(480, 480);

        GraphicsWindow gw = new GraphicsWindow(p);
        gw.setBounds(0,0,480,480);

        Player plr = new Player(gw);
        p.addSprite(plr);
        Projectile ball = new Projectile(ImageIO.read(new URL("https://cdn.pixabay.com/photo/2013/07/12/15/29/ball-149922_1280.png")), 200, 200, 10, 10, 0.03, 0.05);
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
}