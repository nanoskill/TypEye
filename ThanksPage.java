import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.Color;

public class ThanksPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThanksPage window = new ThanksPage();
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
	public ThanksPage() {
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
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		Image theImage = img.getScaledInstance(100,50, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		lblNewLabel.setBounds(157, 52, 119, 86);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(95, 149, 243, 53);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtpnThankYouFor = new JTextPane();
		txtpnThankYouFor.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtpnThankYouFor.setBackground(SystemColor.control);
		txtpnThankYouFor.setBounds(5, 16, 233, 20);
		txtpnThankYouFor.setText("THANK YOU FOR YOUR PARTICIPATION !");
		panel_1.add(txtpnThankYouFor);
		
		JLabel lblCorrect = new JLabel("Correct :");
		lblCorrect.setBounds(95, 213, 46, 14);
		panel.add(lblCorrect);
	}
}
