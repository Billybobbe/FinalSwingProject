import java.awt.*;

public class Sprite {
    private int width;
    private int height;
    private Image i;

    private double x;
    private double y;

    private double speedX;
    private double speedY;

    public Sprite(Image i, int x, int y, int width, int height) {
        this.i = i;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
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

    public double returnX() {
        return x;
    }

    public double returnY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

