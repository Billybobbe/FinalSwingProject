import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        Physics p = new Physics(480, 480);

        GraphicsWindow gw = new GraphicsWindow(p);
        gw.setBounds(0,0,480,480);

        Player plr = new Player(gw);
        p.addSprite(plr, 20, 30);

        Frame f = new Frame();
        f.add(gw);
        f.add(new JPanel()); //will add scoring n stuff here
        f.pack();
        f.setSize(640,480);
        f.setResizable(false);
        f.setVisible(true);
    }
}