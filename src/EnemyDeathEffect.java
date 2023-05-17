import java.awt.*;
import java.io.IOException;

public class EnemyDeathEffect extends Sprite{
    private int maxWidth = 100;
    private int maxHeight = 100;

    public EnemyDeathEffect(Image i, double x, double y, int width, int height, double speedX, double speedY) {
        super(i, x, y, width, height, speedX, speedY);
    }

    @Override
    public void act() throws IOException {
        setWidth(getWidth()+10);
        setHeight(getHeight()+10);
        setX(getX()-5);
        setY(getY()-5);
        if(getHeight()>maxHeight || getWidth()>maxWidth){
            remove();
        }
    }
}
