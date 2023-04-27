public class EnemyRow {
    private Enemy [] enemies;
    private int eneX;
    private int eneY;
    private int size;
    private int y;
    private int enemy;
    public EnemyRow(int type, int length, int level){
        enemy = type;
        eneX = 5;
        eneY = ((level-1)*55)+10;
        size = length;
        y = level;
        enemies = new Enemy[length];
        for(int i=0; i<length; i++){
            enemies[i] = new Enemy(type, true, eneX, eneY);;
            eneX = eneX + 60;
        }
        resetCoord();
    }
    public int getType() {
        return enemy;
    }
    public int getX(){
        return eneX;
    }
    public int getY(){
        return eneY;
    }
    public int getLevel() {
        return y;
    }
    public int getSize() {
        return size;
    }
    public void resetCoord(){
        eneX = 5;
        eneY = ((y-1)*55)+20;
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
        int num;
        eneX++;
        num = eneX;
        for(int i=0; i<size; i++){
            enemies[i] = new Enemy(enemy, enemies[i].getLife(), num, eneY);
            num = num + 75;
        }
    }
    public void setPosLeft() {
        int num;
        eneX--;
        num = eneX;
        for(int i=0; i<size; i++){
            enemies[i] = new Enemy(enemy, enemies[i].getLife(), num, eneY);;
            num = num + 75;
        }
    }
    public void setPosDown() {
        int num;
        eneY+=8;
        num = eneY;
        for(int i=0; i<size; i++){
            enemies[i] = new Enemy(enemy, enemies[i].getLife(), eneX, num);;
            num = num + 75;
        }
    }
}
