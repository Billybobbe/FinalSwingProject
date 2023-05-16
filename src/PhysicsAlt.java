import java.util.ArrayList;

public class PhysicsAlt {
    private int minXPlayerBound = 0;
    private int maxXPlayerBound = 439;
    private int minYPlayerBound = 30;
    private int maxYPlayerBound = 412;
    private int gameAreaX;
    private int gameAreaY;

    private ArrayList<Sprite> sprites = new ArrayList<>();

    public PhysicsAlt(int gameAreaX, int gameAreaY) {
        this.gameAreaX = gameAreaX;
        this.gameAreaY = gameAreaY;
    }

    public void updatePhysics() {
        for (int i = 0; i<sprites.size(); i++) {
            Sprite sp = sprites.get(i);
            double oldX = sp.getX();
            double oldY = sp.getY();
            sp.updatePosition();
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
            else if(sp.getX()<0 || sp.getX()>=gameAreaX || sp.getY()<0 || sp.getX()>=gameAreaY){
                sprites.remove(sp);
                i--;
            }
        }

        checkCollisions();
    }

    public void checkCollisions(){
        Player player = null;
        for(Sprite sp : sprites){
            if(sp instanceof Player){
                player = (Player)sp;
                break;
            }
        }
        if(player != null){
            double playerCenterX = player.getX() + player.getWidth()/2.0;
            double playerCenterY = player.getY() + player.getHeight()/2.0;

            for(Sprite sp : sprites){
                double distance = Math.sqrt(Math.pow((playerCenterX-sp.getX()),2) + Math.pow((playerCenterY-sp.getY()),2));
                if(distance<=12){
                    System.out.println("Collision");
                    break;
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
}
