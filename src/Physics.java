public class Physics {
    private Sprite[][] gameArea;

    private int minXPlayerBound = 0;
    private  int maxXPlayerBound = 439;
    private int minYPlayerBound = 30;
    private  int maxYPlayerBound = 412;

    public Physics(int xSize, int ySize){
        this.gameArea = new Sprite[xSize][ySize];
    }
    public void updatePhysics() {
        Sprite[][] temp = new Sprite[gameArea.length][gameArea[0].length];
        for(int i = 0; i<temp.length; i++){
            for(int r = 0; r<temp[0].length; r++){
                temp[i][r] = gameArea[i][r];
            }
        }

        for(int i = 0; i < gameArea.length; i++){
            for(int r = 0; r < gameArea[0].length; r++){
                if(temp[i][r] != null){
                    Sprite sp = temp[i][r];
                    sp.updatePosition();
                    gameArea[i][r] = null;
                    int newX = (int)sp.getX();
                    int newY = (int)sp.getY();
                    if((newX>=maxXPlayerBound || newX<minXPlayerBound || newY >= maxYPlayerBound || newY < minYPlayerBound) && sp instanceof Player){
                        if((newX < maxXPlayerBound && newX >= minXPlayerBound)){
                            gameArea[newX][r] = sp;
                            sp.setY(r);
                        }
                        else if((newY < maxYPlayerBound && newY >= minYPlayerBound)){
                            gameArea[i][newY] = sp;
                            sp.setX(i);
                        }
                        else{
                            gameArea[i][r] = sp;
                            sp.setX(i);
                            sp.setY(r);
                        }
                    }
                    else if(newX < gameArea.length && newX >= 0 && newY < gameArea[0].length && newY >= 0){
                        gameArea[newX][newY] = sp;
                    }

                    if(sp instanceof Player){
                        checkForCollision((Player)sp);
                    }
                }
            }
        }
    }
    public void checkForCollision(Player player){
        for(int i = (int)player.getX(); i < player.getWidth() + (int)player.getX(); i++){
            for(int r = (int)player.getY(); r < player.getHeight() + (int)player.getY(); r++){
                if(gameArea[i][r] != null){
                    Sprite colObject = gameArea[i][r];
                    if(colObject instanceof Projectile){
                        System.out.println("Collided!!!!!");
                    }
                }
            }
        }
    }

    public Sprite[][] getSpriteArray(){
        return gameArea;
    }
    public void addSprite(Sprite sp){
        if(gameArea[(int)sp.getX()][(int)sp.getY()]==null){
            gameArea[(int)sp.getX()][(int)sp.getY()] = sp;
        }
    }

}
