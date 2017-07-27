

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import javax.swing.Timer;

public class TestType
{
	public static final int TIME = 12;
	
	private TimeDisplay timeDisplay; //for nPanel
	private StatusBar statusBar; //for sPanel
	private ScriptPane pane; //for cPanel
	private InputArea input; //for cPanel
	
	private Timer timer;
	
	private DataCounter counter;
	
	public TestType()
	{
		//pane
		try
		{
			pane = new ScriptPane(readTxt("E:/pembukaanuud.txt"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		//input
		input = new InputArea();
		input.addKeyListener(spaceStroke);
		
		//timedisplay
		timeDisplay = new TimeDisplay();
		
		//statusBar
		statusBar = new StatusBar("-Status Bar-");
		
		//timer
		timer = new Timer(1000, timerFunc);
		
	}
	
	private ActionListener timerFunc = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int sec = counter.getSeconds()-1;
			counter.setSeconds(sec);
			timeDisplay.updateTime(new Date(sec*1000));
			if(sec <= 0)
   				terminateTest();
		}
	};
	
	private KeyAdapter spaceStroke = new KeyAdapter()
	{
		public void keyTyped(KeyEvent e)
		{	   		
	   		if (e.getKeyChar() == KeyEvent.VK_SPACE)
	   			e.consume();
		}
	   	public void keyPressed(KeyEvent e)
	   	{
			if(counter == null)
			{
				counter = new DataCounter(TIME);
				timer.start();
				timeDisplay.updateTime(new Date(TIME*1000));
			}
			
			String text = input.getText();
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && text.length()>0)
				text = text.substring(0, input.getText().length()-1);
			else if(DataCounter.isInputtable(e))
				text = text + e.getKeyChar();
			
   			String currWord = pane.getCurrentWord();
			//System.out.println("kP: " + currWord +" "+ text +" "+ e.getKeyChar());
			
			Data temp = null;
			
			if(!pane.isEmptyWord())
			{
				temp = counter.addData(currWord, text, e);
				pane.buildOnWord(text);
				pane.updateText();
			}
	   		
	   		if (e.getKeyChar() == KeyEvent.VK_SPACE)
	   		{	   			
	   			e.consume();
	   			if(pane.isEmptyWord())
	   			{
	   				terminateTest();
	   				return;
	   			}
	   			
	   			if(text.length() < currWord.length())
	   			{
	   				for(int i=text.length();i<currWord.length();i++)
	   					counter.addData(currWord, text, e);
	   			}
   				pane.nextWord(temp.isWordCorrect());
	   			input.setText("");
	 		}

   			//System.out.println(temp.printData());
			statusBar.updateStatus(counter.getCorrects(), counter.getMistakes());
	   	}
	};
	
	private void terminateTest()
	{
		//pane.updateText("--Finished--");
		input.setEnabled(false);
		timer.stop();
		counter.showData();
		System.out.println("WPM: " + ((counter.getCorrects() - counter.getMistakes())));
	}
	
	private String readTxt(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);

	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + " ");
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}

	public TimeDisplay getTimeDisplay()
	{
		return timeDisplay;
	}


	public StatusBar getStatusBar()
	{
		return statusBar;
	}

	public ScriptPane getPane()
	{
		return pane;
	}

	public InputArea getInput()
	{
		return input;
	}
}
