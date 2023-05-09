import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

public class PlayGame extends JPanel implements MouseListener, MouseMotionListener{
  private int x;
  private int y;
  private int lives;
  private int wave;
  private int score;
  private int highScore;
  private int shipNum;
  private final String path = "images\\";
  private boolean right;
  private boolean left;
  private boolean waveStart;
  private boolean gameOver;
  private boolean menuHover;
  private GameEngine run;
  private PlayerShip ship;
  private ShieldShip shield1;
  private ShieldShip shield2;
  private ShieldShip shield3;
  private String shipName;
  private String back;
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
  private Image end;
  private ArrayList <Image> explosions = new ArrayList();
  private ArrayList <Integer> xExplosion = new ArrayList();
  private ArrayList <Integer> yExplosion = new ArrayList();
  private int height;
  private ShieldShip [] shields;
  private Image explosion1;
  private Image explosion2;
  private ArrayList<Rectangle> bullets = new ArrayList<>();
  private ArrayList<Rectangle> enemyBullets = new ArrayList<>();
  private Font font;
  public PlayGame(GameEngine engine, PlayerShip player, String background, int maxScore) {
    gameOver = false;
    this.setFocusable(true);
    this.requestFocusInWindow();
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
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
    enemyBullets.clear();
    bullets.clear();
    explosions.clear();
    setRowSpeeds(1);
    shipLives = resize(new ImageIcon(path+(new PlayerShip(ship.getShip(), 1)).getName()),run.getSize()/7).getImage();
    backG = resize(new ImageIcon(path+background),run.getSize()).getImage();
    back = background;
    enemyOne = resize(new ImageIcon(path+"enemy-type3.png"),run.getSize()/9).getImage();
    enemyTwo = resize(new ImageIcon(path+"enemy-type2.png"),run.getSize()/9).getImage();
    enemyThree = resize(new ImageIcon(path+"enemy-type1.png"),run.getSize()/9).getImage();
    end = resize(new ImageIcon(path+"endscreen.png"),run.getSize()).getImage();
    boss = resize(new ImageIcon(path+"shield14.png"),run.getSize()/5).getImage();
    right = true;
    left = false;
    menuHover = false;
    explosion1 = resize(new ImageIcon(path+"explosion.png"),run.getSize()/8).getImage();
    explosion2 = resize(new ImageIcon(path+"explosion.png"),run.getSize()/4).getImage();
    switchShip(false);
    font = new Font("fonts\\minecraft_font.ttf", Font.TRUETYPE_FONT, 40);
  }
  public int getEneCount() {
    int count = 0;
    for(int i=0; i<10; i++) {
      if(one.getEnemy(i).getLife()) {
        count++;
      }
      if(two.getEnemy(i).getLife()) {
        count++;
      }
      if(three.getEnemy(i).getLife()) {
        count++;
      }
      if(four.getEnemy(i).getLife()) {
        count++;
      }
      if(five.getEnemy(i).getLife()) {
        count++;
      }
    }
    return count;
  }
  public void setRowSpeeds(int num) {
    one.setSpeed(num);
    two.setSpeed(num);
    three.setSpeed(num);
    four.setSpeed(num);
    five.setSpeed(num);
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
      } 
      if((one.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && one.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, one);
      } 
      if((one.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife() && one.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, one);
      } 
      if((two.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && two.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, two);
      } 
      if((two.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && two.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, two);
      } 
      if((two.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife()&& two.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, two);
      } 
      if((three.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && three.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, three);
      } 
      if((three.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && three.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, three);
      }
      if((three.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife() && three.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, three);
      } 
      if((four.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && four.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, four);
      } 
      if((four.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && four.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, four);
      } 
      if((four.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife() && four.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, four);
      } 
      if((five.getEnemy(i).hitbox()).intersects(shield1.getHitBox()) && shield1.getLife() && five.getEnemy(i).getLife()) {
        eneShieldCollided(1, i, five);
      } 
      if((five.getEnemy(i).hitbox()).intersects(shield2.getHitBox()) && shield2.getLife() && five.getEnemy(i).getLife()) {
        eneShieldCollided(2, i, five);
      } 
      if((five.getEnemy(i).hitbox()).intersects(shield3.getHitBox()) && shield3.getLife() && five.getEnemy(i).getLife()) {
        eneShieldCollided(3, i, five);
      }
    }
    for(int i=0; i<10; i++) {
      for(int j=0;j<bullets.size();j++){
        Rectangle bullet = bullets.get(j);
        if(bullets.size()>0 && j<bullets.size() && (one.getEnemy(i).hitbox()).intersects(bullet) && one.getEnemy(i).getLife()) {
          score+=30;
          bullets.remove(j);
          one.getEnemy(i).setLife(false);
          one.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,one),getEneY(i,one));
        } 
        if(bullets.size()>0 && j<bullets.size() && (two.getEnemy(i).hitbox()).intersects(bullet) && two.getEnemy(i).getLife()) {
          score+=20;
          bullets.remove(j);
          two.getEnemy(i).setLife(false);
          two.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,two),getEneY(i,two));
        } 
        if(bullets.size()>0 && j<bullets.size() && (three.getEnemy(i).hitbox()).intersects(bullet) && three.getEnemy(i).getLife()) {
          score+=20;
          bullets.remove(j);
          three.getEnemy(i).setLife(false);
          three.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,three),getEneY(i,three));
        } 
        if(bullets.size()>0 && j<bullets.size() && (four.getEnemy(i).hitbox()).intersects(bullet) && four.getEnemy(i).getLife()) {
          score+=10;
          bullets.remove(j);
          four.getEnemy(i).setLife(false);
          four.getEnemy(i).setHitBox(0, 0, 0, 0);
          addToArrays(explosion1,getEneX(i,four),getEneY(i,four));
        } 
        if(bullets.size()>0 && j<bullets.size() && (five.getEnemy(i).hitbox()).intersects(bullet) && five.getEnemy(i).getLife()) {
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
        if(bullets.size()>0 && j<bullets.size() && shield1.getLife()) {
          bullets.remove(j);
          if(shield1.getHealth()==0) {
            addToArrays(explosion2,convert(90),convert(555));
          }
        }
      } 
      if(shield2.getHitBox().intersects(bullet) && shield2.getLife()) {
        shieldShot(2);
        if(bullets.size()>0 && j<bullets.size() && shield2.getLife()) {
          bullets.remove(j);
          if(shield2.getHealth()==0) {
            addToArrays(explosion2,convert(352),convert(555));
          }
        }
      } 
      if(shield3.getHitBox().intersects(bullet) && shield3.getLife()) {
        shieldShot(3);
        if(bullets.size()>0 && j<bullets.size() && shield3.getLife()) {
          bullets.remove(j);
          if(shield3.getHealth()==0) {
            addToArrays(explosion2,convert(614),convert(555));
          }
        }
      }
    }
    for(int j=0;j<enemyBullets.size();j++){
      Rectangle bullet = enemyBullets.get(j);
      if((shield1.getHitBox()).intersects(bullet) && shield1.getLife()) {
        shieldShot(1);
        if(enemyBullets.size()>0 && j<enemyBullets.size() && shield1.getLife()) {
          enemyBullets.remove(j);
          if(shield1.getHealth()==0) {
            addToArrays(explosion2,convert(100),convert(545));
          }
        }
      } 
      if(shield2.getHitBox().intersects(bullet) && shield2.getLife()) {
        shieldShot(2);
        if(enemyBullets.size()>0 && j<enemyBullets.size() && shield2.getLife()) {
          enemyBullets.remove(j);
          if(shield2.getHealth()==0) {
            addToArrays(explosion2,convert(362),convert(545));
          }
        }
      } 
      if(shield3.getHitBox().intersects(bullet) && shield3.getLife()) {
        shieldShot(3);
        if(enemyBullets.size()>0 && j<enemyBullets.size() && shield3.getLife()) {
          enemyBullets.remove(j);
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
          addToArrays(explosion1,convert(x),convert(y));
          one.getEnemy(j).setLife(false);
        } 
        if(((ship.getHitbox()).intersects(two.getEnemy(j).hitbox())) && two.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,two),getEneY(j,two));
          addToArrays(explosion1,convert(x),convert(y));
          two.getEnemy(j).setLife(false);
        } 
        if(((ship.getHitbox()).intersects(three.getEnemy(j).hitbox())) && three.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,three),getEneY(j,three));
          addToArrays(explosion1,convert(x),convert(y));
          three.getEnemy(j).setLife(false);
        } 
        if(((ship.getHitbox()).intersects(four.getEnemy(j).hitbox())) && four.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,four),getEneY(j,four));
          addToArrays(explosion1,convert(x),convert(y));
          four.getEnemy(j).setLife(false);
        } 
        if(((ship.getHitbox()).intersects(five.getEnemy(j).hitbox())) && five.getEnemy(j).getLife()) {
          lives--;
          addToArrays(explosion1,getEneX(j,five),getEneY(j,five));
          addToArrays(explosion1,convert(x),convert(y));
          five.getEnemy(j).setLife(false);
        }
      }
    }
    for(int j=0;j<enemyBullets.size();j++){
      if(lives>0) {
        if(enemyBullets.size()>0 && j<enemyBullets.size() && (ship.getHitbox()).intersects(enemyBullets.get(j))) {
          lives--;
          addToArrays(explosion1,convert(x),convert(y));
          enemyBullets.remove(j);
        }
      }
    }
    for(int i=0; i<bullets.size(); i++) {
      for(int j=0; j<enemyBullets.size(); j++) {
        if(i<bullets.size() && j<enemyBullets.size() && bullets.size()>0 && enemyBullets.size()>0 && bullets.get(i).intersects(enemyBullets.get(j))) {
          bullets.remove(i);
          enemyBullets.remove(j);
        }
      }
    }
  }
  public boolean colAlive(int col) {
    if(one.getEnemy(col).getLife() || two.getEnemy(col).getLife() || three.getEnemy(col).getLife() || four.getEnemy(col).getLife() || five.getEnemy(col).getLife()) {
      return true;
    }
    return false;
  }
  public Enemy getLiveEnemy(int col) {
    if(one.getEnemy(col).getLife()) {
      return one.getEnemy(col);
    } else if(two.getEnemy(col).getLife()) {
      return two.getEnemy(col);
    } else if(three.getEnemy(col).getLife()) {
      return three.getEnemy(col);
    } else if(four.getEnemy(col).getLife()) {
      return four.getEnemy(col);
    } else {
      return five.getEnemy(col);
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
  public int eneRightBound() {
    for(int i=one.getSize()-1; i>=0; i--) {
      if(colAlive(i)) {
        return getLiveEnemy(i).getX();
      }
    }
    return one.getX();
  }
  public int eneLeftBound() {
    for(int i=0; i<one.getSize(); i++) {
      if(colAlive(i)) {
        return getLiveEnemy(i).getX();
      } 
    }
    return one.getX();
  }
  public int eneYBound() {
    return rowLife().getY();
  }
  public boolean getOver() {
    return gameOver;
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
  public void eneBullet(){
    int randomRow = (int)(Math.random()*5)+1;
    EnemyRow row = one;
    if(randomRow==1) {
      row = one;
    } else if(randomRow==2) {
      row = two;
    } else if(randomRow==3) {
      row = three;
    } else if(randomRow==4) {
      row = three;
    } else {
      row = five;
    }
    int randomCol = (int)(Math.random()*10)+1;
    if(row.getEnemy(randomCol-1).getLife()) {
      Rectangle r = new Rectangle(getEneX(randomCol-1, row) + convert(57), getEneY(randomCol-1, row) + convert(40), convert(7), convert(30));
      enemyBullets.add(r);
    }
  }
  public ArrayList<Rectangle> getBullets(){
    return bullets;
  }
  public ArrayList<Rectangle> getEneBullets(){
    return enemyBullets;
  }
  public int getLives(){
    return lives;
  }
  public void allRowsdead(){
    if(one.allDead() && two.allDead() && three.allDead() && four.allDead() && five.allDead()){
      one = new EnemyRow(3, 10 ,1);
      two = new EnemyRow(2, 10 ,2);
      three = new EnemyRow(2, 10 ,3);
      four = new EnemyRow(1, 10 ,4);
      five = new EnemyRow(1, 10 ,5);
      wave++;
      waveStart = true;
      bullets.clear();
      enemyBullets.clear();
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backG, convert(0),convert(0),null);
    g.drawImage(myPlayer,convert(x),convert(y),null);
    if(lives>0) {
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
    }
    //g.drawRect(ship.hitX(), ship.hitY(), ship.getWidth(), ship.getHeight());
    // g.drawImage(end,convert(0),convert(0),null);
    // ship.getHitBox().add(x, y);
    for(int i=1, j=125; i<=lives; i++, j=j+110) {
      g.drawImage(shipLives,convert(j),convert(842),null);
    }
    g.setColor(Color.white);
    for(Rectangle bullet: bullets){
      g.fillRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight());
    }
    g.setColor(Color.red);
    for(Rectangle bullet: enemyBullets){
      g.fillRect((int)bullet.getX(), (int)bullet.getY(), (int)bullet.getWidth(), (int)bullet.getHeight());
    }
    if(explosions.size()>0) {
      g.drawImage(explosions.get(0), xExplosion.get(0), yExplosion.get(0),null);
      // try{
      //   Thread.sleep( 100 );
      // }
      // catch (InterruptedException ex) { }
      
      removeArrays();
      // 
    }
    g.setColor(Color.WHITE);
    g.setFont(font);
    g.drawString(Integer.toString(highScore), convert(684),convert(950));
    g.drawString(Integer.toString(wave), convert(800),convert(47));
    g.drawString(Integer.toString(score), convert(160),convert(47));
    if(lives==0){
      gameOver = true;
      run.addScore(score);
      g.setColor(Color.WHITE);
      g.setFont(font);
      g.drawString(Integer.toString(run.getHighScore()), convert(475-Integer.toString(run.getHighScore()).length()*10),convert(567));
      g.drawImage(explosion1,convert(x),convert(y),null);
      bullets.clear();
      enemyBullets.clear();
      if(menuHover) {
        g.drawImage(resize(new ImageIcon(path+"endscreenhovered.png"),run.getSize()).getImage(),convert(0),convert(0),null);
      } else {
        g.drawImage(end,convert(0),convert(0),null);
      }
    }
    if(waveStart) {
      // waveStart = false;
      // try{
      //   wait( 5000 );
      // }
      // catch (InterruptedException ex) { }
    }
    waveStart = false;
  }
  public int convert(int d){
		//return (int)((d/960)*(this.getHeight()-50));
		return (int)(((double)d/(double)960)*(run.getSize()));
	}
  private ImageIcon resize(ImageIcon img, int height) {
    Image image = img.getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
  }
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    if(gameOver){
      if(e.getX()<=convert(395+175) && e.getX()>=convert(395) && e.getY()<=convert(710) && e.getY()>=convert(635)){
        run.setMenu(run, ship, back, run.getHighScore());
      }
    }
  }
  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
  }
  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    if(gameOver){
      if(e.getX()<=convert(395+175) && e.getX()>=convert(395) && e.getY()<=convert(710) && e.getY()>=convert(635)){
        menuHover = true;
      } else {
        menuHover = false;
      }
    }
  }
}
