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

    public static final Image STAGE_1_BACKGROUND;

    static {
        try {
            STAGE_1_BACKGROUND = ImageIO.read(new File("res/Stage1Background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image BOSS_BACKGROUND;

    static {
        try {
            BOSS_BACKGROUND = ImageIO.read(new File("res/BossBackground.png"));
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
    public static final Image SCORE_CLING;

    static {
        try {
            SCORE_CLING = ImageIO.read(new File("res/scoreCling.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image PHONE_COMBINED;

    static {
        try {
            PHONE_COMBINED = ImageIO.read(new File("res/phoneCombined.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image PHONE;

    static {
        try {
            PHONE = ImageIO.read(new File("res/phone.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image GAME_WINDOW_BACKGROUND;

    static {
        try {
            GAME_WINDOW_BACKGROUND = ImageIO.read(new File("res/leather.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image PHONE_CASE;

    static {
        try {
            PHONE_CASE = ImageIO.read(new File("res/phoneCase.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image ATTACK_BUBBLE;

    static {
        try {
            ATTACK_BUBBLE = ImageIO.read(new File("res/attackBubble.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Font INFO_FONT_DETAIL;

    static {
        try {
            INFO_FONT_DETAIL = Font.createFont(Font.PLAIN, new File("res/Caveat-Medium.ttf"));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Font INFO_FONT_NUMBER;

    static {
        try {
            INFO_FONT_NUMBER = Font.createFont(Font.PLAIN, new File("res/ReggaeOne-Regular.ttf"));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Image HEALTH_HEART;

    static {
        try {
            HEALTH_HEART = ImageIO.read(new File("res/HealthHeart.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image PARTICLE;

    static {
        try {
            PARTICLE = ImageIO.read(new File("res/particle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Image ATTACK_PARTICLE;

    static {
        try {
            ATTACK_PARTICLE = ImageIO.read(new File("res/attackparticle.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Image[] playerAnimLeft;
    public static Image[] playerAnimRight;
    public static Image[] ethanAnimLeft;
    public static Image[] ethanAnimRight;
    public static Image[] ethanAttackAnim;

    public static void loadAnimations() throws IOException {
        playerAnimLeft = buildArray("res/playerAnimLeft");
        playerAnimRight = buildArray("res/playerAnimRight");
        ethanAnimLeft = buildArray("res/ethanAnimLeft");
        ethanAnimRight = buildArray("res/ethanAnimRight");
        ethanAttackAnim = buildArray("res/ethanAttackAnim");



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
