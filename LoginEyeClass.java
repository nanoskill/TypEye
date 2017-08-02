package loginEye;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginEyeClass {

	private static final BufferedImage NULL = null;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginEyeClass window = new LoginEyeClass();
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
	public LoginEyeClass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.activeCaptionBorder);
		panel_1.setBounds(88, 83, 257, 143);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(91, 98, 75, 23);
		panel_1.add(btnLogin);
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(27, 64, 54, 14);
		panel_1.add(lblId);
		lblId.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		textField_1 = new JTextField();
		textField_1.setBounds(108, 62, 117, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(27, 33, 54, 14);
		panel_1.add(lblName);
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setBounds(108, 31, 117, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel(":");
		label.setBounds(91, 34, 9, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(91, 65, 17, 14);
		panel_1.add(label_1);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		Image theImage = img.getScaledInstance(100,50, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		
		lblNewLabel.setBounds(155, 11, 123, 61);
		panel.add(lblNewLabel);
		
		
	}
}
