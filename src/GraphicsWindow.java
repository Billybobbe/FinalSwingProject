import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

public class GraphicsWindow extends JPanel{
    private Physics phys;

    public GraphicsWindow(Physics physics){
        this.phys = physics;
        setBackground(Color.RED);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Sprite[][] arr = phys.getSpriteArray();
        for(int i = 0; i < arr.length; i++){
            for(int r = 0; r < arr[0].length; r++){
                if(arr[i][r]!= null){
                    Sprite sp = arr[i][r];
                    g.drawImage(sp.getimage(), i , r, sp.getWidth(), sp.getHeight(), new ImageObserver() {
                        @Override
                        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                            return false;
                        }
                    });
                }
            }
        }
        repaint();
    }
}
