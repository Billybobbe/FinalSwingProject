import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Projectile extends Sprite{
    public Projectile(Image i, double x, double y, int width, int height, double speedX, double speedY) {
        super(i, x, y, width, height, speedX, speedY);
    }
    public Projectile(double x, double y, double speedX, double speedY) throws IOException {
        super(ImageIO.read(new URL("https://cdn.pixabay.com/photo/2013/07/12/15/29/ball-149922_1280.png")),
                x, y, 10, 10, speedX, speedY);
    }
}
