public class EnemyRow {
    private Enemy [] enemies;
    private int eneX;
    private int eneY;
    private int size;
    private int y;
    private int enemy;
    public EnemyRow(int type, int length, int level){
        enemy = type;
        eneX = 4;
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
        eneX = 4;
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
        int x;
        int y;
        int width;
        int height;
        for(int i=0; i<size; i++){
            x = enemies[i].hitX();
            y = enemies[i].hitY();
            width = enemies[i].getWidth();
            height = enemies[i].getHeight();
            enemies[i] = new Enemy(enemy, enemies[i].getLife(), num, eneY);
            enemies[i].setHitBox(x, y, width, height);
            num = num + 75;
        }
    }
    public void setPosLeft() {
        int num;
        eneX--;
        num = eneX;
        int x;
        int y;
        int width;
        int height;
        for(int i=0; i<size; i++){
            x = enemies[i].hitX();
            y = enemies[i].hitY();
            width = enemies[i].getWidth();
            height = enemies[i].getHeight();
            enemies[i] = new Enemy(enemy, enemies[i].getLife(), num, eneY);
            enemies[i].setHitBox(x, y, width, height);
            num = num + 75;
        }
    }
    public void setPosDown() {
        int num;
        eneY+=10;
        num = eneY;
        int x;
        int y;
        int width;
        int height;
        for(int i=0; i<size; i++){
            x = enemies[i].hitX();
            y = enemies[i].hitY();
            width = enemies[i].getWidth();
            height = enemies[i].getHeight();
            enemies[i] = new Enemy(enemy, enemies[i].getLife(), eneX, num);
            enemies[i].setHitBox(x, y, width, height);
            num = num + 75;
        }
    }
    public boolean allDead(){
        int count=0;
        for(int i=0;i<enemies.length;i++){
            if(!enemies[i].getLife()){
                count++;
            }
        }
        if(count==enemies.length){
            return true;
        }else{
            return false;
        }
    }
}
