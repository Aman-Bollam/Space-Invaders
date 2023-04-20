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
  private int height;
  public PlayGame(GameEngine engine, PlayerShip player) {
    this.setFocusable(true);
    this.requestFocusInWindow();
    run = engine;
    ship = new PlayerShip(player.getShip(), 2);
    one = new EnemyRow(3, 10  ,1);
    lives = 3;
    x = ship.getX();
    y = ship.getY();
    enemyNum = 50;
    shipName = ship.getName();
    shipLives = resize(new ImageIcon(path+(new PlayerShip(ship.getShip(), 1)).getName()),run.getSize()/7).getImage();
    backG = resize(new ImageIcon(path+"background9.jpg"),run.getSize()).getImage();
    switchShip(false);
  }
  public void setPosRight() {
    if(x+3<=convert(run.getSize())){
      System.out.println(this.getHeight());
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
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backG, convert(0),convert(0),null);
    g.drawImage(myPlayer,convert(x),convert(y),null);
    // g.drawImage(resize(new ImageIcon(path+"enemy-type3.png"),this.getHeight()/7).getImage(), convert(50),convert(347),null);
    for(int i=0; i<one.getSize(); i++) {
      g.drawImage(getEnemyImage(i),convert((one.getEnemy(i)).getX()),convert((one.getEnemy(i)).getY()),null);
    }
    for(int i=1, j=125; i<=lives; i++, j=j+110) {
      g.drawImage(shipLives,convert(j),convert(842),null);
    }
  }
  public Image getEnemyImage(int num) {
    return resize(new ImageIcon(path+(one.getEnemy(num)).getName()),this.getHeight()/9).getImage();
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
