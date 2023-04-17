public class EnemyGrid {
    private Enemy [][] enemies = new Enemy[5][10];
    private int eneX;
    private int eneY;
    public EnemyGrid(){
        eneX = 5;
        eneY = 85;
        for(int i=0, j=eneX; i<10; i++, j=j+85) {
            enemies[0][i] = new Enemy(3, true, j, eneY);
            enemies[1][i] = new Enemy(2, true, j, eneY+85);
            enemies[2][i] = new Enemy(2, true, j, eneY+170);
            enemies[3][i] = new Enemy(1, true, j, eneY+255);
            enemies[4][i] = new Enemy(1, true, j, eneY+340);
        }
    }
    public void setEnemies(Enemy [][] grid) {
        for(int i=0, j=eneX; i<10; i++, j=j+85) {
            boolean life = enemies[0][i].getLife();
            enemies[0][i] = new Enemy(3, life, j, eneY);
        }
        for(int i=0, j=eneX; i<10; i++, j=j+85){
            enemies[2][i] = new Enemy(2, true, j, eneY+170);
        }
        for(int i=0, j=eneX; i<10; i++, j=j+85){
            enemies[3][i] = new Enemy(1, true, j, eneY+255);
        }
        for(int i=0, j=eneX; i<10; i++, j=j+85){
            enemies[4][i] = new Enemy(1, true, j, eneY+340);
        }
    }
    public Enemy[][] getGrid() {
        Enemy[][] myEnemies = new Enemy[5][11];
        for(int i=0, j=eneX; i<10; i++, j=j+85) {
            myEnemies[0][i] = enemies[0][i];
            myEnemies[1][i] = enemies[1][i];
            myEnemies[2][i] = enemies[2][i];
            myEnemies[3][i] = enemies[3][i];
            myEnemies[4][i] = enemies[4][i];
        }
        return myEnemies;
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
