import java.awt.*;
import java.io.IOException;

public class PlayerProjectile extends Projectile{
    private int damage;

    public PlayerProjectile(Image i, double x, double y, int width, int height, double speedX, double speedY, int damage) {
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

}
