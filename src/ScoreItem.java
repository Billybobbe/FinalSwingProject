import java.awt.*;
import java.io.IOException;

public class ScoreItem extends Sprite{
    private int timeExisting;

    public ScoreItem(double x, double y) {
        super(Resource.SCOREITEM, x, y, 15, 15, 0, 0);
        timeExisting = 0;
    }
    public void act() throws IOException {
        timeExisting++;
        setSpeedY(-1 + Math.pow(this.timeExisting, 2)/2000);
        if(getSpeedY()>2){
            setSpeedY(2);
        }
    }
    public int returnScore(){
        return 1000;
    }
    @Override
    public void remove(){
        MainGame.returnGamePhysics().addSprite(new Effect(Resource.THOUSAND_SCORE, getX(), getY(), 15, 20, 0, 0, 50, 50));
        MainGame.setScore(MainGame.getScore()+1000);
        MainGame.returnGamePhysics().remove(this);
    }
}
