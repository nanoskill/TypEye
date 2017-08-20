
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage{

	private static User user;
	private JPanel frame;
	private JTextField userIdField;
	private JTextField userNameField;
	private JLabel errMsg;
	
	public LoginPage() {
		user = new User();
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
		
		userIdField = new JTextField();
		userIdField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		userIdField.setBounds(192, 117, 200, 22);
		panel_1.add(userIdField);
		userIdField.setColumns(10);
		userIdField.addKeyListener(enter);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(68, 57, 54, 15);
		panel_1.add(lblName);
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		userNameField = new JTextField();
		userNameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		userNameField.setBounds(192, 57, 200, 22);
		panel_1.add(userNameField);
		userNameField.setColumns(10);
		userNameField.addKeyListener(enter);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label.setBounds(172, 57, 10, 15);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_1.setBounds(172, 117, 10, 15);
		panel_1.add(label_1);
		
		errMsg = new JLabel("");
		errMsg.setForeground(Color.RED);
		errMsg.setBounds(75, 170, 600, 15);
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
		panel.add(lblNewLabel);
	}
	
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
	
	private ActionListener login = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			user.setName(userNameField.getText());
			user.setId(userIdField.getText());
			boolean temp = validateLogin();
			if(temp == false)
				errMsg.setText("User doesn't exist or User ID and username doesn't match.");
			else
			{
				MainFrame mf = MainFrame.getMainFrame();
				FaceTrackingPage window = new FaceTrackingPage();
	
				//TypeTestPage window = new TypeTestPage();
				mf.setSize(window.getFrame().getSize());
				mf.setContentPane(window.getFrame());
				mf.setTitle("TypEye - Gaze tracking");
				mf.refresh();
			}
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
			rs = db.query("SELECT * FROM `user` where `userid` = \'" + user.getId() + "\' and `username` = \'" + user.getName() + "\'");
			while(rs.next())
			{
				user.setDataid(rs.getInt("id"));
				System.out.println(rs.getInt("id"));
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
		return true;
	}
	
	public static User getUser()
	{
		return user;
	}

	public JPanel getFrame() {
		return frame;
	}
}
