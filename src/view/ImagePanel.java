package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = -4095047488300846602L;
	private BufferedImage image;
	
	public ImagePanel(BufferedImage image){
		super(true);
		this.image= image;
		
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0,0,512, 512, null); 
    }
	
	public void updateImg (BufferedImage img){
		image=img;
		repaint(); 
	}

}
