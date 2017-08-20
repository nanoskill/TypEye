package admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class EnterScript {

	private JPanel frame;
	private JTextArea script;
	private JSpinner duration;
	
	public EnterScript() {
		initialize();
	}

	private void initialize() {
		frame = new JPanel();
		frame.setBounds(100, 100, 800, 600);
		frame.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 561);
		frame.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 784, 60);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel("");
		Image theImage = img.getScaledInstance(90,45, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		lblNewLabel.setBounds(18, 5, 100, 50);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 60, 784, 500);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 784, 10);
		panel_2.add(separator);
		
		script = new JTextArea();
		script.setBounds(72, 61, 640, 256);
		panel_2.add(script);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnInsert.setBounds(615, 392, 97, 29);
		panel_2.add(btnInsert);
		btnInsert.addActionListener(insert);
		
		JLabel lblInputScript = new JLabel("Input Script :");
		lblInputScript.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblInputScript.setBounds(72, 36, 112, 14);
		panel_2.add(lblInputScript);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnBack.setBounds(72, 392, 97, 29);
		panel_2.add(btnBack);
		btnBack.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminMenu window = new AdminMenu(AdminLogin.getAdminName());

				MainFrame.getMainFrame().setContentPane(window.getFrame());
				MainFrame.getMainFrame().setSize(window.getFrame().getSize());
				MainFrame.getMainFrame().setTitle("TypEye Admin - Menu");
				MainFrame.getMainFrame().refresh();
			}
		});
		
		JLabel lblDuration = new JLabel("Duration :");
		lblDuration.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDuration.setBounds(72, 339, 59, 14);
		panel_2.add(lblDuration);
		
		duration = new JSpinner();
		duration.setModel(new SpinnerNumberModel(new Integer(60), new Integer(5), null, new Integer(1)));
		duration.setBounds(141, 337, 55, 20);
		panel_2.add(duration);
		
	}
	
	private ActionListener insert = new ActionListener() {
		public void actionPerformed(ActionEvent arg0)
		{
			if(script.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(null, "Please input the script", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
			if(res == JOptionPane.YES_OPTION)
			{
				if(saveScript() == 1)
				{
					JOptionPane.showMessageDialog(null, "Input success.", "Success", JOptionPane.INFORMATION_MESSAGE);
					AdminMenu window = new AdminMenu(AdminLogin.getAdminName());

					MainFrame.getMainFrame().setContentPane(window.getFrame());
					MainFrame.getMainFrame().setSize(window.getFrame().getSize());
					MainFrame.getMainFrame().setTitle("TypEye Admin - Menu");
					MainFrame.getMainFrame().refresh();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No script has been inputted.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	};
	
	public JPanel getFrame()
	{
		return frame;
	}
	
	private int saveScript()
	{
		MysqlMgr db = new MysqlMgr();
		int a = -1;
		try
		{
			db.connect();
			a = db.insert("INSERT INTO `script` (`Script`, `TextLength`, `Duration`) VALUES ('"+script.getText()+"', '"+ script.getText().length()+"', '"+(Integer)duration.getValue()+"')");
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			db.disconnect();
		}
		return a;
	}
}
