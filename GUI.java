package classDivided;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GUI extends JFrame
{
	private NorthPanel nPanel;
	private CenterPanel cPanel;
	private SouthPanel sPanel;
	
	private DataCounter counter;
	
	private Timer timer;
	
	private final int TIME = 12;
	
	public GUI()
	{
		timer = new Timer(1000, timerFunc);
		setLayout(new BorderLayout());
		nPanel = new NorthPanel();
		cPanel = new CenterPanel();
		sPanel = new SouthPanel();
		add(nPanel, BorderLayout.NORTH);
		add(cPanel, BorderLayout.CENTER);
		add(sPanel, BorderLayout.SOUTH);
		
		cPanel.getInput().addKeyListener(spaceStroke);
		cPanel.getInput().addKeyListener(colorUpdate);
	}
	
	private ActionListener timerFunc = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int sec = counter.getSeconds()-1;
			counter.setSeconds(sec);
			nPanel.getTimer().updateTime(new Date(sec*1000));
			if(sec <= 0)
   				terminateTest();
		}
	};
	
	private KeyAdapter spaceStroke = new KeyAdapter()
	{
	   	public void keyTyped(KeyEvent e)
	   	{
	   		System.out.println("kAT: " + cPanel.getPane().getCurrentWord() +" "+ cPanel.getInput().getText() +" "+ e.getKeyChar());
			
			if(counter == null)
			{
				counter = new DataCounter(TIME);
				timer.start();
				nPanel.getTimer().updateTime(new Date(TIME*1000));
			}
	   		
	   		if (e.getKeyChar() == KeyEvent.VK_SPACE)
	   		{
	   			e.consume();
	   			String text = cPanel.getInput().getText();
	   			String currWord = cPanel.getPane().getCurrentWord();
	   				   			
	   			if(cPanel.getPane().isEmptyWord())
	   			{
	   				terminateTest();
	   				return;
	   			}
	   			/*
	   			while(text.length() > currWord.length())
	   			{
		   			counter.addData(currWord, text);
		   			text = text.substring(0, text.length()-1);
	   			}
	   			if(text.length() < currWord.length())
	   			{
	   				for(int i=text.length();i<currWord.length();i++)
	   					counter.addData(currWord, text);
	   			}*/
   				cPanel.getPane().nextWord(text);
	   			cPanel.getInput().setText("");
	   			
	 		}
	   	}
	};
	
	private KeyListener colorUpdate = new KeyListener()
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			System.out.println("kP: " + cPanel.getPane().getCurrentWord() +" "+ cPanel.getInput().getText() +" "+ e.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE)
			{	 
				if(!cPanel.getPane().isEmptyWord())
				{
					cPanel.getPane().buildOnWord(cPanel.getInput().getText());
					cPanel.getPane().updateText();
				}
				counter.addData(cPanel.getPane().getCurrentWord(), cPanel.getInput().getText(), e);
				sPanel.updateStatus(counter.getCorrects(), counter.getMistakes());
			}
			System.out.println("kR: " + cPanel.getPane().getCurrentWord() +" "+ cPanel.getInput().getText() +" "+ e.getKeyChar());
   		}

		@Override
		public void keyTyped(KeyEvent e)
		{
			System.out.println("kT: " + cPanel.getPane().getCurrentWord() +" "+ cPanel.getInput().getText() +" "+ e.getKeyChar());
		}
	};
	
	private void terminateTest()
	{
		//cPanel.getPane().updateText("--Finished--");
		cPanel.getInput().setEnabled(false);
		timer.stop();
		counter.showData();
		System.out.println("WPM: " + ((counter.getCorrects() - counter.getMistakes())));
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
				public void windowOpened( WindowEvent e ){
			        gui.cPanel.getInput().requestFocus();
			    }
			}
		);
	}

}
