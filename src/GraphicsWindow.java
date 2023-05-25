import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
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
        Graphics2D g2D = (Graphics2D)g;
        ArrayList<Sprite> arr = phys.getSpriteArray();
        for(int i = 0; i< arr.size(); i++){
            Sprite sp = arr.get(i);
            if(sp!=null){
                AffineTransform a = AffineTransform.getRotateInstance(sp.getRotation(), sp.getX()+sp.getWidth()/2.0, sp.getY()+sp.getHeight()/2.0);
                AlphaComposite transparency = AlphaComposite.SrcOver;
                transparency = transparency.derive(sp.getTransparency());
                g2D.setTransform(a);
                g2D.setComposite(transparency);
                g2D.drawImage(sp.getimage(), (int)sp.getX() , (int)sp.getY(), sp.getWidth(), sp.getHeight(), null);
                //g.drawImage(Resource.PROJECTILE_1, (int)(sp.getX()+sp.getWidth()/2.0-1.5), (int)(sp.getY()+sp.getHeight()/2.0-1.5), 6, 6 ,null);
                g2D.setComposite(AlphaComposite.Src);
            }
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
