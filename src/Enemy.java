import java.awt.*;
import java.io.IOException;

public class Enemy extends Sprite {

    public Enemy(Image i, double x, double y, int width, int height, double speedX, double speedY) {
        super(i, x, y, width, height, speedX, speedY);
    }
    public void attack(int numOfPj, int speed) throws IOException {
        for(int i = 0; i <= numOfPj; i++){
            double speedX = Math.cos(2*Math.PI*i/numOfPj);
            double speedY = Math.sin(2*Math.PI*i/numOfPj);
            Projectile pj = new Projectile((getX()+getWidth())/2.0, (getY()+getHeight())/2.0, speedX*speed, speedY*speed);
        }
    }
}
