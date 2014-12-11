package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.GraphModel;

public class Display extends JFrame {

	private static final long serialVersionUID = -419244754655579364L;

	private BufferedImage in;
	private BufferedImage out;
	private GraphModel model;
	private ImagePanel outPanel;

	public Display(GraphModel model) {
		this.model = model;
		in = model.getInputImg();
		out = model.getMutableImg().getImage();

		setTitle("MMAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("icon.png");
		setIconImage(icon.getImage());

		setContent();
		//
		// setMinimumSize(new Dimension(270, 175));

		pack();

		setVisible(true);
	}

	private void setContent() {
		getContentPane().setLayout(new GridLayout(0, 2, 10, 10));
		getContentPane().add(getPreparedPanel("Original", in, false));
		getContentPane().add(getPreparedPanel("Solution", out, true));
	}

	public void update() {
		update(model.getMutableImg().getImage());
	}

	public void update(BufferedImage img) {
		outPanel.updateImg(img);
	}

	private JPanel getPreparedPanel(String titleText, BufferedImage image,
			boolean isOut) {
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JLabel title = new JLabel(titleText, SwingConstants.CENTER);
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

		ImagePanel picture = new ImagePanel(image);
		picture.setMinimumSize(new Dimension(256, 256 * image.getHeight()
				/ image.getWidth()));
		if (isOut) {
			outPanel = picture;
		}

		panel.add(title, BorderLayout.NORTH);
		panel.add(picture, BorderLayout.CENTER);
		return panel;
	}
}