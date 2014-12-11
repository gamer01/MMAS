package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = -4095047488300846602L;
	private BufferedImage image;

	public ImagePanel(BufferedImage image) {
		super(true);
		this.image = image;

	}

	@Override
	public Dimension getPreferredSize() {
		Dimension preferred = super.getPreferredSize();
		Dimension minimum = getMinimumSize();
		Dimension maximum = getMaximumSize();
		preferred.width = Math.min(Math.max(preferred.width, minimum.width),
				maximum.width);
		preferred.height = Math.min(Math.max(preferred.height, minimum.height),
				maximum.height);
		return preferred;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public void updateImg(BufferedImage img) {
		image = img;
		repaint();
	}
}