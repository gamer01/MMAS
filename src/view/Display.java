package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
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

	public Display(GraphModel model) {
		this.model = model;
		in = model.getInputImg();
		out = model.getMutableImg().getImage();

		setTitle("MMAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("icon.png");
		setIconImage(icon.getImage());

		setContent();

		pack();

		setVisible(true);
	}

	private void setContent() {
		getContentPane().setLayout(new BorderLayout(10, 10));
		getContentPane().add(getPreparedPanel("Original", in),
				BorderLayout.WEST);
		getContentPane().add(
				getPreparedPanel("Current best Solution", out),
				BorderLayout.EAST);
	}

	public void update() {
		out = model.getMutableImg().getImage();
		update(getGraphics());
	}

	private JPanel getPreparedPanel(String titleText, BufferedImage image) {
		JPanel panel = new JPanel(new BorderLayout(5, 5));

		JLabel title = new JLabel(titleText, SwingConstants.CENTER);
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));

		JLabel picture = new JLabel(new ImageIcon(image));
		picture.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		panel.add(title, BorderLayout.NORTH);
		panel.add(picture, BorderLayout.CENTER);
		return panel;
	}
}
