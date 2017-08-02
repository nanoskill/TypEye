package loginEye;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class faceTrack {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					faceTrack window = new faceTrack();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public faceTrack() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 52);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("pictures/logo.png"));
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
		frame.getContentPane().add(panel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 52, 434, 11);
		frame.getContentPane().add(separator);
	}
}
