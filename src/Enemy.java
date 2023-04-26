import java.awt.Rectangle;

public class Enemy {
    private int model;
    private boolean life;
    private String name;
    private Rectangle hitbox;
    private int x;
    private int y;
    private int width;
    private int height;
    public Enemy(int type, boolean alive, int xPos, int yPos) {
        model = type;
        life = alive;
        name = "enemy-type"+type+".png";
        x = xPos;
        y = yPos;;
        if(type==1) {
            width = 48;
            height = 22;
        } else if(type==2) {
            width = 48;
            height = 22;
        } else if(type==3) {
            width = 48;
            height = 22;
        }
        hitbox = new Rectangle(xPos, yPos, width, height);
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
    public int getType() {
        return model;
    }
    public boolean getLife() {
        return life;
    }
    public String getName(){
        return name;
    }
    public void setLife(boolean live) {
        life = live;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
}
