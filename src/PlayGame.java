import java.awt.*;
import javax.swing.*;

public class PlayGame extends JPanel{
    public void paintComponent (Graphics g) {
		super.paintComponent(g);
        
    }
    private ImageIcon resize(ImageIcon img, int height) {
      Image image = img.getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH);
      return new ImageIcon(image);
    }
}
