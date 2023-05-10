import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        Physics p = new Physics();
        GraphicsWindow gw = new GraphicsWindow(p);
        Frame f = new Frame();
        f.add(gw);
        f.setVisible(true);
    }
}