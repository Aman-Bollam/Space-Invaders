import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
public class PlayGame extends JPanel implements KeyListener {
  private int x;
  private int y;
  private final String path = "images\\";
  private GameEngine run;
  private PlayerShip ship;
  public PlayGame(GameEngine engine, PlayerShip player) {
		this.setFocusable(true);
		this.addKeyListener(null);
    run = engine;
    ship = player;
    x = 407;
    y = 650;
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(resize(new ImageIcon(path+"background6.jpg"),this.getHeight()).getImage(), convert(0),convert(0),null);
    // g.drawImage(resize(new ImageIcon(path+"enemy-type3.png"),this.getHeight()/7).getImage(), convert(50),convert(347),null);
    g.drawImage(resize(new ImageIcon(path+ship.getName()),this.getHeight()/7).getImage(),convert(x),convert(y),null);
  }
  private ImageIcon resize(ImageIcon img, int height) {
    Image image = img.getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH);
    return new ImageIcon(image);
  }
  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
  }
  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }
  public int convert(int d){
		//return (int)((d/960)*(this.getHeight()-50));
		return (int)(((double)d/(double)960)*(this.getHeight()));
	}
}
