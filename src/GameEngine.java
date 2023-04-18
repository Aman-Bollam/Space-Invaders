import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class GameEngine implements Runnable{
    private PlayerShip theShip;
    private JPanel screen;
    JFrame window;
    int panelHeight;
    int panelWidth;
    private Dimension screenSize;
	private PlayGame myGame;
	private EnemyGrid grid;
	private boolean leftRel;
	private boolean rightRel;
	private Thread gameThread;
	private final int FPS_SET =120;
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
	private void startGameLoop(){
		gameThread= new Thread(this);
		gameThread.start();
	}
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();

		int frames = 0;
		long lastCheck = System.currentTimeMillis();

		while (true) {

			now = System.nanoTime();
			if (now - lastFrame >= timePerFrame) {
				screen.repaint();
				lastFrame = now;
				frames++;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}

	}


    public void setGame(PlayerShip ship){
        theShip = ship;
		grid = new EnemyGrid();
        myGame = new PlayGame(this,theShip,grid);
		screen = myGame;
        screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
        window.setContentPane(screen);
		window.addKeyListener(new PlayerHorizontal());
		window.addKeyListener(new PlayerShoot());
        window.pack();
		window.setVisible(true);
		startGameLoop();
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
