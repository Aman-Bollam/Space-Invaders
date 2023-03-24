public class PlayerShip {
    private boolean [] equipped = {false,false,false,false,false,false,false,false};
    private String [] ships = {};
    private String ship;
    public PlayerShip(int num) {
        ship = ships[num];
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
}
