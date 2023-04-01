public class Enemy {
    int model;
    boolean life;
    public Enemy(int type, boolean alive) {
        model = type;
        life = alive;
    }
    public int getType(){
        return model;
    }
    public boolean getLife(){
        return life;
    }
    public void setLife(boolean live){
        life = live;
    }
}
