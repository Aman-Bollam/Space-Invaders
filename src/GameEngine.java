import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class GameEngine{
    private PlayerShip theShip;
    private JPanel screen;
    private JFrame window;
    int panelHeight;
    int panelWidth;
    private Dimension screenSize;
	private PlayGame myGame;
	private boolean leftRel;
	private boolean rightRel;
	public GameEngine() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("Space Invaders");
		window.setFocusable(true);
        screen = new MenuView(this);
        window.setIconImage((new ImageIcon("images//applogo.png")).getImage());
		screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
		window.setContentPane(screen);
		window.pack();
		window.setVisible(true);
		leftRel = true;
		rightRel = true;
	}

    public void setGame(PlayerShip ship){
        theShip = ship;     
        myGame = new PlayGame(this,theShip);
		screen = myGame;
        screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
        window.setContentPane(screen);
		window.addKeyListener(new PlayerHorizontal());
		window.addKeyListener(new PlayerShoot());
        window.pack();
		window.setVisible(true);
    }
    public void setMenu(){
        
    }
	private class PlayerHorizontal extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
		  	if(e.getKeyCode()==39) {
				myGame.setPosRight();
				rightRel = false;
				leftRel = true;    
		  	} 
		  	if(e.getKeyCode()==37) {
				myGame.setPosLeft();
				leftRel = false;
				rightRel = true;
		  	}
		}
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==39) {
			   	rightRel = true;
			}
			if(e.getKeyCode()==37) {
				leftRel = true;
			}
		}
	}
	private class PlayerShoot extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==32) {
			  myGame.switchShip(true);
			}
	  	}
	  	public void keyReleased(KeyEvent e) {
		 	if(e.getKeyCode()==32) {
				myGame.switchShip(false);
		  	}
	  	}
	}
	public void moveShip() {
		if(!rightRel) {
			myGame.setPosRight();
		} 
		if(!leftRel) {
			myGame.setPosLeft();
		}
	}
	private static void runGUI() {
	  	GameEngine drive = new GameEngine();
	}
	public static void main(String[] args) {
	    /* Methods that create and show a GUI should be 
	       run from an event-dispatching thread */
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	runGUI();
	        }
	    });
	}
}
