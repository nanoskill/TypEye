package classDivided;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimeDisplay extends JPanel
{
	private JLabel timeLbl;
	private SimpleDateFormat timeFormat;

	public TimeDisplay()
	{
		setLayout(new BorderLayout());
		timeLbl = new JLabel();
		timeLbl.setFont(new Font("SansSerif", Font.BOLD, 18));
		timeFormat = new SimpleDateFormat("mm:ss");

		timeLbl.setText("");
		
		timeLbl.setHorizontalAlignment(JLabel.RIGHT);
		add(timeLbl, BorderLayout.EAST);
	}
	
	public void updateTime(Date inp)
	{
		timeLbl.setText(timeFormat.format(inp));
	}
	
}
