import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.util.ArrayList;

public class GraphicsWindow extends JPanel{
    private PhysicsAlt phys;

    public final BossBar bar;


    public GraphicsWindow(PhysicsAlt physics){
        this.phys = physics;
        this.bar = new BossBar();
        MessageThing.setKeyListener(this);
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
            g.fillRect(5, 5, (int)(410*bar.getPercentage()), 4);
            g.setColor(Color.WHITE);
            TextLayout t = new TextLayout("" + bar.getTimeLeft(), new Font(Font.MONOSPACED, Font.BOLD, 30), g.getFontMetrics().getFontRenderContext());
            t.draw((Graphics2D) g, 420, 30);
        }
        if(MessageThing.isDisplay()){
            g.setColor(new Color(0, 0, 0, 130));
            g.fillRect(0, (int)MessageThing.getY(), 480, 200);
            g.setColor(Color.WHITE);
            TextLayout t = new TextLayout(MessageThing.getMessage(), new Font(Font.MONOSPACED, Font.ITALIC, 20), g.getFontMetrics().getFontRenderContext());
            t.draw((Graphics2D) g, 10, (int)(MessageThing.getY())+50);
        }

    }
}
