import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.*;
public class PlayGame extends JPanel implements ActionListener{
  int x;
  private int y;
  private int lives;
  private final String path = "images\\";
  private GameEngine run;
  private PlayerShip ship;
  private Timer moving;
  public PlayGame(GameEngine engine, PlayerShip player) {
    this.setFocusable(true);
    this.requestFocusInWindow();
    run = engine;
    ship = new PlayerShip(player.getShip(), 2);
    x = 407;
    y = 725;
    lives = 3;
    moving = new Timer(100, this);
    moving.start();
  }
  public void setPosRight() {
    x+=10;
  }
  public void setPosLeft() {
    x-=10;
  }
  public void setShip(PlayerShip myShip) {
    ship = myShip;
  }
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    this.repaint();
  }
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    PlayerShip life = new PlayerShip(ship.getShip(), 1);
    g.drawImage(resize(new ImageIcon(path+"background3.jpg"),this.getHeight()).getImage(), convert(0),convert(0),null);
    // g.drawImage(resize(new ImageIcon(path+"enemy-type3.png"),this.getHeight()/7).getImage(), convert(50),convert(347),null);
    g.drawImage(resize(new ImageIcon(path+ship.getName()),this.getHeight()/7).getImage(),convert(x),convert(y),null);
    // for(int i=1, j=125; i<=lives; i++, j=j+110) {
    //   g.drawImage(resize(new ImageIcon(path+life.getName()),this.getHeight()/7).getImage(),convert(j),convert(842),null);
    // }
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
