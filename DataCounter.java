

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class DataCounter
{
	private Vector<Data> datas;
	private long prevNanotime;
	private int corrects, mistakes;
	private int realtime = 0;
	private int faceOk, faceNotOk;
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
		dataShow.append("Face detection rate: OK: " + faceOk + " | Not OK: " + faceNotOk + " | Rate : " + (faceOk/(float)(faceOk + faceNotOk))*100 + "%\n");
		dataShow.append("Total time elapsed: " + realtime + "\n");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");		
		dataShow.append(String.format("Data generated: %s\n", dateFormat.format(new Date())));
	}
	public void showData()
	{
		System.out.println(dataShow.toString());
	}
	
	public void storeDataDetails()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss_");
	
		File newTextFile = new File("results/" +dateFormat.format(new Date()) + LoginPage.getUser().getId() +".txt");

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
	
	public void feedFaceData(int ok, int notok)
	{
		faceOk = ok;
		faceNotOk = notok;
	}
	
	public void storeData(int scriptid)
	{
		MysqlMgr db = new MysqlMgr();
		try
		{
			db.connect();
			String q = "INSERT INTO `result` (`UserId`, `ScriptId`, `Correct`, `Mistakes`, `Accuracy`, `WPM`) "
					+ "VALUES ('"+ LoginPage.getUser().getDataid() +"','"
					+ scriptid + "', '"+ corrects +"', '"+ mistakes +"', '" + getAccuracy() + "', '" + getWPM() + "')";
			db.insert(q);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			db.disconnect();
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
		return (float)corrects / (float)(corrects + mistakes);
	}

	public String getDataShow() {
		return dataShow.toString();
	}
}
