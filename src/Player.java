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
    public Player(GraphicsWindow gw) throws IOException {
        super(ImageIO.read(new File("res/player.png")), 30, 30);
        gw.addKeyListener(this);
        gw.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("test");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
