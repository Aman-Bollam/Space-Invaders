import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
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
	private Timer coolDown;
	private int timer = 0;
	private PlayerShip ship = new PlayerShip(0,2);
	public GameEngine() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window = new JFrame("Space Invaders");
		window.setFocusable(true);
		window.setResizable(false);
        setMenu(this, ship, "background1.jpg");
        window.setIconImage((new ImageIcon("images//applogo.png")).getImage());
		size = (int)screenSize.getHeight()-50;
		screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
		window.setContentPane(screen);
		window.pack();
		window.setVisible(true);
		leftRel = true;
		rightRel = true;
		coolDown = new Timer(0,new cool());
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
				movebullet();
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
		myGame.detectCollisions();
	}
	public int getSize() {
		return size;
	}
    public void setGame(PlayerShip ship, String background){
        theShip = ship;
        myGame = new PlayGame(this,theShip,background);
		screen = myGame;
        screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
        window.setContentPane(screen);
		window.addKeyListener(new PlayerHorizontal());
		window.addKeyListener(new PlayerShoot());
        window.pack();
		window.setVisible(true);
		startGameLoop();
    }
    public void setMenu(GameEngine run, PlayerShip ship, String background) {
        screen = new MenuView(this,ship,background);
		screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
		window.setContentPane(screen);
        window.pack();
		window.setVisible(true);
    }
	public void setSettings(GameEngine run, PlayerShip ship, String background) {
		screen = new Settings(run, ship, background);
        screen.setPreferredSize(new Dimension((int)screenSize.getHeight()-50,(int)screenSize.getHeight()-50));
        window.setContentPane(screen);
        window.pack();
		window.setVisible(true);
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
			if(e.getKeyCode()==32 && timer==0) {
			  	myGame.switchShip(true);
				coolDown.start();
				myGame.bullet();
			}
	  	}
	  	public void keyReleased(KeyEvent e) {
		 	if(e.getKeyCode()==32) {     
				myGame.switchShip(false);
				coolDown.stop();
				timer = 0;
		  	}
	  	}
	}
	private class cool implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(timer<50) {
				timer++;
			} else {
				timer = 0;
			}
		}
		
	}
	public void movebullet(){
		for(int i=0;i<myGame.getBullets().size();i++){
			if(myGame.getBullets().get(i).getY()>0){
				myGame.getBullets().get(i).setBounds((int)myGame.getBullets().get(i).getX(), (int)(myGame.getBullets().get(i).getY()-1), (int)myGame.getBullets().get(i).getWidth(), (int)myGame.getBullets().get(i).getHeight());
			}else{
				myGame.getBullets().remove(i);
			}
		}
	}
	public void setEneHitBox() {
		myGame.setEneBox();
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
		if(myGame.getEnePos("x")<165 && myGame.getRight()) {
			myGame.enePosChange("right");
			move++;
		} 
		if(myGame.getEnePos("x")>=165 && myGame.getRight() && myGame.getEnePos("y")<=740) {
			myGame.setRight(false);
			myGame.enePosChange("down");
			myGame.setLeft(true);
		}
		if(myGame.getLeft()) {
			myGame.enePosChange("left");
		}
		if(myGame.getLeft() && myGame.getEnePos("x")<=10) {
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
