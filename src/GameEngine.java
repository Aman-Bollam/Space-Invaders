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
	}
    public void setGame(PlayerShip ship){
        theShip = ship;
        screen = new PlayGame(this,theShip);
        screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
        window.setContentPane(screen);
		window.addKeyListener(new PlayerInput());
        window.pack();
		window.setVisible(true);
    }
    public void setMenu(){
        
    }
	private class PlayerInput extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
		  System.out.println(e.getKeyCode());
		}
		// public void keyReleased(KeyEvent e) {
		//   // TODO Auto-generated method stub
		//   throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
		// }
		// public void keyTyped(KeyEvent e) {
		//   // TODO Auto-generated method stub
		//   throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
		// }
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
