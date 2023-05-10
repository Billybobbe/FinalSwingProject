import java.awt.*;

public class Sprite {
    private int width;
    private int height;
    private Image i;
    public Sprite(Image i, int width, int height){
        this.i = i;
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Image getimage(){
        return i;
    }
}
