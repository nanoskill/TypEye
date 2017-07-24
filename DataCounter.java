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
	
	public Data addData(String currWord, String currText, KeyEvent event)
	{
		long currNanotime = System.nanoTime();
		Data temp = new Data((currNanotime-prevNanotime)/1000000, currWord, currText, event);
		datas.add(temp);
		prevNanotime = currNanotime;
		if(isInputtable(event) || event.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(temp.isCorrect())
				corrects++;
			else
				mistakes++;
		}
		return temp;
	}
	
	public static boolean isInputtable(KeyEvent e)
	{
		int temp = e.getKeyChar();
		if(temp < 33 || temp > 126) return false;
		return true;
	}
	
	public void showData()
	{
		int temp = 0;
		for(int i=0;i<datas.size();i++)
		{
			System.out.println(i + " " + datas.elementAt(i).printData());
			temp += datas.elementAt(i).getDelay();
		}
		System.out.println("Total time elapsed: " + temp);
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
