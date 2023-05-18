import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy extends Sprite {
    private int numBullets = 10;
    private int health = 1;
    private double chanceofP;
    private double chanceofBP;
    private double chanceofS;

    public Enemy(Image i, double x, double y, int width, int height, double speedX, double speedY) {
        super(i, x, y, width, height, speedX, speedY);
    }
    public Enemy(Image i, double x, double y, int width, int height, double speedX, double speedY, int numBullets, int health, double chanceToDropP, double chanceToDropBP, double chanceToDropScore) {
        super(i, x, y, width, height, speedX, speedY);
        this.numBullets = numBullets;
        this.health = health;
        this.chanceofP = chanceToDropP;
        this.chanceofBP = chanceToDropBP;
        this.chanceofS = chanceToDropScore;
    }
    public void attack(int numOfPj, int speed) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        PhysicsAlt p = MainGame.returnGamePhysics();
        for(int i = 0; i <= numOfPj; i++){
            double speedX = Math.cos(2*Math.PI*i/numOfPj);
            double speedY = Math.sin(2*Math.PI*i/numOfPj);
            Projectile pj = new Projectile(Resource.PROJECTILE_1, getX(), getY(), 20, 20, speedX*speed, speedY*speed);
            MainGame.playMusic("res/projectileSound.wav");
            p.addSprite(pj);
        }
    }
    @Override
    public void act() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if(Math.random()*10<0.1 && getY()<200){
            attack(numBullets, 1);
        }
    }

    @Override
    public void remove() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MainGame.playMusic("res/enemyDeath.wav");
        PhysicsAlt phys = MainGame.returnGamePhysics();
        phys.addSprite(new EnemyDeathEffect(Resource.DEFAULT_ENEMY_DEATH, getX()+getWidth()/4.0, getY()+getHeight()/4.0, 0, 0, 0, 0));
        double num = Math.random();
        System.out.println(num);
        if(chanceofS<chanceofP && chanceofS<chanceofBP){
            if(num<chanceofS){
                dropS();
            }
            else{
                if(chanceofP>chanceofBP){
                    if(num<chanceofBP){
                        dropBP();
                    }
                    else{
                        dropP();
                    }
                }
                else{
                    if(num<chanceofP){
                        dropP();
                    }
                    else{
                        if(num<chanceofBP){
                            dropBP();
                        }
                    }
                }
            }
        }
        else if(chanceofP<chanceofBP && chanceofP<chanceofS){
            if(num<chanceofP){
                dropP();
            }
            else{
                if(chanceofS>chanceofBP){
                    if(num<chanceofBP){
                        dropBP();
                    }
                    else{
                        if (num < chanceofS) {
                            dropS();
                        }
                    }
                }
                else{
                    if(num<chanceofS){
                        dropS();
                    }
                    if(num<chanceofBP){
                        dropBP();
                    }
                }
            }
        }
        else{
            if(num<chanceofBP){
                dropBP();
            }
            else{
                if(chanceofP>chanceofS){
                    if(num<chanceofS){
                        dropS();
                    }
                    else{
                        if(num<chanceofP){
                            dropP();
                        }
                    }
                }
                else{
                    if(num<chanceofP){
                        dropP();
                    }
                    else{
                        if(num<chanceofS){
                            dropS();
                        }
                    }
                }
            }

        }

        phys.remove(this);
    }
    public void dropS(){
        MainGame.returnGamePhysics().addSprite(new ScoreItem(getX(), getY()));
    }
    public void dropBP(){
        MainGame.returnGamePhysics().addSprite(new PowerItem(getX(), getY(), 20, 20, 10));
    }
    public void dropP(){
        MainGame.returnGamePhysics().addSprite(new PowerItem(getX(), getY(), 1));
    }

    public void setHealth(int health){
        this.health = health;
    }
    public int getHealth(){
        return health;
    }
}
