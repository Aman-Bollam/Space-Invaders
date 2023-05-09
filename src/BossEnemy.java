import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class BossEnemy{
    private boolean alive;
    private boolean visible;
    private Rectangle hitbox;
    private int x;
    private int y;
    private int width;
    private int height;
    private int hitX;
    private int hitY;
    private boolean right;
    private boolean left;
    // private Timer delay;
    public BossEnemy() {
        alive = true;
        visible = false;
        x = -40;
        y = 40;
        right = true;
        left = false;
        // delay = new Timer(100,new Stop());
    }
    public boolean getLife() {
        return alive;
    }
    public void setLife(boolean life) {
        alive = life;
    }
    public boolean visibility() {
        return visible;
    }
    public void setVisibility(boolean vis) {
        visible = vis;
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
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int pos) {
        x=pos;
    }
    public void setY(int pos) {
        y=pos;
    }
    public void setRight() {
        x+=2;
    } 
    public void setLeft() {
        x-=2;
    }
    public void setRight(boolean direct) {
        right = direct;
    }
    public void setLeft(boolean direct) {
        left = direct;
    }
    public boolean right() {
        return right;
    }
    public boolean left() {
        return left;
    }
    public void setHitBox(int posX, int posY, int x, int y) {
        width = x;
        height = y;
        hitX = posX;
        hitY = posY;
        hitbox = new Rectangle(posX, posY, x, y);
    }
    public Rectangle hitbox() {
        return hitbox;
    }
    // private class Stop implements ActionListener {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //     // TODO Auto-generated method stub
    //     if(time<num) {
    //         time++;
    //     } else {
    //         motion = false;
    //         delay.stop();
    //     }
    //     }
    // }
}
