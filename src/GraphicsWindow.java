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
        g.drawImage(Resource.STAGE_1_BACKGROUND, 0, 0, null);
        if(SpellCard.getBackgroundTransparency() > 0){
            Graphics2D g2D = (Graphics2D) g;
            AffineTransform a = AffineTransform.getRotateInstance(SpellCard.getRotation(), 240, 240);
            AlphaComposite transparency = AlphaComposite.SrcOver;
            transparency = transparency.derive(SpellCard.getBackgroundTransparency());
            g2D.setComposite(transparency);
            g2D.setTransform(a);
            g2D.drawImage(Resource.BOSS_BACKGROUND,-240, -240, null);
        }

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
            }
        }
        if(bar.isActive()){
            g2D.setComposite(AlphaComposite.Src);
            g.setColor(bar.getColor());
            g.fillRect(5, 5, (int)(410*bar.getPercentage()), 4);
            g.setColor(Color.WHITE);
            TextLayout t = new TextLayout("" + bar.getTimeLeft(), new Font(Font.MONOSPACED, Font.BOLD, 30), g.getFontMetrics().getFontRenderContext());
            t.draw((Graphics2D) g, 420, 30);

            t = new TextLayout(bar.getBossName(), new Font(Font.MONOSPACED, Font.BOLD, 15), g.getFontMetrics().getFontRenderContext());
            t.draw((Graphics2D) g, 10, 25);

        }
        if(MessageThing.isDisplay()){
            AlphaComposite transparency = AlphaComposite.SrcOver;

            Sprite p1 = MessageThing.getP1();
            Sprite p2 = MessageThing.getP2();
            g2D.setComposite(AlphaComposite.SrcOver);

            transparency = transparency.derive(p1.getTransparency());
            g2D.setComposite(transparency);
            g2D.drawImage(p1.getimage(), (int)p1.getX(), (int)p1.getY(), p1.getWidth(), p1.getHeight(), null);
            transparency = transparency.derive(p2.getTransparency());
            g2D.setComposite(transparency);
            g2D.drawImage(p2.getimage(), (int)p2.getX(), (int)p2.getY(), p2.getWidth(), p2.getHeight(), null);
            g2D.setComposite(AlphaComposite.SrcOver);
            g.setColor(new Color(0, 0, 0, 130));
            g.fillRect(0, (int)MessageThing.getY(), 480, 200);
            g.setColor(Color.WHITE);
            TextLayout t = new TextLayout(MessageThing.getMessage(), new Font(Font.MONOSPACED, Font.ITALIC, 20), g.getFontMetrics().getFontRenderContext());
            t.draw((Graphics2D) g, 10, (int)(MessageThing.getY())+50);
        }
        if(SpellCard.getAttackName() != null){
            if(SpellCard.getSprite().getTransparency()!=0){
                Sprite sp = SpellCard.getSprite();
                AlphaComposite transparency = AlphaComposite.SrcOver;
                transparency = transparency.derive(sp.getTransparency());
                g2D.setComposite(transparency);
                g2D.drawImage(sp.getimage(), (int)sp.getX(), (int)sp.getY(), sp.getWidth(), sp.getHeight(), null);
            }

            g2D.setComposite(AlphaComposite.SrcOver);
            g2D.setColor(new Color(0,0,0,100));
            g2D.fillRect((int)(415-SpellCard.getTextSize()*SpellCard.getAttackName().length()/1.6), SpellCard.getNameY(), (int)(SpellCard.getTextSize()*SpellCard.getAttackName().length()/1.6), SpellCard.getTextSize());
            g2D.setColor(new Color(136, 233, 255, (int)(255*SpellCard.getNameTransparency())));
            TextLayout t = new TextLayout(SpellCard.getAttackName(), new Font(Font.MONOSPACED, Font.BOLD, SpellCard.getTextSize()), g.getFontMetrics().getFontRenderContext());
            t.draw((Graphics2D) g, (int)(415-SpellCard.getTextSize()*SpellCard.getAttackName().length()/1.6), SpellCard.getNameY()+15);

            g2D.setColor(Color.WHITE);

        }

    }
}
