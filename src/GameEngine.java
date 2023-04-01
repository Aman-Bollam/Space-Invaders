import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;
import javax.swing.event.InternalFrameListener;
public class GameEngine {
    private boolean menu = true;
    private boolean game = false;
    private boolean end = false;
    private PlayerShip theShip;
    private JPanel screen;
    private JFrame window;
    int panelHeight;
    int panelWidth;
	public GameEngine() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("test");
        if(menu) {
            screen = new MenuView();
        } else if(game) {
            screen = new PlayGame();
        } else if(end) {
            screen = new EndScreen();
        }
		//testing.setPreferredSize(new Dimension(960,960));
		//Toolkit.getDefaultToolkit().getScreenSize().getHeight()
        window.setIconImage((new ImageIcon("images//applogo.png")).getImage());
		screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
		window.setContentPane(screen);
		window.pack();
		window.setVisible(true);
	}
    public void setGame(PlayerShip ship){
        theShip = ship;
        game = true;
        menu = false;
    }
    public void setMenu(){
        menu = true;
        game = false;
        end = false;
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
