import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class GraphicsWindow extends JPanel{
    private PhysicsAlt phys;

    public GraphicsWindow(PhysicsAlt physics){
        this.phys = physics;
        setBackground(Color.RED);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ArrayList<Sprite> arr = phys.getSpriteArray();
        for(Sprite sp : arr){
            g.drawImage(sp.getimage(), (int)sp.getX() , (int)sp.getY(), sp.getWidth(), sp.getHeight(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
    }
}
