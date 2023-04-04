import java.awt.Rectangle;

public class Enemy {
    private int model;
    private boolean life;
    private String name;
    private Rectangle hitbox;
    public Enemy(int type, boolean alive) {
        model = type;
        life = alive;
        name = "enemy-type"+type;
        //hitbox = new Dimension(x,y);
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
