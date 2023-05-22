import java.awt.*;

public class ScoreClingItem extends Sprite{
    private final double speed = 5;
    private Player p;

    public ScoreClingItem(double x, double y, Player p) {
        super(Resource.SCORE_CLING, x, y, 15, 15, 0, 0);
        this.p = p;
    }

    @Override
    public void act(){
        if(p != null){
            double targetX = p.getX()+p.getWidth()/4.0;
            double targetY = p.getY()+p.getHeight()/4.0;
            double potentialSpeedX = targetX - getX();
            double potentialSpeedY = targetY - getY();
            if (Math.abs(Math.max(potentialSpeedX, potentialSpeedY)) > speed) { //so it doesn't move too fast
                double divisior = (speed / Math.max(Math.abs(potentialSpeedX), Math.abs(potentialSpeedY)));
                speedX = potentialSpeedX*divisior;
                speedY = potentialSpeedY*divisior;
            }
            else{
                speedX = potentialSpeedX;
                speedY = potentialSpeedY;
            }
        }
    }
    @Override
    public void remove(){
        MainGame.setScore(MainGame.getScore()+500);
        MainGame.returnGamePhysics().remove(this);
    }
}
