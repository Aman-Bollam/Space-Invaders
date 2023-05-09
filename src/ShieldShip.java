import java.awt.Rectangle;
public class ShieldShip {
    private int health;
    private Rectangle hitbox;
    private boolean life;
    private String name;
    private int hitX;
    private int hitY;
    private int width;
    private int height;
    public ShieldShip(int x, int y, int length, int hight) {
        health = 140;
        name = "shield1.png";
        life = true;
        hitX = x;
        hitY = y;
        width = length;
        height = hight;
        hitbox = new Rectangle(x,y,length,hight);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int hitX() {
        return hitX;
    }
    public int hitY() {
        return hitY;
    }
    public String getName() {
        return name;
    }
    public void setHealth() {
        int num;
        if(health>0) {
            health-=10;
        } else if(health==0){
            setLife(false);
        }
        num = (14)-(((health)/10)-1);
        name = "shield"+num+".png";
    }
    public int getHealth() {
        return health;
    }
    public boolean getLife() {
        return life;
    }
    public void setLife(boolean alive) {
        life = alive;
    }
    public Rectangle getHitBox() {
        return hitbox;
    }
}
