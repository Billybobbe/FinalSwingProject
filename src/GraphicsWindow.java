import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class GraphicsWindow extends JPanel{
    private PhysicsAlt phys;

    public final BossBar bar;

    public GraphicsWindow(PhysicsAlt physics){
        this.phys = physics;
        this.bar = new BossBar();
        setBackground(Color.RED);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Resource.DEFAULT_BACKGROUND, 0, 0, null);
        ArrayList<Sprite> arr = phys.getSpriteArray();
        for(int i = 0; i< arr.size(); i++){
            Sprite sp = arr.get(i);
            g.drawImage(sp.getimage(), (int)sp.getX() , (int)sp.getY(), sp.getWidth(), sp.getHeight(), null);
        }
        if(bar.isActive()){
            g.setColor(bar.getColor());
            g.fillRect(5, 5, (int)(460*bar.getPercentage()), 4);
        }
    }
}
