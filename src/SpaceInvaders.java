import java.awt.*;
//import java.util.*;
//import java.awt.image.*;
import javax.swing.*;
public class SpaceInvaders {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        JFrame frame = new JFrame("Galaxy Test on VScode");
        JPanel panel = new JPanel();
        ImageIcon bg = new ImageIcon("galaxy1.png");
        Image image = bg.getImage();
        Image newImage = image.getScaledInstance(500,500,java.awt.Image.SCALE_SMOOTH);
        bg = new ImageIcon(newImage);

        JLabel label = new JLabel();
        label.setIcon(bg);
        panel.add(label);
        
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
