package admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Scoreboard {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Scoreboard window = new Scoreboard();
		window.frame.setVisible(true);
		
	}

	/**
	 * Create the application.
	 */
	public Scoreboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 784, 60);
		panel.add(panel_1);
		panel_1.setLayout(null);
	/*	
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel = new JLabel("");
		Image theImage = img.getScaledInstance(90,45, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		lblNewLabel.setBounds(18, 5, 100, 50);
		panel_1.add(lblNewLabel);
		*/
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
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"No.", "ID", "Wrong Typing", "Correct Typing", "WPM", "Accuracy"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Integer.class, Integer.class, Float.class
			};
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
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnBack.setBounds(141, 423, 95, 29);
		panel_2.add(btnBack);
	}
}