package classDivided;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SouthPanel extends JPanel
{
	private JLabel statusBar;
	
	public JLabel getStatusBar()
	{
		return statusBar;
	}

	public void setStatusBar(JLabel statusBar)
	{
		this.statusBar = statusBar;
	}

	public SouthPanel()
	{
		statusBar = new JLabel("-Status Bar-");
		statusBar.setHorizontalAlignment(JLabel.CENTER);
		add(statusBar);
	}
	
	public void updateStatus(int correct, int mistakes)
	{
		statusBar.setText("Correct : " + correct + " | Mistakes: " + mistakes);
	}
}
