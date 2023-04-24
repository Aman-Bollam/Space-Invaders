import java.awt.Rectangle;

public class ShieldShip {
    private int health;
    private Rectangle hitbox;
    private boolean life;
    private String name;
    public ShieldShip() {
        health = 140;
        name = "shield1.png";
        life = true;
    }
    public String getName() {
        return name;
    }
    public void setHealth() {
        int num;
        if(health>0) {
            health-=10;
        }
        num = (14)-(((health)/10)-1);
        name = "shield"+num+".png";
    }
    public boolean getLife() {
        return life;
    }
    public void setLife(boolean alive) {
        life = alive;
    }
}
