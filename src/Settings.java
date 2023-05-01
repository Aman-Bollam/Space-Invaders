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
    private GameEngine game;
    private int backNum;
    private String [] backgrounds = {"background1.jpg","background2.jpg","background3.jpg","background4.jpg","background5.jpg","background6.jpg","background7.jpg","background8.jpg","background9.jpg","background10.jpg","background11.jpg"};
    private String [] backChoices = {"settingback1.png","settingback2.png","settingback3.png","settingback4.png","settingback5.png","settingback6.png","settingback7.png","settingback8.png","settingback9.png","settingback10.png","settingback11.png"};
    public Settings(GameEngine run, PlayerShip theShip, String background) {
        this.setFocusable(true);
		this.addMouseListener(this);
        backPage = false;
        points = false;
        instructions = true;
        ship = theShip;
        back = background;
        game = run;
        backNum = matchBackgrounds(background);
    }
    public int matchBackgrounds(String background) {
        int num = 0;
        for(int i=0; i<backgrounds.length; i++) {
            if(background.equals(backgrounds[i])) {
                return i;
            }
        }
        return num;
    }
    public void paintComponent (Graphics g) {
		super.paintComponent(g);
        if(instructions) {
            g.drawImage(resize(new ImageIcon(path+"settingscontrols.png"), this.getHeight()).getImage(),0,0,null);
        } else if(points) {
            g.drawImage(resize(new ImageIcon(path+"settingspoints.png"), this.getHeight()).getImage(),0,0,null);
        } else if(backPage) {
            g.drawImage(resize(new ImageIcon(path+backChoices[backNum]), this.getHeight()).getImage(),0,0,null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(convert(e.getX())<=115 && convert(e.getX())>=61 && convert(e.getY())<=189 && convert(e.getY())>=55) {
            backPage = false;
            points = false;
            instructions = true;
            repaint();
        } else if(convert(e.getX())<=115 && convert(e.getX())>=61 && convert(e.getY())<=325 && convert(e.getY())>=194) {
            backPage = false;
            points = true;
            instructions = false;
            repaint();
        } else if(convert(e.getX())<=115 && convert(e.getX())>=61 && convert(e.getY())<=511 && convert(e.getY())>=331) {
            backPage = true;
            points = false;
            instructions = false;
            repaint();
        } else if(convert(e.getX())<=115 && convert(e.getX())>=61 && convert(e.getY())<=631 && convert(e.getY())>=516) {
            game.setMenu(game,ship,back);
        }
        if(backPage) {
            if(convert(e.getX())<=287 && convert(e.getX())>=169 && convert(e.getY())<=210 && convert(e.getY())>=92) {
                back = backgrounds[0];
                backNum = 0;
                repaint();
            } else if(convert(e.getX())<=430 && convert(e.getX())>=312 && convert(e.getY())<=210 && convert(e.getY())>=92) {
                back = backgrounds[1];
                backNum = 1;
                repaint();
            } else if(convert(e.getX())<=573 && convert(e.getX())>=455 && convert(e.getY())<=210 && convert(e.getY())>=92) {
                back = backgrounds[2];
                backNum = 2;
                repaint();
            } else if(convert(e.getX())<=287 && convert(e.getX())>=169 && convert(e.getY())<=337 && convert(e.getY())>=219) {
                back = backgrounds[3];
                backNum = 3;
                repaint();
            } else if(convert(e.getX())<=430 && convert(e.getX())>=312 && convert(e.getY())<=337 && convert(e.getY())>=219) {
                back = backgrounds[4];
                backNum = 4;
                repaint();
            } else if(convert(e.getX())<=573 && convert(e.getX())>=455 && convert(e.getY())<=337 && convert(e.getY())>=219) {
                back = backgrounds[5];
                backNum = 5;
                repaint();
            } else if(convert(e.getX())<=287 && convert(e.getX())>=169 && convert(e.getY())<=463 && convert(e.getY())>=345) {
                back = backgrounds[6];
                backNum = 6;
                repaint();
            } else if(convert(e.getX())<=430 && convert(e.getX())>=312 && convert(e.getY())<=463 && convert(e.getY())>=345) {
                back = backgrounds[7];
                backNum = 7;
                repaint();
            } else if(convert(e.getX())<=573 && convert(e.getX())>=455 && convert(e.getY())<=463 && convert(e.getY())>=345) {
                back = backgrounds[8];
                backNum = 8;
                repaint();
            } else if(convert(e.getX())<=287 && convert(e.getX())>=169 && convert(e.getY())<=590 && convert(e.getY())>=472) {
                back = backgrounds[9];
                backNum = 9;
                repaint();
            } else if(convert(e.getX())<=430 && convert(e.getX())>=312 && convert(e.getY())<=590 && convert(e.getY())>=472) {
                back = backgrounds[10];
                backNum = 10;
                repaint();
            }
        }
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
