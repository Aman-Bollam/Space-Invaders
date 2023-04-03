import java.awt.Rectangle;

public class Enemy {
    int model;
    boolean life;
    private Rectangle hitbox;
    public Enemy(int type, boolean alive) {
        model = type;
        life = alive;
        //hitbox = new Dimension(x,y);
    }
    public int getType() {
        return model;
    }
    public boolean getLife() {
        return life;
    }
    public void setLife(boolean live) {
        life = live;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
}
