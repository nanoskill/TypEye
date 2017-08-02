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

public class LoginEyeClass{

	private JPanel frame;
	private JTextField textField;
	private JTextField textField_1;

	public LoginEyeClass() {
		initialize();
	}

	private void initialize() {
		frame = new JPanel(new BorderLayout());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setSize(new Dimension(450, 300));
		
		JPanel panel = new JPanel();
		frame.add(panel, BorderLayout.CENTER);
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
		btnLogin.addActionListener(login);
		
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
			img = ImageIO.read(new File("src/pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		Image theImage = img.getScaledInstance(100,50, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		
		lblNewLabel.setBounds(155, 11, 123, 61);
		panel.add(lblNewLabel);
		
		
	}
	
	private ActionListener login = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			MainFrame mf = MainFrame.getMainFrame();
			//FaceTrack window = new FaceTrack();

			TypeTestGUI window = new TypeTestGUI();
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
