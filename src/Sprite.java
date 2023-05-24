import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Sprite {
    private int width;
    private int height;
    private Image i;

    private double x;
    private double y;

    private double speedX;
    private double speedY;

    public Sprite(Image i, double x, double y, int width, int height, double speedX, double speedY) {
        this.i = i;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getimage() {
        return i;
    }

    public void updatePosition() {
        x += speedX;
        y += speedY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void act() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void remove() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MainGame.returnGamePhysics().remove(this);
    }

    public void setImage(Image i){
        this.i = i;
    }
    public void setSpeedX(double speedX){
        this.speedX = speedX;
    }
    public void setSpeedY(double speedY){
        this.speedY = speedY;
    }
    public double getSpeedX(){
        return speedX;
    }
    public double getSpeedY(){
        return speedY;
    }
}

