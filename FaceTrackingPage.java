
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;

public class FaceTrackingPage {

	private JPanel frame;

	public FaceTrackingPage() {
		initialize();
	}

	private void initialize() {	
		frame = new JPanel();
		getFrame().setBounds(100, 100, 800, 600);
		getFrame().setSize(new Dimension(800, 600));
		frame.setLayout(null);
	
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 60);
		getFrame().add(panel);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(18, 5, 100, 50);
		Image theImage = img.getScaledInstance(90,45, Image.SCALE_SMOOTH);
		panel.setLayout(null);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY));
		panel_1.setBounds(154, 88, 492, 447);
		getFrame().add(panel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 60, 800, 6);
		getFrame().add(separator);
	}

	public JPanel getFrame() {
		return frame;
	}
}
