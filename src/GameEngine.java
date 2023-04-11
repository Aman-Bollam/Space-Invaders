import java.awt.*;
import javax.swing.*;
public class GameEngine{
    private PlayerShip theShip;
    private JPanel screen;
    private JFrame window;
    int panelHeight;
    int panelWidth;
    private Dimension screenSize;
	public GameEngine() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("Space Invaders");
        screen = new MenuView(this);
        window.setIconImage((new ImageIcon("images//applogo.png")).getImage());
		screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
		window.setContentPane(screen);
		window.pack();
		window.setVisible(true);
	}
    public void setGame(PlayerShip ship){
        theShip = ship;
        screen = new PlayGame(this,theShip);
        screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
        window.setContentPane(screen);
        window.pack();
		window.setVisible(true);
    }
    public void setMenu(){
        
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
