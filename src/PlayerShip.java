public class PlayerShip {
    private boolean [] equipped = {false,false,false,false,false,false,false,false,false};
    private String ship;
    private int phase;
    private int shipNum;
    public PlayerShip(int num, int pos) {
        ship = "ship-phase-"+num+"-pos"+phase+".png";
        phase = pos;
        shipNum = num;
        for(int i=0; i<9; i++) {
            if(equipped[i]){
                equipped[i] = false;
            }
        }
        equipped[num]=true;
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
    public int getPhase(){
        return phase;
    }
    public int getNum() {
        return shipNum;
    }
}
