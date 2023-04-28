import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.*;
public class GameEngine implements Runnable{
	//private static Font ourFont = Font.createFont(Font.TRUETYPE_FONT, GameEngine.class.getResourceAsStream("fonts/minecraft_font.ttf"));
    private PlayerShip theShip;
    private JPanel screen;
    JFrame window;
    int panelHeight;
    int panelWidth;
	private int eneFrame;
    private Dimension screenSize;
	private PlayGame myGame;
	private boolean leftRel;
	private boolean rightRel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private int size;
	private int move;
	public GameEngine() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("Space Invaders");
		window.setFocusable(true);
		window.setResizable(false);
        screen = new MenuView(this);
        window.setIconImage((new ImageIcon("images//applogo.png")).getImage());
		size = (int)screenSize.getHeight()-50;
		screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
		window.setContentPane(screen);
		window.pack();
		window.setVisible(true);
		leftRel = true;
		rightRel = true;
		move = 0;
	}
	private void startGameLoop(){
		gameThread = new Thread(this);
		gameThread.start();
		//push
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
				setEneHitBox();
				moveEnemy();
				getCollisions();
				moveShip();
				screen.repaint();
				lastFrame = now;
				frames++;
			}
			
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}

	}
	public void setDefenses() {
		
	}
	public void getCollisions() {
		
	}
	public int getSize() {
		return size;
	}
    public void setGame(PlayerShip ship){
        theShip = ship;
		// grid = new EnemyGrid();
        myGame = new PlayGame(this,theShip);
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
	public void setEneHitBox() {
		myGame.setEneBox();
		myGame.detectCollisions();
	}
	public void moveShip() {
		if(!rightRel) {
			myGame.setPosRight();
		} 
		if(!leftRel) {
			myGame.setPosLeft();
		}
	}
	public void moveEnemy() {
		if(myGame.getEnePos("x")<175 && myGame.getRight()) {
			myGame.enePosChange("right");
			move++;
		} 
		if(myGame.getEnePos("x")>=175 && myGame.getRight() && myGame.getEnePos("y")<=740) {
			myGame.setRight(false);
			myGame.enePosChange("down");
			myGame.setLeft(true);
		}
		if(myGame.getLeft()) {
			myGame.enePosChange("left");
		}
		if(myGame.getLeft() && myGame.getEnePos("x")<=5) {
			myGame.setLeft(false);
			myGame.setRight(true);
		}
	}
	public int convert(int d){
		//return (int)((d/960)*(this.getHeight()-50));
		return (int)(((double)d/(double)960)*(size));
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
