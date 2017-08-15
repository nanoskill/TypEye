
import java.awt.event.KeyEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestType
{
	private JLabel statusBar;
	private ScriptPane pane; 
	private JTextField input;
	private JLabel timeLbl;
	
	private int elapsed;
	
	private JButton resetBtn;
	private DataCounter counter;
	
	public TestType(JLabel timeLbl, JLabel statusBar, JTextField input)
	{
		MysqlMgr db = new MysqlMgr();
		ResultSet rs;
		try
		{
			db.connect();
			rs = db.query("SELECT * FROM `script`");
			rs.last();
			int rowcount = rs.getRow();
			rs.close();
			Random rnd = new Random(System.nanoTime());
			int x = rnd.nextInt(rowcount) + 1;
			rs = db.query("SELECT `script`,`duration` FROM `script` WHERE `id`=" + x);
			String inp = "- No data -";
			while(rs.next())
			{
				inp = rs.getString("script");
				elapsed = rs.getInt("duration");
			}
			pane = new ScriptPane(inp);
			rs.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			db.disconnect();
		}
		//pane = new ScriptPane("No data");
		//input
		this.input = input;
		
		this.timeLbl = timeLbl;
		
		//statusBar
		this.statusBar = statusBar;
		
		resetBtn = new JButton("Reset");
		resetBtn.setVisible(false);
		
	}
	
	
	public void keyboardTyped(KeyEvent e)
	{	   		
   		if (e.getKeyChar() == KeyEvent.VK_SPACE)
   			e.consume();
	}
   	public void keyBoardPressed(KeyEvent e)
   	{
		if(counter == null)
			counter = new DataCounter();
		
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
   			if(pane.isEmptyWord()) return;
   			if(text.length() < currWord.length())
   			{
   				for(int i=text.length();i<currWord.length();i++)
   					counter.addData(currWord, text, e);
   			}
			pane.nextWord(temp.isWordCorrect());
   			input.setText("");
 		}

		//System.out.println(temp.printData());
		updateStatus(counter.getCorrects(), counter.getMistakes());
   	}
	public void updateTime(int inp)
	{
		//System.out.println(inp);
		elapsed = inp;
		SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
		timeLbl.setText(timeFormat.format(new Date(elapsed*1000)));
	}
	
	private void updateStatus(int correct, int mistakes)
	{
		statusBar.setText("Correct : " + correct + " | Mistakes: " + mistakes);
	}
	
	public void terminateTest()
	{
		//pane.updateText("--Finished--");
		input.setEnabled(false);
		counter.showData();
		//counter.storeData();
		resetBtn.setSize(100, 50);
		resetBtn.setVisible(true);
		MainFrame mf = MainFrame.getMainFrame();
		//FaceTrack window = new FaceTrack();

		ThanksPage window = new ThanksPage();
		window.updateResult(counter.getCorrects(), counter.getMistakes(), 999);
		mf.setSize(window.getFrame().getSize());
		mf.setContentPane(window.getFrame());
		mf.setTitle("TypEye - Result");
		mf.refresh();
	}

	public ScriptPane getPane()
	{
		return pane;
	}

	public JButton getResetBtn() {
		return resetBtn;
	}

	public void setResetBtn(JButton resetBtn) {
		this.resetBtn = resetBtn;
	}


	public int getElapsed() {
		return elapsed;
	}


	public void setElapsed(int elapsed) {
		this.elapsed = elapsed;
	}
}
