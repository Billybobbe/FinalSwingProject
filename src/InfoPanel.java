import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;


class InfoPanel extends JPanel {


    public void paintComponent(Graphics g){
        g.drawImage(Resource.GAME_WINDOW_BACKGROUND, 0, 0, null);

        Graphics2D g2D = (Graphics2D) g;
        g.setColor(Color.WHITE);
        Font f = Resource.INFO_FONT_DETAIL;
        f = f.deriveFont(Font.PLAIN, 30);
        Font f1 = Resource.INFO_FONT_NUMBER;
        f1 = f1.deriveFont(Font.PLAIN, 25);

        TextLayout scoreLayout = new TextLayout("Score", f, g2D.getFontMetrics().getFontRenderContext());
        TextLayout scoreLayoutNumber = new TextLayout("" + MainGame.getScore(), f1, g2D.getFontMetrics().getFontRenderContext());
        TextLayout powerLayout = new TextLayout("Power", f, g2D.getFontMetrics().getFontRenderContext());
        TextLayout powerLayoutNumber = new TextLayout("" + MainGame.getNumPower(), f1, g2D.getFontMetrics().getFontRenderContext());
        TextLayout livesLayout = new TextLayout("Lives ", f, g2D.getFontMetrics().getFontRenderContext());

        scoreLayout.draw(g2D, 10, 40);
        scoreLayoutNumber.draw(g2D, 10, 80);
        powerLayout.draw(g2D, 20, 200);
        powerLayoutNumber.draw(g2D, 90, 200);
        livesLayout.draw(g2D, 20, 250);
        for(int i = 0; i < MainGame.getLives()-1; i++){
            g.drawImage(Resource.HEALTH_HEART, i*30+25, 260, 30, 30, null);
        }
    }
}