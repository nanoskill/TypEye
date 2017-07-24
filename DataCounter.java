package classDivided;

import java.awt.event.KeyEvent;
import java.util.Vector;

public class DataCounter
{
	private Vector<Data> datas;
	private int seconds;
	private long prevNanotime;
	private int corrects, mistakes;
	
	public DataCounter(int forTime)
	{
		datas = new Vector<Data>();
		prevNanotime = System.nanoTime();
		seconds = forTime;
		setCorrects(0);
		setMistakes(0);
	}
	
	public void addData(String currWord, String currText, KeyEvent event)
	{
		long currNanotime = System.nanoTime();
		Data temp = new Data((currNanotime-prevNanotime)/1000000, currWord, currText, event);
		datas.add(temp);
		prevNanotime = currNanotime;
		if(event.getKeyChar() != KeyEvent.CHAR_UNDEFINED)
		{
			if(temp.isCorrect())
				corrects++;
			else
				mistakes++;
		}
	}
	
	public void showData()
	{
		for(int i=0;i<datas.size();i++)
		{
			System.out.println(i + " " + datas.elementAt(i).printData());
		}
	}

	public int getSeconds()
	{
		return seconds;
	}

	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}

	public int getCorrects()
	{
		return corrects;
	}

	public void setCorrects(int corrects)
	{
		this.corrects = corrects;
	}

	public int getMistakes()
	{
		return mistakes;
	}

	public void setMistakes(int mistakes)
	{
		this.mistakes = mistakes;
	}
}
