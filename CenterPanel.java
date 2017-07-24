package classDivided;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JPanel;

public class CenterPanel extends JPanel
{
	private ScriptPane pane;
	private InputArea input;
	
	public CenterPanel()
	{
		try
		{
			pane = new ScriptPane(readTxt("E:/pembukaanuud.txt"));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		input = new InputArea();

		add(pane);
		add(input);
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

	public ScriptPane getPane()
	{
		return pane;
	}

	public void setPane(ScriptPane pane)
	{
		this.pane = pane;
	}

	public InputArea getInput()
	{
		return input;
	}

	public void setInput(InputArea input)
	{
		this.input = input;
	}
}
