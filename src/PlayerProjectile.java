import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class PlayerProjectile extends Projectile{
    private int damage;

    public PlayerProjectile(Image i, double x, double y, int width, int height, double speedX, double speedY, int damage) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(i, x, y, width, height, speedX, speedY);
        this.damage = damage;
    }

    public PlayerProjectile(double x, double y, double speedX, double speedY, int damage) throws IOException {
        super(x, y, speedX, speedY);
        this.damage = damage;
    }
    public int getDamage(){
        return damage;
    }

    public void removeBoss() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MainGame.returnGamePhysics().addSprite(new Effect(Resource.ATTACK_PARTICLE, getX(), getY(), 20, 20, 0, -2, 10, 0));
        MainGame.returnGamePhysics().addSprite(new Effect(Resource.ATTACK_PARTICLE, getX()+5, getY(), 20, 20, 0, -2, 10, 0));
        MainGame.returnGamePhysics().addSprite(new Effect(Resource.ATTACK_PARTICLE, getX()-5, getY(), 20, 20, 0, -2, 10, 0));
        super.remove();
    }

}
