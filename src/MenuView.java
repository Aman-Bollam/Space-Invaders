import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class MenuView extends JPanel implements MouseListener, MouseMotionListener{
	private boolean settings;
	private boolean skins;
	private int slideCounter;
	private String [] imageSlides = {"slide1.png","slide2.png","slide3.png","slide4.png","slide5.png","slide6.png","slide7.png","slide8.png","slide9.png"};
	private Image display;
	private final String path = "images\\";
	private boolean mouseHoveredPlay;
	private boolean mouseHoveredSettings;
	private boolean mouseHoveredSkins;
	private boolean hoveredNext;
	private boolean hoveredEquip;
	private boolean hoveredBack;
	private boolean menuButtHovered;
	private PlayerShip ship;
	public MenuView() {
		slideCounter = 0;
		settings = false;
		skins = false;
		mouseHoveredPlay = false;
		mouseHoveredSettings = false;
		mouseHoveredSkins = false;
		hoveredNext = false;
		hoveredEquip= false;
		hoveredBack=false;
		menuButtHovered = false;
		ship = new PlayerShip(0,2);
		this.setPreferredSize(new Dimension(960,960));
		this.setFocusable(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if(!settings && !skins){
			if(mouseHoveredPlay){
				display = new ImageIcon(path+"menuhoveredplay.png").getImage();
			} else if(mouseHoveredSettings){
				display = new ImageIcon(path+"menuhoveredsettings.png").getImage();
			} else if(mouseHoveredSkins){
				display = new ImageIcon(path+"menuhoveredskins.png").getImage();
			} else{
				display = new ImageIcon(path+"Menu.png").getImage();
			}
			g.drawImage(display, 0, 0, null);
		} else if(skins){
			g.drawImage(new ImageIcon(path+"BlackHole.png").getImage(),0,0,null);
			g.drawImage(new ImageIcon(path+imageSlides[slideCounter]).getImage(),0,100,null);
			if(hoveredNext && slideCounter<8){
				g.drawImage(new ImageIcon(path+"nexthovered.png").getImage(),518,622,null);
			} else if(slideCounter<8) {
				g.drawImage(new ImageIcon(path+"next.png").getImage(),518,622,null);
			} 	
			if(hoveredBack && slideCounter>0){
				g.drawImage(new ImageIcon(path+"backhovered.png").getImage(),320,625,null);
			} else if(slideCounter>0) {
				g.drawImage(new ImageIcon(path+"back.png").getImage(),320,625,null);
			}
			if(menuButtHovered){
				g.drawImage(new ImageIcon(path+"menubuttonhover.png").getImage(),158,556,null);
			} else {
				g.drawImage(new ImageIcon(path+"menubutton.png").getImage(),158,556,null);
			}
			if(ship.isEquipped(slideCounter)) {
				g.drawImage(new ImageIcon(path+"equipped.png").getImage(),159,347,null);
			} else if(hoveredEquip) {
				g.drawImage(new ImageIcon(path+"equiphovered.png").getImage(),159,347,null);
			} else{
				g.drawImage(new ImageIcon(path+"equip.png").getImage(),159,347,null);
			}
			
		}
		g.drawImage(new ImageIcon(path+"a.png").getImage(),0,100,null);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(!skins && !settings){
			if(e.getX()>=325 && e.getX()<=637 && e.getY()>=558 && e.getY()<=678){
				mouseHoveredPlay = true;
			} else {
				mouseHoveredPlay = false;
			}
			if(e.getX()>=397 && e.getX()<=559 && e.getY()>=714 && e.getY()<=768) {
				mouseHoveredSkins = true;
			} else {
				mouseHoveredSkins = false;
			}
			if(e.getX()>=347 && e.getX()<=611 && e.getY()>=807 && e.getY()<=863) {
				mouseHoveredSettings = true;
			} else {
				mouseHoveredSettings = false;
			}
			this.repaint();
		} else if(skins) {
			if(e.getX()>=518 && e.getX()<=813 && e.getY()>=621 && e.getY()<=694) {
				hoveredNext = true;
			} else {
				hoveredNext = false;
			}
			if(e.getX()>=158 && e.getX()<=412 && e.getY()>=346 && e.getY()<=418) {
				hoveredEquip = true;
			} else {
				hoveredEquip = false;
			} 
			if(e.getX()>=319 && e.getX()<=513 && e.getY()>=624 && e.getY()<=696 && slideCounter>0) {
				hoveredBack = true;
			} else {
				hoveredBack = false;
			} 
			if(e.getX()>=156 && e.getX()<=482 && e.getY()>=557 && e.getY()<=611) {
				menuButtHovered = true;
			} else {
				menuButtHovered = false;
			} 
			this.repaint();
		}
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("X:" + e.getX());
		// System.out.println("Y:" + e.getY());
		if(!skins && !settings){
			if(e.getX()>=325 && e.getX()<=637 && e.getY()>=558 && e.getY()<=678){
			
			} else if(e.getX()>=397 && e.getX()<=559 && e.getY()>=714 && e.getY()<=768) {
				skins = true;
				this.repaint();
			} else if(e.getX()>=347 && e.getX()<=611 && e.getY()>=807 && e.getY()<=863) {
				settings = true;
				this.repaint();
			}
		} else if(skins){
			if(e.getX()>=518 && e.getX()<=813 && e.getY()>=621 && e.getY()<=694 && slideCounter<8) {
				slideCounter++;
				this.repaint();
			} else if(e.getX()>=158 && e.getX()<=412 && e.getY()>=346 && e.getY()<=418) {
				ship = new PlayerShip(slideCounter, 2);
				this.repaint();
			} else if(e.getX()>=319 && e.getX()<=513 && e.getY()>=624 && e.getY()<=696 && slideCounter>0) {
				slideCounter--;
				this.repaint();
			} else if(e.getX()>=156 && e.getX()<=482 && e.getY()>=557 && e.getY()<=611){
				skins = false;
				slideCounter=0;
				this.repaint();
			}
		}	
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public int convert(int d){
		//return (int)((d/960)*(this.getHeight()-50));
		return (int)((d/960)*(this.getHeight()));
	}
}