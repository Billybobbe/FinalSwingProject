public class Physics {
    private Sprite[][] gameArea;

    public Physics(){
        this.gameArea = new Sprite[1000][1000];
    }
    public void updatePhysics() {
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
