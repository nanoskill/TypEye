

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GUI extends JFrame
{
	private static GUI gui;
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
		nPanel.removeAll();
		cPanel.removeAll();
		sPanel.removeAll();
		nPanel.setTimer(typingTest.getTimeDisplay());
		cPanel.setPane(typingTest.getPane());
		cPanel.setInput(typingTest.getInput());
		cPanel.add(typingTest.getResetBtn());
		cPanel.getInput().requestFocus();
		sPanel.setStatusBar(typingTest.getStatusBar());
		if(gui!=null)
		{
			gui.repaint();
			gui.revalidate();
		}
		typingTest.getResetBtn().addActionListener(reset);
	}
	
	private ActionListener reset = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			initiateTest();
		}

	};
	
	public static void main(String[] args)
	{
		gui = new GUI();
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
