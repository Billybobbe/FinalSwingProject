import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class PhoneProjectile extends Projectile{
    private double timer = 0;
    private double splitTimeInSeconds;

    public PhoneProjectile(double x, double y, double speedX, double speedY, double splitTimeInSeconds) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(Resource.PHONE_COMBINED, x, y, 15, 20, speedX, speedY);
        this.splitTimeInSeconds = splitTimeInSeconds;
    }

    @Override
    public void act() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        timer++;
        if(timer/60 >= splitTimeInSeconds){
            Projectile p = new Projectile(Resource.PHONE, getX(), getY(), 15, 20, getSpeedX()-(0.5*getSpeedX()), getSpeedY());
            Projectile p1 = new Projectile(Resource.PHONE_CASE, getX(), getY(), 15, 20, getSpeedX()+(0.5*getSpeedX()), getSpeedY());
            MainGame.returnGamePhysics().addSprite(p);
            MainGame.returnGamePhysics().addSprite(p1);
            remove();
        }
    }
}
