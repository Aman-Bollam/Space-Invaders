import java.awt.*;
public class PlayerShip {
    private boolean [] equipped = {false,false,false,false,false,false,false,false,false};
    private String ship;
    private int phase;
    private int shipNum;
    private int lives;
    private Rectangle hitbox;
    private int x;
    private int y;
    private int width;
    private int height;
    private int hitX;
    private int hitY;
    public PlayerShip(int num, int pos) {
        lives = 3;
        ship = "ship-phase-"+(num+1)+"-pos"+pos+".png";
        phase = pos;
        shipNum = num+1;
        for(int i=0; i<9; i++) {
            if(equipped[i]){
                equipped[i] = false;
            }
        x = 407;
        y = 740;
        }
        equipped[num]=true;
    }
    public void setPos(int pos) {
        phase = pos;
        ship = "ship-phase-"+shipNum+"-pos"+pos+".png";
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPosRight() {
        x+=2;
    }
    public void setPosLeft() {
        x-=2;
    }
    public boolean isEquipped(int num) {
        if(equipped[num]){
            return true;
        }
        return false;
    }
    public String getName() {
        return ship;
    }
    public int getShip() {
        int num = 0;
        for(int i=0; i<9; i++) {
            if(equipped[i]){
                num = i;
                i=9;
            }
        }
        return num;
    }
    public int getPhase() {
        return phase;
    }
    public int getShipNum() {
        return shipNum;
    }
    public void subtractLife() {
        if(lives>0){
            lives--;
        }
    }
    public int getLives(){
        return lives;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
    public void setHitBox(int posX, int posY, int x, int y) {
        width = x;
        height = y;
        hitX = posX;
        hitY = posY;
        hitbox = new Rectangle(posX, posY, x, y);
    }
    public int hitX() { 
        return hitX;
    }
    public int hitY() {
        return hitY;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
