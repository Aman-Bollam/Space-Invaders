public class EnemyGrid {
    private Enemy [][] enemies = new Enemy[5][10];
    private int eneX;
    private int eneY;
    public EnemyGrid(){
        eneX = 5;
        eneY = 85;
        for(int i=0, j=eneX; i<10; i++, j=j+85) {
            enemies[0][i] = new Enemy(3, true, j, eneY);
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
    public void setEnemies() {
        for(int i=0, j=eneX; i<10; i++, j=j+85) {
          enemies[0][i] = new Enemy(3, true, j, eneY);
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
}
