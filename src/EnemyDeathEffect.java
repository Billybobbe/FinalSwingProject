import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class EnemyDeathEffect extends Effect{


    public EnemyDeathEffect(Image i, double x, double y, int width, int height, double speedX, double speedY) {
        super(i, x, y, width, height, speedX, speedY, 10, 5);
    }

    @Override
    public void act() {
        super.act();
        setWidth(getWidth()+10);
        setHeight(getHeight()+10);
        setX(getX()-5);
        setY(getY()-5);
    }
}
