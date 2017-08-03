
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class FaceTrackingPage {

	private JPanel frame;

	public FaceTrackingPage() {
		initialize();
	}

	private void initialize() {	
		frame = new JPanel(new BorderLayout());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setSize(new Dimension(450, 300));
	
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 52);
		getFrame().add(panel);
		panel.setLayout(null);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		Image theImage = img.getScaledInstance(60,30, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		lblNewLabel.setBounds(10, 11, 76, 30);
		panel.add(lblNewLabel);
		
		JLabel lblTime = new JLabel("01:00");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(361, 11, 63, 30);
		panel.add(lblTime);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY));
		panel_1.setBounds(119, 63, 196, 176);
		getFrame().add(panel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 52, 434, 11);
		getFrame().add(separator);
	}

	public JPanel getFrame() {
		return frame;
	}
}
