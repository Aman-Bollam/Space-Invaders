import java.awt.*;

import javax.swing.*;
public class PlayGame extends JPanel{
  private int x;
  private int y;
  private int lives;
  private final String path = "images\\";
  private GameEngine run;
  private PlayerShip ship;
  private String shipName;
  private EnemyRow one;
  private EnemyRow two;
  private EnemyRow three;
  private EnemyRow four;
  private EnemyRow five;
  private Timer enemyMove;
  private int enemyNum;
  private Image shipLives;
  private Image myPlayer;
  private Image backG;
  private Image enemyOne;
  private Image enemyTwo;
  private Image enemyThree;
  private Image boss;
  private int height;
  public PlayGame(GameEngine engine, PlayerShip player) {
    this.setFocusable(true);
    this.requestFocusInWindow();
    run = engine;
    ship = new PlayerShip(player.getShip(), 2);
    one = new EnemyRow(3, 10  ,1);
    two = new EnemyRow(2, 10  ,2);
    three = new EnemyRow(2, 10  ,3);
    four = new EnemyRow(1, 10  ,4);
    five = new EnemyRow(1, 10  ,5);
    lives = 3;
    x = ship.getX();
    y = ship.getY();
    enemyNum = 50;
    shipName = ship.getName();
    shipLives = resize(new ImageIcon(path+(new PlayerShip(ship.getShip(), 1)).getName()),run.getSize()/7).getImage();
    backG = resize(new ImageIcon(path+"background6.jpg"),run.getSize()).getImage();
    enemyOne = resize(new ImageIcon(path+"enemy-type3.png"),run.getSize()/9).getImage();
    enemyTwo = resize(new ImageIcon(path+"enemy-type2.png"),run.getSize()/9).getImage();
    enemyThree = resize(new ImageIcon(path+"enemy-type1.png"),run.getSize()/9).getImage();
    // boss = resize(new ImageIcon(path+"shield.png"),run.getSize()/9).getImage();
    boss = resize(new ImageIcon(path+"shield1.png"),run.getSize()/6).getImage();
    switchShip(false);
  }
  public void setPosRight() {
    if(x+3<=convert(run.getSize())+136){
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
    // g.drawImage(boss,convert(200),convert(40),null);
    g.drawImage(boss,convert(388),convert(590),null);
    for(int i=0; i<one.getSize(); i++) {
      if(one.getLife(i)) {
        g.drawImage(enemyOne,getEneX(i,one),getEneY(i,one),null);
      }
    }
    for(int i=0; i<two.getSize(); i++) {
      if(two.getLife(i)) {
        g.drawImage(enemyTwo,getEneX(i,two),getEneY(i,two),null);
      }
    }
    for(int i=0; i<two.getSize(); i++) {
      if(three.getLife(i)) {
        g.drawImage(enemyTwo,getEneX(i,three),getEneY(i,three),null);
      }
    }
    for(int i=0; i<two.getSize(); i++) {
      if(four.getLife(i)) {
        g.drawImage(enemyThree,getEneX(i,four),getEneY(i,four),null);
      }
    }
    for(int i=0; i<two.getSize(); i++) {
      if(five.getLife(i)) {
        g.drawImage(enemyThree,getEneX(i,five),getEneY(i,five),null);
      }
    }
    for(int i=1, j=125; i<=lives; i++, j=j+110) {
      g.drawImage(shipLives,convert(j),convert(842),null);
    }
  }
  public int convert(int d){
		//return (int)((d/960)*(this.getHeight()-50));
		return (int)(((double)d/(double)960)*(this.getHeight()));
	}
  private ImageIcon resize(ImageIcon img, int height) {
    Image image = img.getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
  }
}
