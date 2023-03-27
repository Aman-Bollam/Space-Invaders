import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.*;
public class GameEngine {
    private boolean menu;
    private boolean game;
    private boolean end;
    private JPanel screen;
    private JFrame window;
	public GameEngine() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("test");
		MenuView testing = new MenuView();
		//testing.setPreferredSize(new Dimension(960,960));
		//Toolkit.getDefaultToolkit().getScreenSize().getHeight()
		testing.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
		window.setContentPane(testing);
		window.pack();
		window.setVisible(true);
	}
	private static void runGUI() {
	  	JFrame.setDefaultLookAndFeelDecorated(true);
	  	Intro drive = new Intro();
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
