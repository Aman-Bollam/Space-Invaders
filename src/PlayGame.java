import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
public class PlayGame extends JPanel implements KeyListener {
  private final String path = "images\\";
  private GameEngine run;
  public PlayGame(GameEngine engine) {
		this.setFocusable(true);
		this.addKeyListener(null);
    run = engine;
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(resize(new ImageIcon(path + "BlackHole.png"), this.getHeight()).getImage(), 0, 0, null);
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
}
