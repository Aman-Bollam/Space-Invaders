import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Settings extends JPanel implements MouseListener{
    private boolean backPage;
    private boolean points;
    private boolean instructions;
    private final String path = "images\\";
    private PlayerShip ship;
    private String back;
    private String [] backgrounds = {"background1.png","background2.png","background3.png","background4.png","background5.png","background6.png","background7.png","background8.png","background9.png","background10.png","background11.png"};
    private String [] backChoices = {"settingback1.png","settingback2.png","settingback3.png","settingback4.png","settingback5.png","settingback6.png","settingback7.png","settingback8.png","settingback9.png","settingback10.png","settingback11.png"};
    public Settings(GameEngine run, PlayerShip theShip, String background) {
        this.setFocusable(true);
		this.addMouseListener(this);
        backPage = false;
        points = false;
        instructions = true;
        ship = theShip;
        back = background;
    }
    public void paintComponent (Graphics g) {
		super.paintComponent(g);
        if(instructions) {
            g.drawImage(resize(new ImageIcon(path+"settingscontrols.png"), this.getHeight()).getImage(),0,0,null);
        } else if(points) {
            g.drawImage(resize(new ImageIcon(path+"settingspoints.png"), this.getHeight()).getImage(),0,0,null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(convert(e.getX())<=115 && convert(e.getX())>=61 && convert(e.getY())<=189 && convert(e.getY())>=55 && !backPage) {
            backPage = false;
            points = false;
            instructions = true;
            repaint();
        } else if(convert(e.getX())<=115 && convert(e.getX())>=61 && convert(e.getY())<=325 && convert(e.getY())>=194 && !backPage) {
            backPage = false;
            points = true;
            instructions = false;
            repaint();
        } else if(convert(e.getX())<=115 && convert(e.getX())>=61 && convert(e.getY())<=511 && convert(e.getY())>=331 && !backPage) {
            backPage = true;
            points = false;
            instructions = false;
            repaint();
        }
        System.out.println(convert(e.getY()));
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
    public int convert(int d){
		//return (int)((d/960)*(this.getHeight()-50));
		return (int)(((double)d/(double)960)*(this.getHeight()));
	}
	private ImageIcon resize(ImageIcon img, int height) {
		Image image = img.getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	private ImageIcon resizeSettings(ImageIcon img) {
		Image image = img.getImage().getScaledInstance(convert(img.getIconWidth()), convert(img.getIconHeight()), Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
}
