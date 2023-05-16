import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        PhysicsAlt p = new PhysicsAlt(480, 480);

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

        for(int i = 0; i<10; i++){
            p.addSprite(new Projectile(ImageIO.read(new URL("https://cdn.pixabay.com/photo/2013/07/12/15/29/ball-149922_1280.png")), (int)(Math.random()*300), (int)(Math.random()*300), 10, 10, (int)(Math.random()*3), (int)(Math.random()*3)));

        }

        Enemy e = new Enemy(ImageIO.read(new URL("https://cdn.pixabay.com/photo/2013/07/12/15/29/ball-149922_1280.png")), 300, 300, 10, 10, 0 , 0);
        p.addSprite(e);


        while (true){
            p.updatePhysics();
            gw.repaint();
            Thread.sleep(17);

        }
    }
}