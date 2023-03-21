import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MenuView extends JPanel implements MouseListener, MouseMotionListener{
	private boolean settings = false;
	private boolean skins = false;
	private int slideCounter = 0;
	private String [] imageSlides = new String[]{};
	private Image display;
	private final String path = "images\\";
	private boolean mouseHoveredPlay;
	private boolean mouseHoveredSettings;
	private boolean mouseHoveredSkins;
	public MenuView() {
		this.setPreferredSize(new Dimension(960,960));
		this.setFocusable(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if(!settings && !skins){
			if(mouseHoveredPlay){
				
			} else if(mouseHoveredSettings){

			} else if(mouseHoveredSkins){

			} else{
				display = new ImageIcon(path+"Menu.png").getImage();
			}
		}
		g.drawImage(display, 0, 0, null);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX()>=325 && e.getX()<=637 && e.getY()>=558 && e.getY()<=678){
			
		} else if(e.getX()>=397 && e.getX()<=559 && e.getY()>=714 && e.getY()<=768) {

		} else if(e.getX()>=347 && e.getX()<=611 && e.getY()>=807 && e.getY()<=863) {

		}
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX()>=325 && e.getX()<=637 && e.getY()>=558 && e.getY()<=678){

		} else if(e.getX()>=397 && e.getX()<=559 && e.getY()>=714 && e.getY()<=768) {

		} else if(e.getX()>=347 && e.getX()<=611 && e.getY()>=807 && e.getY()<=863) {

		}
		System.out.println("x: "+e.getX());
		System.out.println("y: "+e.getY());
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
}