/*

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeDisplay extends JPanel
{
	private JLabel timeLbl;
	private SimpleDateFormat timeFormat;

	public TimeDisplay()
	{
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
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
	
}*/
