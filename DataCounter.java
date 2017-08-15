

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class DataCounter
{
	private Vector<Data> datas;
	//private int seconds;
	private long prevNanotime;
	private int corrects, mistakes, wpm;
	private int realtime = 0;
	private User user;
	private StringBuilder dataShow;
	
	public DataCounter()
	{
		datas = new Vector<Data>();
		prevNanotime = System.nanoTime();
		//seconds = forTime;
		setCorrects(0);
		setMistakes(0);
	}
	
	public Data addData(String currWord, String currText, KeyEvent event)
	{
		long currNanotime = System.nanoTime();
		Data temp = new Data((currNanotime-prevNanotime)/1000000, currWord, currText, event);
		realtime += (currNanotime-prevNanotime)/1000000;
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
	public void prepareDataShow()
	{
		dataShow = new StringBuilder();
		for(int i=0;i<datas.size();i++)
		{
			dataShow.append(String.format("%4d %s\n", i, datas.elementAt(i).printData()));
		}
		dataShow.append("Total time elapsed: " + realtime + "\n");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");		
		dataShow.append(String.format("Data generated: %s\n", dateFormat.format(new Date())));
	}
	public void showData()
	{
		System.out.println(dataShow.toString());
	}
	
	public void storeData()
	{
		File newTextFile = new File("results/result.txt");

        FileWriter fw;
		try {
			fw = new FileWriter(newTextFile);
            fw.write(dataShow.toString());
            fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public int getWPM()
	{
		return (int)((corrects / 5) / ((float)realtime/60000)); 
	}
	public float getAccuracy()
	{
		return corrects / (corrects + mistakes);
	}

	public String getDataShow() {
		return dataShow.toString();
	}
}
