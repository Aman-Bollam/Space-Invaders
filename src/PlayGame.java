import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class PlayGame extends JPanel implements ActionListener, KeyListener {
  private int x;
  private int y;
  private int lives;
  private final String path = "images\\";
  private GameEngine run;
  private PlayerShip ship;
  private Timer moving;
  public PlayGame(GameEngine engine, PlayerShip player) {
		this.setFocusable(true);
    this.addKeyListener(this);
    run = engine;
    ship = player;
    x = 407;
    y = 725;
    lives = 3;
    moving = new Timer(0, this);
    // moving.start();
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(resize(new ImageIcon(path+"background7.jpg"),this.getHeight()).getImage(), convert(0),convert(0),null);
    // g.drawImage(resize(new ImageIcon(path+"enemy-type3.png"),this.getHeight()/7).getImage(), convert(50),convert(347),null);
    g.drawImage(resize(new ImageIcon(path+ship.getName()),this.getHeight()/7).getImage(),convert(x),convert(y),null);
    for(int i=1, j=125; i<=lives; i++, j=j+110) {
      g.drawImage(resize(new ImageIcon(path+ship.getName()),this.getHeight()/7).getImage(),convert(j),convert(842),null);
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

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    x++;
    this.repaint();
  }
  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("hi");
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
}
