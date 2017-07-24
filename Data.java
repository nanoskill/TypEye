package classDivided;

import java.awt.event.KeyEvent;

public class Data
{
	private long delay;
	private String currWord;
	private String currText;
	
	private char masterChar;
	private KeyEvent userKey;

	public Data(long delay, String currWord, String currText, KeyEvent event)
	{
		this.setDelay(delay);
		this.currWord = currWord;
		this.currText = currText;
		
		int lenUser = currText.length();
		int lenWord = currWord.length();
		if(lenUser <= lenWord && lenUser!=0)
			masterChar = currWord.charAt(lenUser-1);
		else
			masterChar = ' ';
		
		userKey = event;
	}

	public long getDelay()
	{
		return delay;
	}

	public void setDelay(long delay)
	{
		this.delay = delay;
	}

	public boolean isCorrect()
	{
		return masterChar == userKey.getKeyChar();
	}
	
	public String printData()
	{
		String temp = KeyEvent.getKeyText(userKey.getKeyCode());
		
		return (String)(delay + " " + currWord  + " " + currText  + " " + masterChar + " " + temp);
	}
}
