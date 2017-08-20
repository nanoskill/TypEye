package admin;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class AdminMenu {
	private JPanel frame;
	
	public AdminMenu(String adminName)
	{
		initialize(adminName);
	}
	
	private void initialize(String adminName)
	{
		frame = new JPanel();
		frame.setBounds(100, 100, 800, 600);
		frame.setLayout(null);
		
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
		
		JButton btnViewScoreboard = new JButton("View Scoreboard");
		btnViewScoreboard.setBounds(304, 276, 192, 44);
		frame.add(btnViewScoreboard);
		btnViewScoreboard.addActionListener(viewSB);
		
		JButton btnImportScript = new JButton("Import Script");
		btnImportScript.setBounds(304, 349, 192, 44);
		frame.add(btnImportScript);
		btnImportScript.addActionListener(impScr);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(304, 424, 192, 44);
		frame.add(btnLogout);
		btnLogout.addActionListener(logout);
		
		JLabel welcomeLabel = new JLabel("Welcome, " + adminName);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		welcomeLabel.setBounds(255, 218, 289, 14);
		frame.add(welcomeLabel);
	}
	
	private ActionListener viewSB = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Scoreboard window = new Scoreboard();
			
			MainFrame.getMainFrame().setContentPane(window.getFrame());
			MainFrame.getMainFrame().setSize(window.getFrame().getSize());
			MainFrame.getMainFrame().setTitle("TypEye Admin - View Scoreboard");
			MainFrame.getMainFrame().refresh();
			
		}
	};
	
	private ActionListener impScr = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			EnterScript window = new EnterScript();
			
			MainFrame.getMainFrame().setContentPane(window.getFrame());
			MainFrame.getMainFrame().setSize(window.getFrame().getSize());
			MainFrame.getMainFrame().setTitle("TypEye Admin - View Scoreboard");
			MainFrame.getMainFrame().refresh();
			
		}
	};
	
	private ActionListener logout = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			AdminLogin window = new AdminLogin();
			
			MainFrame.getMainFrame().setContentPane(window.getFrame());
			MainFrame.getMainFrame().setSize(window.getFrame().getSize());
			MainFrame.getMainFrame().setTitle("TypEye Admin - Login");
			MainFrame.getMainFrame().refresh();
		}
	};
	
	public JPanel getFrame() {
		return frame;
	}
}
