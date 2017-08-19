package admin;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AdminLogin {

	private JPanel frame;
	private JTextField textField;
	private JPasswordField passwordField;

	public AdminLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JPanel();
		frame.setBounds(100, 100, 800, 600);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 561);
		frame.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.controlShadow);
		panel_1.setBounds(154, 184, 476, 275);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(68, 52, 90, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblPassword.setBounds(68, 120, 90, 15);
		panel_1.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
			}
		});
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnLogin.setBounds(168, 193, 139, 39);
		panel_1.add(btnLogin);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setBounds(172, 52, 15, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_1.setBounds(172, 120, 15, 14);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(192, 48, 200, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(192, 117, 200, 22);
		panel_1.add(passwordField);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel("");
		Image theImage = img.getScaledInstance(200,100, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		lblNewLabel.setBounds(286, 54, 212, 137);
		panel.add(lblNewLabel);

	}

	public JPanel getFrame() {
		return frame;
	}
}