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
		
		//decide masterchar
		if(currText.length() > currWord.length())
		{
			masterChar = ' ';
		}
		else
		{
			int i;
			for(i=currText.length()-1;i>0;i--)
				if(currWord.startsWith(currText.substring(0, i)))
				{
					masterChar = currWord.charAt(i);
					break;
				}
			if(i==0) masterChar = currWord.charAt(i);
		}
		
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
	public boolean isWordCorrect()
	{
		return currWord.equals(currText);
	}
	public String printData()
	{
		String temp = KeyEvent.getKeyText(userKey.getKeyCode());
		
		return (String)(delay + " " + currWord  + " " + currText  + " " + masterChar + " " + temp);
	}
}
