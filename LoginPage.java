import java.awt.EventQueue;

import java.awt.BorderLayout;
import javax.swing.JPanel;
//import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPage{

	private JPanel frame;
	private JTextField textField;
	private JTextField textField_1;

	public LoginPage() {
		initialize();
	}

	private void initialize() {
		frame = new JPanel(new BorderLayout());
		getFrame().setBounds(100, 100, 800, 600);
		getFrame().setSize(new Dimension(800, 600));
		
		JPanel panel = new JPanel();
		frame.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.activeCaptionBorder);
		panel_1.setBounds(162, 170, 476, 275);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(180, 193, 139, 39);
		panel_1.add(btnLogin);
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 17));
		btnLogin.addActionListener(login);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(68, 117, 54, 15);
		panel_1.add(lblId);
		lblId.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField_1.setBounds(192, 117, 200, 22);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(68, 57, 54, 15);
		panel_1.add(lblName);
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField.setBounds(192, 57, 200, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setBounds(172, 57, 10, 15);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_1.setBounds(172, 117, 10, 15);
		panel_1.add(label_1);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		Image theImage = img.getScaledInstance(190,95, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		
		lblNewLabel.setBounds(294, 40, 212, 137);
		panel.add(lblNewLabel);
		
		
	}
	
	private ActionListener login = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			MainFrame mf = MainFrame.getMainFrame();
			//FaceTrack window = new FaceTrack();

			TypeTestPage window = new TypeTestPage();
			mf.setSize(window.getFrame().getSize());
			mf.setContentPane(window.getFrame());
			mf.setTitle("TypEye - FaceTrack");
			mf.refresh();
		}
	};

	public JPanel getFrame() {
		return frame;
	}
}
