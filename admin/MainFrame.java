package admin;

import javax.swing.JFrame;

import org.opencv.core.Core;

public class MainFrame extends JFrame{
	
	public static MainFrame mainFrame;
	
	public void refresh()
	{
		repaint();
		revalidate();
	}
	
	public static MainFrame getMainFrame() {
		return mainFrame;
	}
		
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		mainFrame = new MainFrame();
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
		AdminLogin window = new AdminLogin();

		mainFrame.setContentPane(window.getFrame());
		mainFrame.setSize(window.getFrame().getSize());
		mainFrame.setTitle("TypEye Admin - Login");
		mainFrame.refresh();
	}
}
