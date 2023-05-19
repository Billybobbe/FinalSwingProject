import java.awt.*;

public class BossBar {
    private boolean active;
    private double percentage;
    private Color c;
    public BossBar(){
        active = false;
        percentage = 0;
        c = Color.white;
    }
    public boolean isActive(){
        return active;
    }
    public void setPercentage(double num){
        this.percentage = num;
    }
    public double getPercentage(){
        return percentage;
    }

    public Color getColor() {
        return c;
    }
    public void setColor(Color c){
        this.c = c;
    }
    public void setActive(boolean b){
        active = b;
    }
}
