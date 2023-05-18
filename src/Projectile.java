import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Projectile extends Sprite{

    public Projectile(Image i, double x, double y, int width, int height, double speedX, double speedY) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(i, x, y, width, height, speedX, speedY);
    }
    public Projectile(double x, double y, double speedX, double speedY) throws IOException {
        super(Resource.DEFAULT_PROJECTILE,
                x, y, 10, 10, speedX, speedY);
    }
}
