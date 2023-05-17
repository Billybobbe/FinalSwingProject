import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Resource{
    public static final Image DEFAULT_ENEMY;

    static {
        try {
            DEFAULT_ENEMY = ImageIO.read(new File("res/player.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Image DEFAULT_PROJECTILE;

    static {
        try {
            DEFAULT_PROJECTILE = ImageIO.read(new URL("https://cdn.pixabay.com/photo/2013/07/12/15/29/ball-149922_1280.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Image DEFAULT_BACKGROUND;

    static {
        try {
            DEFAULT_BACKGROUND = ImageIO.read(new URL("https://stepbysteppainting.net/wp-content/uploads/2018/05/img_7468-e1526614302190-480x480.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
