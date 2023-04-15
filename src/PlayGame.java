import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.*;
public class PlayGame extends JPanel implements ActionListener{
  private Enemy [][] enemies = new Enemy[5][10];
  private int x;
  private int y;
  private int lives;
  private final String path = "images\\";
  private GameEngine run;
  private PlayerShip ship;
  private String shipName;
  private Timer moving;
  private Timer moving2;
  private Timer moving3;
  private Timer moving4;
  private Timer moving5;
  private Timer moving6;
  private Timer moving7;
  private Timer moving8;
  private Timer moving9;
  private Timer moving10;
  private Image shipLives;
  private Image myPlayer;
  private int enemyNum;
  public PlayGame(GameEngine engine, PlayerShip player) {
    this.setFocusable(true);
    this.requestFocusInWindow();
    run = engine;
    ship = new PlayerShip(player.getShip(), 2);
    lives = 3;
    x = ship.getX();
    y = ship.getY();
    enemyNum = 50;
    shipName = ship.getName();
    moving = new Timer(0, this);
    moving2 = new Timer(0, this);
    moving3 = new Timer(0, this);
    moving4 = new Timer(0, this);
    moving5 = new Timer(0, this);
    moving6 = new Timer(0, this);
    moving7 = new Timer(0, this);
    moving8 = new Timer(0, this);
    moving9 = new Timer(0, this);
    moving10 = new Timer(0, this);
    moving.start();
    moving2.start();
    moving3.start();
    moving4.start();
    moving5.start();
    moving6.start();
    moving7.start();
    moving8.start();
    moving9.start();
    moving10.start();
    
  }
  public void setEnemies() {
    for(int i=0,j=5; i<10; i++, j=j+85) {
      enemies[0][i] = new Enemy(3, true, j, 85);
    }
    for(int j=1, k=160; j<3; j++, k=k+75) {
      for(int i=0, l=5; i<10; i++, l=l+85){
        enemies[j][i] = new Enemy(2, true, l, k);
      }
    }
    for(int j=3, k=310; j<5; j++, k=k+75) {
      for(int i=0, l=5; i<10; i++, l=l+85){
        enemies[j][i] = new Enemy(1, true, l, k);
      }
    }
  }
  public void setPosRight() {
    if(x+3<=convert(960)){
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
  }
  public void actionPerformed(ActionEvent e) {
    run.moveShip();
    this.repaint();    
  }
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    myPlayer = resize(new ImageIcon(path+shipName),this.getHeight()/7).getImage();
    shipLives = resize(new ImageIcon(path+(new PlayerShip(ship.getShip(), 1)).getName()),this.getHeight()/7).getImage();
    g.drawImage(resize(new ImageIcon(path+"background9.jpg"),this.getHeight()).getImage(), convert(0),convert(0),null);
    // g.drawImage(resize(new ImageIcon(path+"enemy-type3.png"),this.getHeight()/7).getImage(), convert(50),convert(347),null);
    g.drawImage(myPlayer,convert(x),convert(y),null);
    for(int i=1, j=125; i<=lives; i++, j=j+110) {
      g.drawImage(shipLives,convert(j),convert(842),null);
    }
    for(int j=0; j<5; j++) {
      for(int i=0; i<10; i++){
        g.drawImage(resize(new ImageIcon(path+(enemies[j][i]).getName()),this.getHeight()/7).getImage(), convert((enemies[j][i]).getX()),convert((enemies[j][i]).getY()),null);
      }
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
