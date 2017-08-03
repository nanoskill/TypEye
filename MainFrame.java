
import java.awt.BorderLayout;

import javax.swing.JFrame;

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
		mainFrame = new MainFrame();
		mainFrame.setSize(450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
<<<<<<< HEAD
		//LoginPage window = new LoginPage();
		//TypeTestPage window = new TypeTestPage();
		//ThanksPage window = new ThanksPage();
		FaceTrackingPage window = new FaceTrackingPage();
=======
		LoginPage window = new LoginPage();
		//TypeTestPage window = new TypeTestPage();
>>>>>>> master
		mainFrame.setContentPane(window.getFrame());
		mainFrame.setSize(window.getFrame().getSize());
		mainFrame.setTitle("TypEye - Login");
		mainFrame.refresh();
	}
}
