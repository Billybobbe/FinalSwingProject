import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Resource{
    public static final Image DEFAULT_PLAYER;

    static {
        try {
            DEFAULT_PLAYER = ImageIO.read(new File("res/player.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Image DEFAULT_ENEMY;

    static {
        try {
            DEFAULT_ENEMY = ImageIO.read(new File("res/enemy1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image DEFAULT_MENU_BACKGROUND;

    static {
        try {
            DEFAULT_MENU_BACKGROUND = ImageIO.read(new URL("https://i.pinimg.com/originals/55/1d/7f/551d7fd19970f4c27431db6a40a7880b.jpg"));
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

    public static final Image DEFAULT_PLAYER_PROJECTILE;

    static {
        try {
            DEFAULT_PLAYER_PROJECTILE = ImageIO.read(new File("res/playerprojectile.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Image DEFAULT_ENEMY_DEATH;

    static {
        try {
            DEFAULT_ENEMY_DEATH = ImageIO.read(new File("res/enemydeath.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image PROJECTILE_1;

    static {
        try {
            PROJECTILE_1 = ImageIO.read(new File("res/projectile1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image POWERITEM;

    static {
        try {
            POWERITEM = ImageIO.read(new File("res/powerItem.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image SCOREITEM;

    static {
        try {
            SCOREITEM = ImageIO.read(new File("res/scoreitem.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image THOUSAND_SCORE;

    static {
        try {
            THOUSAND_SCORE = ImageIO.read(new File("res/1000point.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image ETHAN_IDLE;

    static {
        try {
            ETHAN_IDLE = ImageIO.read(new File("res/ethanIdle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Image[] playerAnimLeft;
    public static Image[] playerAnimRight;
    public static Image[] ethanAnimLeft;

    public static void loadAnimations() throws IOException {
        playerAnimLeft = buildArray("res/playerAnimLeft");
        playerAnimRight = buildArray("res/playerAnimRight");
        ethanAnimLeft = buildArray("res/ethanAnimLeft");


    }
    public static Image[] buildArray(String path) throws IOException {
        File[] dir = new File(path).listFiles();
        Image [] arr = new Image[dir.length];
        for(int i = 0; i < dir.length; i++){
            arr[i] = ImageIO.read(dir[i]);
        }
        return arr;
    }


}
