package classDivided;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JTextPane;

public class ScriptPane extends JTextPane
{
	private String[] textBank;
	private int currWordIdx, currLineLen;
	private String doneWord, onWord, restWord;
	private int topLineLen;
	
	private final int LIMIT = 85;
	private final int N_LINE = 13;
	
	public ScriptPane(String text)
	{
		currWordIdx = 0;
		textBank = text.split(" ");
		
		doneWord = "";
		
		setLayout(new FlowLayout());
		
		setEditable(false);
		setContentType("text/html");

		setMargin(new Insets(3,10,3,10));
		setPreferredSize(new Dimension(700,300));

		buildOnWord("");
		buildRestWord();
		updateText();
	}
	public String getCurrentWord()
	{
		return textBank[currWordIdx];
	}
	
	public void nextWord(boolean correct)
	{
		buildDoneWord(correct);
		if(currLineLen + textBank[currWordIdx].length() > topLineLen)
		{
			currLineLen = 0;
			doneWord = "";
		}
		currWordIdx++;
		buildOnWord("");
		buildRestWord();
		updateText();
	}
	
	public boolean isEmptyWord()
	{
		return currWordIdx >= textBank.length-1;
	}
	
	public void updateText()
	{
		setText("<font style='font-size:14px'>" + doneWord + onWord + restWord + "</font");
	}
	
	public void updateText(String inp)
	{
		setText("<font style='font-size:14px'>" + inp + "</font");
	}
	
	public void buildDoneWord(boolean correct)
	{
		if(correct)
			doneWord += "<font style='color:green;'>" + textBank[currWordIdx] + "</font>&nbsp;";
		else
			doneWord += "<font style='color:red;'>" + textBank[currWordIdx] + "</font>&nbsp;";
		currLineLen += textBank[currWordIdx].length() + 1;
	}
	
	public void buildOnWord(String inp)
	{
		if(!textBank[currWordIdx].startsWith(inp))
			onWord = "<font style='color:red;'>" + textBank[currWordIdx] + "</font>&nbsp;";
		else if(inp.equals(textBank[currWordIdx]))
			onWord = "<font style='color:green;'>" + textBank[currWordIdx] + "</font>&nbsp;";
		else
			onWord = "<span style='background-color:#DCDCDC;'>" + textBank[currWordIdx] + "</span>&nbsp;";
	}
	
	private void buildRestWord()
	{
		int lastIdx = currWordIdx + 1;
		int[] rowL = new int[N_LINE];
		rowL[0] = currLineLen + textBank[currWordIdx].length() + 1;
		
		StringBuilder builder = new StringBuilder("");
		for(int i=0;i<N_LINE-1;i++)
		{
			if(lastIdx > textBank.length-1) break;
			while(rowL[i] + textBank[lastIdx].length()+1 <= LIMIT)
			{
				builder.append(textBank[lastIdx] + "&nbsp;");
				rowL[i] += textBank[lastIdx].length()+1;
				lastIdx++;
				if(lastIdx > textBank.length-1) break;
			}
			builder.append("<br>");
		}
		topLineLen = rowL[0];
		restWord = builder.toString();
	}
}
