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
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class ThanksPage {

	private JPanel frame;

	/**
	 * Launch the application.
	 *//*
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
	}*/

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
		setFrame(new JPanel(new BorderLayout()));
		getFrame().setBounds(100, 100, 800, 600);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(-8, -19, 800, 600);
		getFrame().add(panel);
		panel.setLayout(null);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		Image theImage = img.getScaledInstance(290,145, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		lblNewLabel.setBounds(250, 52, 300, 150);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(75, 213, 650, 80);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblThankYouFor = new JLabel("Thank You For Your Participation!");
		lblThankYouFor.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblThankYouFor.setBounds(95, 5, 455, 70);
		panel_1.add(lblThankYouFor);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
		panel_2.setBounds(250, 321, 300, 210);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblResult = new JLabel("RESULT");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblResult.setBounds(110, 11, 80, 20);
		panel_2.add(lblResult);
		
		JLabel lblCorrect = new JLabel("Correct");
		lblCorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrect.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCorrect.setForeground(new Color(0, 0, 0));
		lblCorrect.setBounds(47, 50, 55, 20);
		panel_2.add(lblCorrect);
		
		JLabel lblWrong = new JLabel("Wrong");
		lblWrong.setHorizontalAlignment(SwingConstants.CENTER);
		lblWrong.setForeground(new Color(0, 0, 0));
		lblWrong.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblWrong.setBounds(194, 50, 55, 20);
		panel_2.add(lblWrong);
		
		JLabel lblNewLabel_1 = new JLabel("000");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 35));
		lblNewLabel_1.setBounds(24, 73, 100, 30);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("000");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 35));
		lblNewLabel_2.setBounds(173, 73, 100, 30);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblWmpResult = new JLabel("WPM Result");
		lblWmpResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblWmpResult.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblWmpResult.setBounds(100, 113, 100, 30);
		panel_2.add(lblWmpResult);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 42, 298, 7);
		panel_2.add(separator);
		separator.setBackground(Color.GRAY);
		separator.setForeground(Color.GRAY);
		
		JLabel lblNewLabel_3 = new JLabel("000");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 60));
		lblNewLabel_3.setBounds(95, 140, 111, 55);
		panel_2.add(lblNewLabel_3);
	}

	public JPanel getFrame() {
		return frame;
	}

	public void setFrame(JPanel frame) {
		this.frame = frame;
	}
}
