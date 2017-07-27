

import javax.swing.JLabel;

public class StatusBar extends JLabel
{
	public StatusBar(String inp)
	{
		super(inp);
		setHorizontalAlignment(JLabel.CENTER);
	}
	public void updateStatus(int correct, int mistakes)
	{
		setText("Correct : " + correct + " | Mistakes: " + mistakes);
	}
}
