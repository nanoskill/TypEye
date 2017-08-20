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
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AdminLogin {

	private JPanel frame;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JLabel errMsg;
	private static String adminName;
	public AdminLogin() {
		initialize();
	}

	private void initialize() {
		frame = new JPanel();
		frame.setBounds(100, 100, 800, 600);
		frame.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(SystemColor.controlShadow);
		panel_1.setBounds(161, 240, 477, 278);
		frame.add(panel_1);
		frame.addComponentListener(reqFocus);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(68, 52, 90, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblPassword.setBounds(68, 108, 90, 15);
		panel_1.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(login);
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btnLogin.setBounds(168, 193, 139, 39);
		panel_1.add(btnLogin);
		btnLogin.addKeyListener(enter);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setBounds(172, 52, 15, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_1.setBounds(172, 108, 15, 14);
		panel_1.add(label_1);
		
		nameField = new JTextField();
		nameField.setBounds(192, 48, 200, 22);
		panel_1.add(nameField);
		nameField.setColumns(10);
		nameField.addKeyListener(enter);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(192, 105, 200, 22);
		panel_1.add(passwordField);
		passwordField.addKeyListener(enter);
		
		errMsg = new JLabel("");
		errMsg.setForeground(Color.RED);
		errMsg.setFont(new Font("SansSerif", Font.PLAIN, 12));
		errMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errMsg.setBounds(34, 164, 409, 14);
		panel_1.add(errMsg);

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
		frame.add(lblNewLabel);
		
		JLabel lblAdminPage = new JLabel("ADMIN PAGE");
		lblAdminPage.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblAdminPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminPage.setBounds(338, 193, 123, 14);
		frame.add(lblAdminPage);
	}

	public JPanel getFrame() {
		return frame;
	}
	
	private ActionListener login = new ActionListener() {
		public void actionPerformed(ActionEvent arg0)
		{
			if(validateLogin())
			{	
				AdminMenu window = new AdminMenu(adminName);

				MainFrame.getMainFrame().setContentPane(window.getFrame());
				MainFrame.getMainFrame().setSize(window.getFrame().getSize());
				MainFrame.getMainFrame().setTitle("TypEye Admin - Menu");
				MainFrame.getMainFrame().refresh();
			}
			else
			{
				errMsg.setText("No admin data found");
			}
		}
	};
	
	private ComponentListener reqFocus = new ComponentListener() {

		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentResized(ComponentEvent arg0) {
			nameField.requestFocus();			
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private KeyListener enter = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				login.actionPerformed(null);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private boolean validateLogin()
	{
		MysqlMgr db = new MysqlMgr();
		ResultSet rs;
		int rowcount = 0;
		try
		{
			db.connect();
			String temp = String.copyValueOf(passwordField.getPassword());
			rs = db.query("SELECT * FROM `admin` where `adminid` = \'" + nameField.getText()+ "\' and `password` = \'" + temp + "\'");
			while(rs.next())
			{
				rowcount++;
			}
			rs.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			db.disconnect();
		}
		if(rowcount == 0)
			return false;
		adminName = nameField.getText();
		return true;
	}
	
	public static String getAdminName()
	{
		return adminName;
	}
}