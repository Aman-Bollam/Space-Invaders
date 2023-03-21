import java.awt.*;

import javax.swing.*;

public class Intro {
    private JFrame test;
	public Intro() {
		test = new JFrame("test");
		MenuView testing = new MenuView();
		testing.setPreferredSize(new Dimension(960,960));
		test.setContentPane(testing);
		test.pack();
		test.setVisible(true);
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

