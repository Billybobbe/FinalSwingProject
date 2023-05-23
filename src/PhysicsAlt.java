import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class PhysicsAlt {
    private int minXPlayerBound = 0;
    private int maxXPlayerBound = 439;
    private int minYPlayerBound = -20;
    private int maxYPlayerBound = 370;
    private int gameAreaX;
    private int gameAreaY;

    private ArrayList<Sprite> sprites = new ArrayList<>();

    public PhysicsAlt(int gameAreaX, int gameAreaY) {
        this.gameAreaX = gameAreaX;
        this.gameAreaY = gameAreaY;
    }

    public void updatePhysics() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        for (int i = 0; i<sprites.size(); i++) {
            Sprite sp = sprites.get(i);
            if(sp!=null){
                double oldX = sp.getX();
                double oldY = sp.getY();
                sp.updatePosition();
                sp.act();
                if(sp instanceof Player && (sp.getX() < minXPlayerBound || sp.getX() > maxXPlayerBound || sp.getY() < minYPlayerBound || sp.getY() > maxYPlayerBound)){
                    double newX = sp.getX();
                    double newY = sp.getY();
                    if((newX < maxXPlayerBound && newX >= minXPlayerBound)){
                        sp.setY(oldY);
                    }
                    else if((newY < maxYPlayerBound && newY >= minYPlayerBound)){
                        sp.setX(oldX);
                    }
                    else{
                        sp.setX(oldX);
                        sp.setY(oldY);
                    }
                }
                else if(sp.getX()<-100 || sp.getX()>=gameAreaX+100 || sp.getY()<-100 || sp.getX()>=gameAreaY+100){
                    sprites.remove(sp);
                    i--;
                }
            }
        }

        checkCollisions();
    }

    public void checkCollisions() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Player player = null;
        for(int i = 0; i<sprites.size(); i++){
            Sprite sp = sprites.get(i);
            if(sp instanceof Player){
                player = (Player)sp;
                break;
            }
        }
        if(player != null){
            double playerCenterX = player.getX() + player.getWidth()/2.0;
            double playerCenterY = player.getY() + player.getHeight()/2.0;

            for(int i = 0; i<sprites.size(); i++){
                Sprite sp = sprites.get(i);
                if(sp!= null){
                    double distance = Math.sqrt(Math.pow((playerCenterX-(sp.getX()+sp.getWidth()/2.0)),2) + Math.pow((playerCenterY-(sp.getY()+sp.getHeight()/2.0)),2));
                    if(distance<=9 && (sp instanceof Projectile || sp instanceof Enemy)){
                        player.remove();
                        MainGame.setLives(MainGame.getLives()-1);
                        break;
                    }
                    if(distance<= 2 && sp instanceof Boss){
                        player.remove();
                        MainGame.setLives(MainGame.getLives()-1);
                        break;
                    }

                    if(distance<=30 && (sp instanceof ScoreItem || sp instanceof PowerItem || sp instanceof ScoreClingItem)){
                        if(sp instanceof PowerItem){
                            int power = ((PowerItem)sp).returnPower();
                            MainGame.setNumPower(MainGame.getNumPower()+power);
                            if(MainGame.getNumPower()>126){
                                MainGame.setNumPower(126);
                            }
                        }
                        sp.remove();
                        i--;
                    }
                }
            }
        }
        for(int i = 0; i<sprites.size(); i++) {
            for(int r = 0; r<sprites.size(); r++){
                Sprite sp = sprites.get(i);
                Sprite sp2 = sprites.get(r);

                if(sp instanceof Enemy && sp2 instanceof PlayerProjectile){
                    double spCenterX = sp.getX() + sp.getWidth()/2.0;
                    double spCenterY = sp.getY() + sp.getHeight()/2.0;
                    double distance = Math.sqrt(Math.pow((spCenterX-sp2.getX()),2) + Math.pow((spCenterY-sp2.getY()),2));
                    if(distance<=12){
                        MainGame.setScore(MainGame.getScore()+100);
                        ((Enemy) sp).setHealth(((Enemy) sp).getHealth()- ((PlayerProjectile) sp2).getDamage());
                        if(((Enemy) sp).getHealth()<=0){
                            sp.remove();
                            sp2.remove();
                            if(i<r){
                                if(i>0){
                                    i-=1;
                                }
                                if(r>1){
                                    r-=2;
                                }
                            }
                            else{
                                if(i>1){
                                    i-=2;
                                }
                                if (r > 0) {
                                    r-=1;
                                }
                            }
                        }
                    }
                }
                if(sp instanceof Boss && sp2 instanceof PlayerProjectile){
                    double spCenterX = sp.getX() + sp.getWidth()/2.0;
                    double spCenterY = sp.getY() + sp.getHeight()/2.0;
                    double distance = Math.sqrt(Math.pow((spCenterX-sp2.getX()),2) + Math.pow((spCenterY-sp2.getY()),2));
                    if(distance<=12){
                        MainGame.setScore(MainGame.getScore()+100);
                        ((Boss) sp).decreaseHealth(((PlayerProjectile) sp2).getDamage());
                        sp2.remove();
                        if(i>0){
                            i--;
                        }
                    }
                }
            }
        }

    }

    public void addSprite(Sprite sp){
        sprites.add(sp);
    }
    public ArrayList<Sprite> getSpriteArray(){
        return sprites;
    }

    public void remove(Sprite sp){
        sprites.remove(sp);
    }

    public void projectileSweep() {
        Player p = null;
        for(Sprite sp : sprites){
            if (sp instanceof Player){
                p = (Player)sp;
            }
        }
        for(int i = 0; i < sprites.size(); i++){
            Sprite sp = sprites.get(i);
            if((sp instanceof Projectile || sp instanceof Enemy) && !(sp instanceof PlayerProjectile)){
                sprites.set(i, new ScoreClingItem(sp.getX(), sp.getY(), p));
            }
        }
    }
}
