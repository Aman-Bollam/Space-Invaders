import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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
				display = resize(new ImageIcon(path+"menuhoveredplay.png"), this.getHeight()).getImage();
			} else if(mouseHoveredSettings){
				display = resize(new ImageIcon(path+"menuhoveredsettings.png"), this.getHeight()).getImage();
			} else if(mouseHoveredSkins){
				display = resize(new ImageIcon(path+"menuhoveredskins.png"), this.getHeight()).getImage();
			} else{
				display = resize(new ImageIcon(path+"Menu.png"), this.getHeight()).getImage();
			}
			g.drawImage(display, 0, 0, null);
		} else if(skins){
			g.drawImage(resize(new ImageIcon(path+"BlackHole.png"), this.getHeight()).getImage(),0,0,null);
			g.drawImage(resizeMenu(new ImageIcon(path+imageSlides[slideCounter])).getImage(),0,convert(100),null);
			//g.drawImage(new ImageIcon(path+imageSlides[slideCounter]).getImage(),0,100,null);
			if(hoveredNext && slideCounter<8){
				g.drawImage(resizeMenu(new ImageIcon(path+"nexthovered.png")).getImage(),convert(518),convert(622),null);
			} else if(slideCounter<8) {
				g.drawImage(resizeMenu(new ImageIcon(path+"next.png")).getImage(),convert(518),convert(622),null);
			} 	
			if(hoveredBack && slideCounter>0){
				g.drawImage(resizeMenu(new ImageIcon(path+"backhovered.png")).getImage(),convert(320),convert(625),null);
			} else if(slideCounter>0) {
				g.drawImage(resizeMenu(new ImageIcon(path+"back.png")).getImage(),convert(320),convert(625),null);
			}
			if(menuButtHovered){
				g.drawImage(resizeMenu(new ImageIcon(path+"menubuttonhover.png")).getImage(),convert(158),convert(556),null);
			} else {
				g.drawImage(resizeMenu(new ImageIcon(path+"menubutton.png")).getImage(),convert(158),convert(556),null);
			}
			if(ship.isEquipped(slideCounter)) {
				g.drawImage(resizeMenu(new ImageIcon(path+"equipped.png")).getImage(),convert(159),convert(347),null);
			} else if(hoveredEquip) {
				g.drawImage(resizeMenu(new ImageIcon(path+"equiphovered.png")).getImage(),convert(159),convert(347),null);
			} else{
				g.drawImage(resizeMenu(new ImageIcon(path+"equip.png")).getImage(),convert(159),convert(347),null);
			}
		}
		g.drawImage(resizeMenu(new ImageIcon(path+"ship-phase-1-pos2.png")).getImage(),convert(159),convert(347),null);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println(convert(e.getX()));
		if(!skins && !settings){
			if(e.getX()>=convert(325) && e.getX()<=convert(637) && e.getY()>=convert(558) && e.getY()<=convert(678)){
				mouseHoveredPlay = true;
			} else {
				mouseHoveredPlay = false;
			}
			if(e.getX()>=convert(397) && e.getX()<=convert(559) && e.getY()>=convert(714) && e.getY()<=convert(768)) {
				mouseHoveredSkins = true;
			} else {
				mouseHoveredSkins = false;
			}
			if(e.getX()>=convert(347) && e.getX()<=convert(611) && e.getY()>=convert(807) && e.getY()<=convert(863)) {
				mouseHoveredSettings = true;
			} else {
				mouseHoveredSettings = false;
			}
			this.repaint();
		} else if(skins) {
			if(e.getX()>=convert(518) && e.getX()<=convert(813) && e.getY()>=convert(621) && e.getY()<=convert(694)) {
				hoveredNext = true;
			} else {
				hoveredNext = false;
			}
			if(e.getX()>=convert(158) && e.getX()<=convert(412) && e.getY()>=convert(346) && e.getY()<=convert(418)) {
				hoveredEquip = true;
			} else {
				hoveredEquip = false;
			} 
			if(e.getX()>=convert(319) && e.getX()<=convert(513) && e.getY()>=convert(624) && e.getY()<=convert(696) && slideCounter>0) {
				hoveredBack = true;
			} else {
				hoveredBack = false;
			} 
			if(e.getX()>=convert(156) && e.getX()<=convert(482) && e.getY()>=convert(557) && e.getY()<=convert(611)) {
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
			if(e.getX()>=convert(325) && e.getX()<=convert(637) && e.getY()>=convert(558) && e.getY()<=convert(678)){
			
			} else if(e.getX()>=convert(397) && e.getX()<=convert(559) && e.getY()>=convert(714) && e.getY()<=convert(768)) {
				skins = true;
				this.repaint();
			} else if(e.getX()>=convert(347) && e.getX()<=convert(611) && e.getY()>=convert(807) && e.getY()<=convert(863)) {
				settings = true;
				this.repaint();
			}
		} else if(skins){
			if(e.getX()>=convert(518) && e.getX()<=convert(813) && e.getY()>=convert(621) && e.getY()<=convert(694) && slideCounter<8) {
				slideCounter++;
				this.repaint();
			} else if(e.getX()>=convert(158) && e.getX()<=convert(412) && e.getY()>=convert(346) && e.getY()<=convert(418)) {
				ship = new PlayerShip(slideCounter, 2);
				this.repaint();
			} else if(e.getX()>=convert(319) && e.getX()<=convert(513) && e.getY()>=convert(624) && e.getY()<=convert(696) && slideCounter>0) {
				slideCounter--;
				this.repaint();
			} else if(e.getX()>=convert(156) && e.getX()<=convert(482) && e.getY()>=convert(557) && e.getY()<=convert(611)){
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
		return (int)(((double)d/(double)960)*(this.getHeight()));
	}
	private ImageIcon resize(ImageIcon img, int height) {
		Image image = img.getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	private ImageIcon resizeMenu(ImageIcon img) {
		Image image = img.getImage().getScaledInstance(convert(img.getIconWidth()), convert(img.getIconHeight()), Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
}