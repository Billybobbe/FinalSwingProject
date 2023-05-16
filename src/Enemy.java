import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Enemy extends Sprite {
    private int numBullets = 10;
    private int health = 1;

    public Enemy(Image i, double x, double y, int width, int height, double speedX, double speedY) {
        super(i, x, y, width, height, speedX, speedY);
    }
    public Enemy(Image i, double x, double y, int width, int height, double speedX, double speedY, int numBullets, int health) {
        super(i, x, y, width, height, speedX, speedY);
        this.numBullets = numBullets;
        this.health = health;
    }
    public void attack(int numOfPj, int speed) throws IOException {
        PhysicsAlt p = MainGame.returnGamePhysics();
        for(int i = 0; i <= numOfPj; i++){
            double speedX = Math.cos(2*Math.PI*i/numOfPj);
            double speedY = Math.sin(2*Math.PI*i/numOfPj);
            Projectile pj = new Projectile(getX(), getY(), speedX*speed, speedY*speed);
            p.addSprite(pj);
        }
    }
    @Override
    public void act() throws IOException {
        if(Math.random()*10<0.1 && getY()<200){
            attack(numBullets, 1);
        }
    }
    public void setHealth(int health){
        this.health = health;
    }
    public int getHealth(){
        return health;
    }
}
