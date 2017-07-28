

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GUI extends JFrame
{
	private NorthPanel nPanel;
	private CenterPanel cPanel;
	private SouthPanel sPanel;
	
	private TestType typingTest;
	public GUI()
	{
		typingTest = new TestType();
		nPanel = new NorthPanel();
		cPanel = new CenterPanel();
		sPanel = new SouthPanel();
		add(nPanel, BorderLayout.NORTH);
		add(cPanel, BorderLayout.CENTER);
		//LoginPage a = new LoginPage();
		//add(a, BorderLayout.CENTER);
		add(sPanel, BorderLayout.SOUTH);
		
		initiateTest();
	}
	
	public void initiateTest()
	{
		typingTest = new TestType();
		nPanel.setTimer(typingTest.getTimeDisplay());
		cPanel.setPane(typingTest.getPane());
		cPanel.setInput(typingTest.getInput());
		sPanel.setStatusBar(typingTest.getStatusBar());
	}
	
	public static void main(String[] args)
	{
		GUI gui = new GUI();
		gui.setTitle("Title");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setSize(800,600);
		
		gui.addWindowListener( new WindowAdapter()
		{
			public void windowOpened( WindowEvent e )
			{
		        if(gui.cPanel.getInput() != null)
		        	gui.cPanel.getInput().requestFocus();
			}
		});
	}

}
