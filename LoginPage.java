

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPage extends JPanel
{
	private JTextField name;
	//private JTextField id;
	
	public LoginPage()
	{
		setLayout(new FlowLayout());
		
		JPanel namePack = new JPanel();
		namePack.setAlignmentX(CENTER_ALIGNMENT);
		namePack.setLayout(new GridLayout(1,2));
		namePack.add(new JLabel("Name :"));
		name = new JTextField();
		namePack.add(name);
		add(namePack);
	}
}
