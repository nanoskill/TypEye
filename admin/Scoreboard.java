package admin;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Scoreboard {

	private JPanel frame;
	private JTable table;

	public Scoreboard() {
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
		
		JLabel lblScoreboard = new JLabel("SCOREBOARD");
		lblScoreboard.setFont(new Font("SansSerif", Font.PLAIN, 30));
		lblScoreboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreboard.setBounds(224, 11, 336, 35);
		panel_2.add(lblScoreboard);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(92, 68, 600, 342);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 580, 320);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"No.", "UserID", "Date", "Correct Typing", "Mistake Typing", "WPM", "Accuracy"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Float.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(114);
		table.getColumnModel().getColumn(2).setPreferredWidth(113);
		table.getColumnModel().getColumn(3).setPreferredWidth(113);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 784, 9);
		panel_2.add(separator);
		
		JButton btnExport = new JButton("Export");
		btnExport.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnExport.setBounds(546, 423, 95, 29);
		panel_2.add(btnExport);
		btnExport.addActionListener(exportData);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnBack.setBounds(141, 423, 95, 29);
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
		
		populateTable();
	}
	
	public JPanel getFrame()
	{
		return frame;
	}
	
	private void populateTable()
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		MysqlMgr db = new MysqlMgr();
		ResultSet rs;
		try
		{
			db.connect();
			rs = db.query("SELECT * FROM `result` order by wpm desc");
			int rowcount = 1;
			while(rs.next())
			{
				
				ResultSet getname = db.query("SELECT `userid` from `user` where `id` = " + rs.getInt("userid"));
				String name = "";
				while(getname.next())
					name = getname.getString("userid");
				getname.close();
				model.addRow(new Object[]{rowcount++, name, rs.getString("date"), rs.getInt("correct"), rs.getInt("mistakes"), rs.getInt("wpm"), rs.getFloat("accuracy")});
			}
			rs.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			db.disconnect();
		}
	}
	
	private ActionListener exportData= new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent ev) {
			// TODO Auto-generated method stub
			MysqlMgr db = new MysqlMgr();
			ResultSet rs;
			StringBuilder result = new StringBuilder();
			result.append("No, UserID, Date, Correct Typing, Mistake Typing, WPM, Accuracy" + System.getProperty("line.separator"));
			try
			{
				db.connect();
				rs = db.query("SELECT * FROM `result` order by wpm desc");
				int rowcount = 1;
				while(rs.next())
				{
					
					ResultSet getname = db.query("SELECT `userid` from `user` where `id` = " + rs.getInt("userid"));
					String name = "";
					while(getname.next())
						name = getname.getString("userid");
					getname.close();
					result.append(rowcount++ + ",");
					result.append(name + ",");
					result.append(rs.getString("date") + ",");
					result.append(rs.getInt("correct") + ",");
					result.append(rs.getInt("mistakes") + ",");
					result.append(rs.getInt("wpm") + ",");
					result.append(rs.getFloat("accuracy") + System.getProperty("line.separator"));
				}
				rs.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			} finally
			{
				db.disconnect();
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
			
			File newTextFile = new File("results/Result_" +dateFormat.format(new Date()) + ".csv");

	        FileWriter fw;
			try {
				fw = new FileWriter(newTextFile);
	            fw.write(result.toString());
	            fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Export success.", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
		
	};
}