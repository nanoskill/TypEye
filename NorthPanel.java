

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NorthPanel extends JPanel
{
	TimeDisplay timer;
	
	public NorthPanel()
	{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 40));
		add(new JLabel("Logo"), BorderLayout.WEST);
	}
	
	public TimeDisplay getTimer()
	{
		return timer;
	}

	public void setTimer(TimeDisplay timer)
	{
		this.timer = timer;
		add(timer, BorderLayout.EAST);
	}

}
