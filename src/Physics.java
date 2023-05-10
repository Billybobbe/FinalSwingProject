public class Physics {
    private Sprite[][] gameArea;

    public Physics(int xSize, int ySize){
        this.gameArea = new Sprite[xSize][ySize];
    }
    public void updatePhysics() {
        for(int i = 0; i < gameArea.length; i++){
            for(int r = 0; r < gameArea[0].length; r++){
                if(gameArea[i][r] != null){

                }
            }
        }
    }
    public Sprite[][] getSpriteArray(){
        return gameArea;
    }
    public void addSprite(Sprite sp, int x, int y){
        if(gameArea[x][y]==null){
            gameArea[x][y] = sp;
        }
    }

}
