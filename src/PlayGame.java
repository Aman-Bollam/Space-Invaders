import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
public class PlayGame extends JPanel{
  private int x;
  private int y;
  private int lives;
  private int wave;
  private int score;
  private int highScore;
  private final String path = "images\\";
  private boolean right;
  private boolean left;
  private boolean waveStart;
  private GameEngine run;
  private PlayerShip ship;
  private int shipNum;
  private ShieldShip shield1;
  private ShieldShip shield2;
  private ShieldShip shield3;
  private String shipName;
  private EnemyRow one;
  private EnemyRow two;
  private EnemyRow three;
  private EnemyRow four;
  private EnemyRow five;
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
  private Image explosion1;
  private Image explosion2;
  private ArrayList<Rectangle> bullets = new ArrayList<>();
  private Font font;
  public PlayGame(GameEngine engine, PlayerShip player, String background, int maxScore) {
    this.setFocusable(true);
    this.requestFocusInWindow();
    run = engine;
    ship = new PlayerShip(player.getShip(), 2);
    shipNum = player.getShip();
    one = new EnemyRow(3, 10 ,1);
    two = new EnemyRow(2, 10 ,2);
    three = new EnemyRow(2, 10 ,3);
    four = new EnemyRow(1, 10 ,4);
    five = new EnemyRow(1, 10 ,5);
    lives = 3;
    x = ship.getX();
    y = ship.getY();
    waveStart = true;
    highScore = maxScore;
    wave = 1;
    shipName = ship.getName();
    shield1 = new ShieldShip(convert(155), convert(628), convert(100), convert(63));
    shield2 = new ShieldShip(convert(417), convert(628), convert(100), convert(63));
    shield3 = new ShieldShip(convert(679), convert(628), convert(100), convert(63));
    setShields();
    shipLives = resize(new ImageIcon(path+(new PlayerShip(ship.getShip(), 1)).getName()),run.getSize()/7).getImage();
    backG = resize(new ImageIcon(path+background),run.getSize()).getImage();
    enemyOne = resize(new ImageIcon(path+"enemy-type3.png"),run.getSize()/9).getImage();
    enemyTwo = resize(new ImageIcon(path+"enemy-type2.png"),run.getSize()/9).getImage();
    enemyThree = resize(new ImageIcon(path+"enemy-type1.png"),run.getSize()/9).getImage();
    boss = resize(new ImageIcon(path+"shield14.png"),run.getSize()/5).getImage();
    right = true;
    left = false;
    explosion1 = resize(new ImageIcon(path+"explosion.png"),run.getSize()/8).getImage();
    explosion2 = resize(new ImageIcon(path+"explosion.png"),run.getSize()/3).getImage();
    switchShip(false);
    font = new Font("fonts\\minecraft_font.ttf", Font.TRUETYPE_FONT, 40);
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
  public void setPlayerBox() {
    if(shipNum==0) {
      ship.setHitBox(getPlayerX()+convert(35), getPlayerY()+convert(10), convert(50), convert(68));
    } else if(shipNum==1) {
      ship.setHitBox(getPlayerX()+convert(35), getPlayerY()+convert(13), convert(50), convert(73));
    } else if(shipNum==2) {
      ship.setHitBox(getPlayerX()+convert(32), getPlayerY()+convert(25), convert(55), convert(52));
    } else if(shipNum==3) {
      ship.setHitBox(getPlayerX()+convert(32), getPlayerY()+convert(29), convert(55), convert(45));
    } else if(shipNum==4) {
      ship.setHitBox(getPlayerX()+convert(32), getPlayerY()+convert(24), convert(55), convert(55));
    } else if(shipNum==5) {
      ship.setHitBox(getPlayerX()+convert(32), getPlayerY()+convert(21), convert(55), convert(57));
    } else if(shipNum==6) {
      ship.setHitBox(getPlayerX()+convert(28), getPlayerY()+convert(26), convert(64), convert(55));
    } else if(shipNum==7) {
      ship.setHitBox(getPlayerX()+convert(32), getPlayerY()+convert(32), convert(55), convert(66));
    } else if(shipNum==8) {
      ship.setHitBox(getPlayerX()+convert(27), getPlayerY()+convert(33), convert(67), convert(52));
    } 
  }
  public void detectCollisions() {
    for(int i=0; i<10; i++) {
      if((one.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && one.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, one);
      } else if((one.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && one.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, one);
      } else if((one.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife() && one.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, one);
      } else if((two.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && two.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, two);
      } else if((two.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && two.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, two);
      } else if((two.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife()&& two.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, two);
      } else if((three.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && three.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, three);
      } else if((three.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && three.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, three);
      } else if((three.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife() && three.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, three);
      } else if((four.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && four.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, four);
      } else if((four.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && four.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, four);
      } else if((four.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife() && four.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, four);
      } else if((five.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && five.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, five);
      } else if((five.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && five.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, five);
      } else if((five.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife() && five.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, five);
      }
    }
    for(int i=0; i<10; i++) {
      for(int j=0;j<bullets.size();j++){
        Rectangle bullet = bullets.get(j);
        if((one.getEnemy(i).hitbox()).intersects(bullet) && one.getEnemy(i).getLife()) {
          score+=30;
          bullets.remove(j);
          one.getEnemy(i).setLife(false);
          one.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,one),getEneY(i,one));
        } else if((two.getEnemy(i).hitbox()).intersects(bullet) && two.getEnemy(i).getLife()) {
          score+=20;
          bullets.remove(j);
          two.getEnemy(i).setLife(false);
          two.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,two),getEneY(i,two));

        } else if((three.getEnemy(i).hitbox()).intersects(bullet) && three.getEnemy(i).getLife()) {
          score+=20;
          bullets.remove(j);
          three.getEnemy(i).setLife(false);
          three.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,three),getEneY(i,three));
        } else if((four.getEnemy(i).hitbox()).intersects(bullet) && four.getEnemy(i).getLife()) {
          score+=10;
          bullets.remove(j);
          four.getEnemy(i).setLife(false);
          four.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,four),getEneY(i,four));
        } else if((five.getEnemy(i).hitbox()).intersects(bullet) && five.getEnemy(i).getLife()) {
          score+=10;
          bullets.remove(j);
          five.getEnemy(i).setLife(false);
          five.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,five),getEneY(i,five));
        }
      }
    }
    for(int j=0;j<bullets.size();j++){
      Rectangle bullet = bullets.get(j);
      if((shield1.getHitBox()).intersects(bullet) && shield1.getLife()) {
        shieldShot(1);
        if(shield1.getLife()) {
          bullets.remove(j);
          if(shield1.getHealth()==0) {
            addToArrays(explosion2,convert(100),convert(545));
          }
        }
      } else if(shield2.getHitBox().intersects(bullet) && shield2.getLife()) {
        shieldShot(2);
        if(shield2.getLife()) {
          bullets.remove(j);
          if(shield2.getHealth()==0) {
            addToArrays(explosion2,convert(362),convert(545));
          }
        }
      } else if(shield3.getHitBox().intersects(bullet) && shield3.getLife()) {
        shieldShot(3);
        if(shield3.getLife()) {
          bullets.remove(j);
          if(shield3.getHealth()==0) {
            addToArrays(explosion2,convert(624),convert(545));
          }
        }
      }
    }
    for(int j=0;j<10;j++){
      if(lives>0) {
        if(((ship.getHitbox()).intersects(one.getEnemy(j).hitbox())) && one.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,one),getEneY(j,one));
          one.getEnemy(j).setLife(false);
        } else if(((ship.getHitbox()).intersects(two.getEnemy(j).hitbox())) && two.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,two),getEneY(j,two));
          two.getEnemy(j).setLife(false);
        } else if(((ship.getHitbox()).intersects(three.getEnemy(j).hitbox())) && three.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,three),getEneY(j,three));
          three.getEnemy(j).setLife(false);
        } else if(((ship.getHitbox()).intersects(four.getEnemy(j).hitbox())) && four.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,four),getEneY(j,four));
          four.getEnemy(j).setLife(false);
        } else if(((ship.getHitbox()).intersects(five.getEnemy(j).hitbox())) && five.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,five),getEneY(j,five));
          five.getEnemy(j).setLife(false);
        }
      }
    }
  }
  public void shieldShot(int shield) {
    if(shield==1) {
      shield1.setHealth();
      shieldOne = resize(new ImageIcon(path+(shield1.getName())),run.getSize()/5).getImage();
    } else if(shield==2) {
      shield2.setHealth();
      shieldTwo = resize(new ImageIcon(path+(shield2.getName())),run.getSize()/5).getImage();
      
    } else {
      shield3.setHealth();
      shieldThree = resize(new ImageIcon(path+(shield3.getName())),run.getSize()/5).getImage();
    }
  }
  public void eneShieldCollided(int shield,int enemy,EnemyRow row) {
    addToArrays(explosion1,getEneX(enemy,row),getEneY(enemy,row));
    row.getEnemy(enemy).setLife(false);
    if(shield==1) {
      shield1.setHealth();
      shieldOne = resize(new ImageIcon(path+(shield1.getName())),run.getSize()/5).getImage();
      if(!(shield1.getLife())) {
        addToArrays(explosion2,convert(100),convert(545));
      }
    } else if(shield==2) {
      shield2.setHealth();
      shieldTwo = resize(new ImageIcon(path+(shield2.getName())),run.getSize()/5).getImage();
      if(!(shield2.getLife())) {
        addToArrays(explosion2,convert(362),convert(545));
      }
    } else {
      shield3.setHealth();
      shieldThree = resize(new ImageIcon(path+(shield3.getName())),run.getSize()/5).getImage();
      if(!(shield3.getLife())) {
        addToArrays(explosion2,convert(624),convert(545));
      }
    }     
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
    if(x+3<=(830)){
      ship.setPosRight();
    }
    x=ship.getX();
  }
  public void setPosLeft() {
    if(x-3>=0){
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
  public int getPlayerY() {
    return convert(ship.getY());
  }
  public int getPlayerX() {
    return convert(ship.getX());
  }
  public void bullet(){
    Rectangle r = new Rectangle(convert(ship.getX()+57), convert(ship.getY()-25), convert(7), convert(30));
    bullets.add(r);
  }
  public ArrayList<Rectangle> getBullets(){
    return bullets;
  }
  public void allRowsdead(){
    if(one.allDead() && two.allDead() && three.allDead() && four.allDead() && five.allDead()){
      one = new EnemyRow(3, 10 ,1);
      two = new EnemyRow(2, 10 ,2);
      three = new EnemyRow(2, 10 ,3);
      four = new EnemyRow(1, 10 ,4);
      five = new EnemyRow(1, 10 ,5);
      wave++;
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backG, convert(0),convert(0),null);
    g.drawImage(myPlayer,convert(x),convert(y),null);
    g.drawRect(ship.hitX(), ship.hitY(), ship.getWidth(), ship.getHeight());
    // ship.getHitBox().add(x, y);
    if(shield1.getLife()) {
      g.drawImage(shieldOne,convert(110),convert(555),null);
    }
    if(shield2.getLife()) {
      g.drawImage(shieldTwo,convert(372),convert(555),null);
      // g.drawRect(shield2.hitX(), shield2.hitY(), shield2.getWidth(), shield2.getHeight());
    }
    if(shield3.getLife()) {
      g.drawImage(shieldThree,convert(634),convert(555),null);
      // g.drawRect(shield3.hitX(), shield3.hitY(), shield3.getWidth(), shield3.getHeight());
    }
    if(explosions.size()>0) {
      g.drawImage(explosions.get(0), xExplosion.get(0), yExplosion.get(0),null);
      removeArrays();  
    }
    if(waveStart) {
      
    }
    for(int i=0; i<one.getSize(); i++) {
      if(one.getLife(i)) {
        g.drawImage(enemyOne,getEneX(i,one),getEneY(i,one),null);
        // g.drawRect(getEneX(i,one)+convert(28), getEneY(i,one)+convert(35), convert(52),convert(28));
      }
    }
    for(int i=0; i<two.getSize(); i++) {
      if(two.getLife(i)) {
        g.drawImage(enemyTwo,getEneX(i,two),getEneY(i,two),null);
      }
    }
    for(int i=0; i<three.getSize(); i++) {
      if(three.getLife(i)) {
        g.drawImage(enemyTwo,getEneX(i,three),getEneY(i,three),null);
      }
    }
    for(int i=0; i<four.getSize(); i++) {
      if(four.getLife(i)) {
        g.drawImage(enemyThree,getEneX(i,four),getEneY(i,four),null);
      }
    }
    for(int i=0; i<five.getSize(); i++) {
      if(five.getLife(i)) {
        g.drawImage(enemyThree,getEneX(i,five),getEneY(i,five),null);
      }
    }
    for(int i=1, j=125; i<=lives; i++, j=j+110) {
      g.drawImage(shipLives,convert(j),convert(842),null);
    }
    g.setColor(Color.white);
    for(Rectangle bullet: bullets){
      g.fillRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight());
    }
    g.setColor(Color.CYAN);
    g.setFont(font);
    g.drawString(Integer.toString(wave), convert(800),convert(47));
    g.drawString(Integer.toString(score), convert(160),convert(47));

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
