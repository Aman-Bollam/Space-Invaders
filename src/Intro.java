// import java.awt.*;
// import javax.swing.*;

<<<<<<< HEAD
public class Intro {
    private JFrame test;
	public Intro() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		test = new JFrame("test");
		MenuView testing = new MenuView();
		//testing.setPreferredSize(new Dimension(960,960));
		//Toolkit.getDefaultToolkit().getScreenSize().getHeight()
		testing.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
		test.setContentPane(testing);
		test.pack();
		test.setVisible(true);
	}
	private static void runGUI() {
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
=======
// public class Intro {
//     private JFrame test;
// 	public Intro() {
// 		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
// 		test = new JFrame("test");
// 		MenuView testing = new MenuView();
// 		//testing.setPreferredSize(new Dimension(960,960));
// 		//Toolkit.getDefaultToolkit().getScreenSize().getHeight()
// 		testing.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
// 		test.setContentPane(testing);
// 		test.pack();
// 		test.setVisible(true);
// 	}
// 	private static void runGUI() {
// 	  	JFrame.setDefaultLookAndFeelDecorated(true);
// 	  	Intro drive = new Intro();
// 	}
// 	public static void main(String[] args) {
// 	    /* Methods that create and show a GUI should be 
// 	       run from an event-dispatching thread */
// 	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
// 	        public void run() {
// 	        	runGUI();
// 	        }
// 	    });
// 	}
// }
>>>>>>> ad8a6917c8f997ebb5bb63034c6cfce4a0a9113b

