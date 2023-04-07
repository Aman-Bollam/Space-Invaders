import java.awt.Rectangle;

public class BossEnemy {
    private boolean alive;
    private boolean visible;
    private Rectangle hitbox;
    public BossEnemy() {
        alive = true;
        visible = false;
        hitbox = new Rectangle();
    }
}
