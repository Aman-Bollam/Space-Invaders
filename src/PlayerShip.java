import java.awt.*;
import javax.swing.*;
public class PlayerShip {
    private boolean [] equipped = {false,false,false,false,false,false,false,false,false};
    private String ship;
    private int phase;
    private int shipNum;
    private int lives;
    private Rectangle hitbox;
    private int x;
    private int y;
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
        y = 725;
        }
        equipped[num]=true;
<<<<<<< HEAD
        //hitbox = new Rectangle(new Dimension(num, pos))
=======
        // hitbox = new Rectangle(x, y, , pos);
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
>>>>>>> 122cf249bfb4a908259746c2b60032aed83970c7
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
    public Rectangle getHitBox() {
        return hitbox;
    }
}
