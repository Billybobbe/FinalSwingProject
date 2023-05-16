import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Enemy extends Sprite{
    public Enemy(int x, int y, double speedX, double speedY) throws IOException {
        super(ImageIO.read(new File("res/enemy.png")), x, y, 30, 30, speedX, speedY);
    }

    public void attack1(Physics phys) throws IOException {
        for(int i = (int)getX()-2; i <= (int)getX()+2; i++){
            Projectile p = new Projectile(i, (int)getY()+2, i-getX(), 2);

        }


    }

}
