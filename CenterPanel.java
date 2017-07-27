

import javax.swing.JPanel;

public class CenterPanel extends JPanel
{
	private ScriptPane pane;
	private InputArea input;
	
	public CenterPanel()
	{

	}

	public ScriptPane getPane()
	{
		return pane;
	}

	public void setPane(ScriptPane pane)
	{
		this.pane = pane;
		add(pane);
	}

	public InputArea getInput()
	{
		return input;
	}

	public void setInput(InputArea input)
	{
		this.input = input;
		add(input);
	}
}
