package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;

public class ImportFile {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ImportFile window = new ImportFile();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public ImportFile() {
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
		
		BufferedImage img = null;
		/*try {
			img = ImageIO.read(new File("pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}*/
		JLabel lblNewLabel = new JLabel("");
		//Image theImage = img.getScaledInstance(90,45, Image.SCALE_SMOOTH);
		//lblNewLabel.setIcon(new ImageIcon(theImage));
		lblNewLabel.setBounds(18, 5, 100, 50);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 60, 784, 500);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 784, 10);
		panel_2.add(separator);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(169, 61, 445, 256);
		panel_2.add(textArea);
		
		JRadioButton rdbtnDefault = new JRadioButton("Default");
		rdbtnDefault.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnDefault.setBounds(169, 338, 109, 23);
		panel_2.add(rdbtnDefault);
		
		JButton btnTakeTest = new JButton("Done");
		btnTakeTest.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnTakeTest.setBounds(517, 386, 97, 29);
		panel_2.add(btnTakeTest);
	}
}
