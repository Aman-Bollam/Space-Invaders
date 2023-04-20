public class EnemyRow {
    private Enemy [] enemies;
    private int eneX;
    private int eneY;
    private int size;
    private int y;
    public EnemyRow(int type, int length, int level){
        eneX = 5;
        eneY = ((level-1)*55)+90;
        size = length;
        y = level;
        enemies = new Enemy[length];
        for(int i=0; i<length; i++){
            enemies[i] = new Enemy(type, true, eneX, eneY);;
            eneX = eneX + 75;
        }
        resetCoord();
    }
    public int getLevel() {
        return y;
    }
    public int getSize() {
        return size;
    }
    public void resetCoord(){
        eneX = 5;
        eneY = ((y-1)*85)+100;
    }
    public void setGrid(Enemy [] grid) {
        for(int i=0, j=eneX; i<size; i++, j=j+85) {
        
        }
    }
    public Enemy[] getRow() {
        Enemy[] myEnemies = new Enemy[size];
        for(int i=0; i<10; i++) {
            myEnemies[i] = enemies[i];
        }
        return myEnemies;
    }
    public Enemy getEnemy(int pos) {
        if(pos<size) {
            return enemies[pos];
        }
        return null;
    }
    public boolean getLife(int num) {
        return (enemies[num]).getLife();
    }
    public void setPosRight() {
        eneX+=2;
    }
    public void setPosLeft() {
        eneX-=2;
    }
    public void setPosDown() {
        eneY+=20;
    }
}
