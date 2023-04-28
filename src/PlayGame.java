import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
public class PlayGame extends JPanel{
  private int x;
  private int y;
  private int lives;
  private final String path = "images\\";
  private boolean right;
  private boolean left;
  private GameEngine run;
  private PlayerShip ship;
  private ShieldShip shield1;
  private ShieldShip shield2;
  private ShieldShip shield3;
  private String shipName;
  private EnemyRow one;
  private EnemyRow two;
  private EnemyRow three;
  private EnemyRow four;
  private EnemyRow five;
  private int enemySpeed;
  private int enemyNum;
  private Image shipLives;
  private Image myPlayer;
  private Image backG;
  private Image enemyOne;
  private Image enemyTwo;
  private Image enemyThree;
  private Image shieldOne;
  private Image shieldTwo;
  private Image shieldThree;
  private Image boss;
  private ArrayList <Image> explosions = new ArrayList();
  private ArrayList <Integer> xExplosion = new ArrayList();
  private ArrayList <Integer> yExplosion = new ArrayList();
  private int height;
  private ShieldShip [] shields;
  public PlayGame(GameEngine engine, PlayerShip player) {
    this.setFocusable(true);
    this.requestFocusInWindow();
    run = engine;
    ship = new PlayerShip(player.getShip(), 2);
    one = new EnemyRow(3, 10 ,1);
    two = new EnemyRow(2, 10 ,2);
    three = new EnemyRow(2, 10 ,3);
    four = new EnemyRow(1, 10 ,4);
    five = new EnemyRow(1, 10 ,5);
    lives = 3;
    x = ship.getX();
    y = ship.getY();
    enemyNum = 50;
    enemySpeed = 100;
    shipName = ship.getName();
    shield1 = new ShieldShip(convert(155), convert(636), convert(100), convert(55));
    shield2 = new ShieldShip(convert(417), convert(636), convert(100), convert(55));
    shield3 = new ShieldShip(convert(679), convert(636), convert(100), convert(55));
    setShields();
    shipLives = resize(new ImageIcon(path+(new PlayerShip(ship.getShip(), 1)).getName()),run.getSize()/7).getImage();
    backG = resize(new ImageIcon(path+"background6.jpg"),run.getSize()).getImage();
    enemyOne = resize(new ImageIcon(path+"enemy-type3.png"),run.getSize()/9).getImage();
    enemyTwo = resize(new ImageIcon(path+"enemy-type2.png"),run.getSize()/9).getImage();
    enemyThree = resize(new ImageIcon(path+"enemy-type1.png"),run.getSize()/9).getImage();
    boss = resize(new ImageIcon(path+"shield14.png"),run.getSize()/5).getImage();
    right = true;
    left = false;
    switchShip(false);
  }
  public void addToArrays(Image exp, int x, int y) {
      explosions.add(exp);
      xExplosion.add(x);
      yExplosion.add(y);
  }
  public void removeArrays() {
    explosions.remove(0);
    xExplosion.remove(0);
    yExplosion.remove(0);
}
  public void setEneBox() {
    for(int i=0; i<one.getSize(); i++) {
      one.getEnemy(i).setHitBox(getEneX(i,one)+convert(28), getEneY(i,one)+convert(35),convert(52), convert(28));
      // System.out.println(one.getEnemy(i).getWidth());
    }
    for(int i=0; i<two.getSize(); i++) {
      two.getEnemy(i).setHitBox(getEneX(i,two)+convert(28), getEneY(i,two)+convert(35),convert(52), convert(28));
    }
    for(int i=0; i<three.getSize(); i++) {
      three.getEnemy(i).setHitBox(getEneX(i,three)+convert(28), getEneY(i,three)+convert(35),convert(52), convert(28));
    }
    for(int i=0; i<four.getSize(); i++) {
      four.getEnemy(i).setHitBox(getEneX(i,four)+convert(28), getEneY(i,four)+convert(35),convert(52), convert(28));
    }
    for(int i=0; i<five.getSize(); i++) {
      five.getEnemy(i).setHitBox(getEneX(i,five)+convert(28), getEneY(i,five)+convert(35),convert(52), convert(28));
    }
  }
  public void detectCollisions() {
    for(int i=0; i<one.getSize(); i++) {
      if((one.getEnemy(i).getHitbox()).intersects(shield1.getHitBox()) && shield1.getLife()) {
        eneShieldCollided(1, i, one);
      }
      if((one.getEnemy(i).getHitbox()).intersects(shield2.getHitBox()) && shield2.getLife()) {
        eneShieldCollided(2, i, one);
      }
      if((one.getEnemy(i).getHitbox()).intersects(shield3.getHitBox()) && shield3.getLife()) {
        eneShieldCollided(3, i, one);
      }
      if((two.getEnemy(i).getHitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && two.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, two);
      }
      if((two.getEnemy(i).getHitbox()).intersects(shield2.getHitBox()) && shield2.getLife()) {
        eneShieldCollided(2, i, two);
      }
      if((two.getEnemy(i).getHitbox()).intersects(shield3.getHitBox()) && shield3.getLife()) {
        eneShieldCollided(3, i, two);
      }
      if((three.getEnemy(i).getHitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && three.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, three);
      }
      if((three.getEnemy(i).getHitbox()).intersects(shield2.getHitBox()) && shield2.getLife()) {
        eneShieldCollided(2, i, three);
      }
      if((three.getEnemy(i).getHitbox()).intersects(shield3.getHitBox()) && shield3.getLife()) {
        eneShieldCollided(3, i, three);
      }
      if((four.getEnemy(i).getHitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && four.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, four);
      }
      if((four.getEnemy(i).getHitbox()).intersects(shield2.getHitBox()) && shield2.getLife()) {
        eneShieldCollided(2, i, four);
      }
      if((four.getEnemy(i).getHitbox()).intersects(shield3.getHitBox()) && shield3.getLife()) {
        eneShieldCollided(3, i, four);
      }
      if((five.getEnemy(i).getHitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && five.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, five);
      }
      if((five.getEnemy(i).getHitbox()).intersects(shield2.getHitBox()) && shield2.getLife()) {
        eneShieldCollided(2, i, five);
      }
      if((five.getEnemy(i).getHitbox()).intersects(shield3.getHitBox()) && shield3.getLife()) {
        eneShieldCollided(3, i, five);
      }
    } 
  }
  public void eneShieldCollided(int shield,int enemy,EnemyRow row) {
    addToArrays(resize(new ImageIcon(path+"explosion.png"),run.getSize()/9).getImage(),getEneX(enemy,row)+convert(28),getEneY(enemy,row)+convert(35));
    row.getEnemy(enemy).setLife(false);
    if(shield==1) {
      shieldOne = resize(new ImageIcon(path+(shield1.getName())),run.getSize()/5).getImage();
      shield1.setHealth();
    } else if(shield==2) {
      shieldTwo = resize(new ImageIcon(path+(shield2.getName())),run.getSize()/5).getImage();
      shield2.setHealth();
    } else {
      shieldThree = resize(new ImageIcon(path+(shield3.getName())),run.getSize()/5).getImage();
      shield3.setHealth();
    }
        
  }
  public int getEnemySpeed() {
    return enemySpeed;
  }
  public EnemyRow rowLife() {
    for(int i=0; i<five.getSize(); i++) {
      if(five.getLife(i)) {
        return five;
      }
    }
    for(int i=0; i<four.getSize(); i++) {
      if(four.getLife(i)) {
        return four;
      }
    }
    for(int i=0; i<three.getSize(); i++) {
      if(three.getLife(i)) {
        return three;
      }
    }
    for(int i=0; i<two.getSize(); i++) {
      if(two.getLife(i)) {
        return two;
      }
    }
    for(int i=0; i<one.getSize(); i++) {
      if(one.getLife(i)) {
        return one;
      }
    }
    return one;
  }
  public void setRight(boolean direct) {
    right = direct;
  }
  public void setLeft(boolean direct) {
    left = direct;
  }
  public boolean getRight() {
    return right;
  }
  public boolean getLeft() {
    return left;
  }
  public int getEnePos(String pos) {
    if(pos.equals("x")) {
      return one.getX();
    } else {
      return rowLife().getY();
    }
  }
  public void enePosChange(String direct) {
    if(direct.equals("down")) {
      one.setPosDown();
      two.setPosDown();
      three.setPosDown();
      four.setPosDown();
      five.setPosDown();
    } else if(direct.equals("right")) {
      one.setPosRight();
      two.setPosRight();
      three.setPosRight();
      four.setPosRight();
      five.setPosRight();
    } else if(direct.equals("left")) {
      one.setPosLeft();
      two.setPosLeft();
      three.setPosLeft();
      four.setPosLeft();
      five.setPosLeft();
    }
  }
  public void setShields() {
    shieldOne = resize(new ImageIcon(path+(shield1.getName())),run.getSize()/5).getImage();
    shieldTwo = resize(new ImageIcon(path+(shield2.getName())),run.getSize()/5).getImage();
    shieldThree = resize(new ImageIcon(path+(shield3.getName())),run.getSize()/5).getImage();
  }
  public void setPosRight() {
    if(x+3<=(820)){
      ship.setPosRight();
    }
    x=ship.getX();
  }
  public void setPosLeft() {
    if(x-3>=10){
      ship.setPosLeft();
    }
    x=ship.getX();
  }
  public void switchShip(boolean pic) {
    if(!pic) {
      shipName = ship.getName();
    } else {
      shipName = "ship-phase-" + ship.getShipNum() + "-pos3.png";
    }
    myPlayer = resize(new ImageIcon(path+shipName),run.getSize()/8).getImage();
  }
  public int getEneX(int num, EnemyRow lvl) {
    return convert((lvl.getEnemy(num)).getX());
  }
  public int getEneY(int num, EnemyRow lvl) {
    return convert((lvl.getEnemy(num)).getY());
  }
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backG, convert(0),convert(0),null);
    g.drawImage(myPlayer,convert(x),convert(y),null);
    if(shield1.getLife()) {
      g.drawImage(shieldOne,convert(110),convert(555),null);
      g.drawRect(shield1.hitX(), shield1.hitY(), shield1.getWidth(), shield1.getHeight());
    }
    if(shield2.getLife()) {
      g.drawImage(shieldTwo,convert(372),convert(555),null);
    }
    if(shield3.getLife()) {
      g.drawImage(shieldThree,convert(634),convert(555),null);
    }
    for(int i=0; i<one.getSize(); i++) {
      if(one.getLife(i)) {
        g.drawImage(enemyOne,getEneX(i,one),getEneY(i,one),null);
<<<<<<< HEAD
        g.drawRect(getEneX(i,one)+convert(28), getEneY(i,one)+convert(35), convert(52),convert(28));
=======
        g.drawRect(one.getEnemy(i).hitX(), one.getEnemy(i).hitY(),one.getEnemy(i).getWidth(), one.getEnemy(i).getHeight());
>>>>>>> bf17d252d5a6e935f1a629df91a0d2f71f20bfad
      }
    }
    for(int i=0; i<two.getSize(); i++) {
      if(two.getLife(i)) {
        g.drawImage(enemyTwo,getEneX(i,two),getEneY(i,two),null);
<<<<<<< HEAD
        g.drawRect(getEneX(i,one)+convert(28), getEneY(i,two)+convert(35), convert(52),convert(28));
=======
        g.drawRect(two.getEnemy(i).hitX(), two.getEnemy(i).hitY(),two.getEnemy(i).getWidth(), two.getEnemy(i).getHeight());
>>>>>>> bf17d252d5a6e935f1a629df91a0d2f71f20bfad
      }
    }
    for(int i=0; i<three.getSize(); i++) {
      if(three.getLife(i)) {
        g.drawImage(enemyTwo,getEneX(i,three),getEneY(i,three),null);
<<<<<<< HEAD
        g.drawRect(getEneX(i,one)+convert(28), getEneY(i,three)+convert(35), convert(52),convert(28));
=======
        g.drawRect(three.getEnemy(i).hitX(), three.getEnemy(i).hitY(),three.getEnemy(i).getWidth(), three.getEnemy(i).getHeight());
>>>>>>> bf17d252d5a6e935f1a629df91a0d2f71f20bfad
      }
    }
    for(int i=0; i<four.getSize(); i++) {
      if(four.getLife(i)) {
        g.drawImage(enemyThree,getEneX(i,four),getEneY(i,four),null);
<<<<<<< HEAD
        g.drawRect(getEneX(i,one)+convert(28), getEneY(i,four)+convert(35), convert(52),convert(28));
=======
        g.drawRect(four.getEnemy(i).hitX(), four.getEnemy(i).hitY(),four.getEnemy(i).getWidth(), four.getEnemy(i).getHeight());
>>>>>>> bf17d252d5a6e935f1a629df91a0d2f71f20bfad
      }
    }
    for(int i=0; i<five.getSize(); i++) {
      if(five.getLife(i)) {
        g.drawImage(enemyThree,getEneX(i,five),getEneY(i,five),null);
<<<<<<< HEAD
        g.drawRect(getEneX(i,one)+convert(28), getEneY(i,five)+convert(35), convert(52),convert(28));
=======
        g.drawRect(five.getEnemy(i).hitX(), five.getEnemy(i).hitY(),five.getEnemy(i).getWidth(), five.getEnemy(i).getHeight());
>>>>>>> bf17d252d5a6e935f1a629df91a0d2f71f20bfad
      }
    }
    for(int i=1, j=125; i<=lives; i++, j=j+110) {
      g.drawImage(shipLives,convert(j),convert(842),null);
    }
  }
  public int convert(int d){
		//return (int)((d/960)*(this.getHeight()-50));
		return (int)(((double)d/(double)960)*(run.getSize()));
	}
  private ImageIcon resize(ImageIcon img, int height) {
    Image image = img.getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
  }
}
