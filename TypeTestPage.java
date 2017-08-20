

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JSeparator;

public class TypeTestPage
{
	public static int duration;
	
	private JPanel frame;
	private JPanel nPanel;
	private JPanel cPanel;
	private JPanel sPanel;
	
	private JPanel timeDisplay;
	private JLabel timeLbl;
	private JTextField input;
	
	private Timer timer;
	
	private TestType typingTest;
	
	public TypeTestPage() {
		initialize();
	}

	private void initialize() {	
		frame = new JPanel();
		getFrame().setBounds(100, 100, 800, 600);
		getFrame().setSize(new Dimension(800,600));
		
		frame.addComponentListener(focusReq);
		
		//North Panel
		nPanel = new JPanel();
		nPanel.setBounds(0, 0, 800, 60);
		nPanel.setPreferredSize(new Dimension(800, 40));
		//nPanel.add(new JLabel("Logo"), BorderLayout.WEST);

		timeDisplay = new JPanel();
		timeDisplay.setBounds(670, 5, 100, 50);
		timeDisplay.setLayout(new BorderLayout());
		timeDisplay.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		timeLbl = new JLabel();
		timeLbl.setFont(new Font("SansSerif", Font.BOLD, 24));
		
		timeLbl.setText("");
		
		timeLbl.setHorizontalAlignment(JLabel.RIGHT);
		timeDisplay.add(timeLbl, BorderLayout.EAST);
		
		//nPanel.add(timeDisplay, BorderLayout.EAST);
		//end of North Panel
		
		//Center Panel
		cPanel = new JPanel();
		cPanel.setBounds(0, 60, 800, 540);
		input = new JTextField();
		input.setBounds(238, 338, 322, 31);
		input.setColumns(20);
		input.setFont(new Font("Serif", Font.PLAIN, 22));
		input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		input.addKeyListener(spaceStroke);
		//end of center panel
		
		//South Panel
		sPanel = new JPanel();
		sPanel.setBounds(0, 590, 800, 10);
		JLabel statusBar = new JLabel();
		frame.setLayout(null);
		sPanel.add(statusBar);
		//end of SouthPanel
		
		getFrame().add(nPanel);
		getFrame().add(cPanel);
		getFrame().add(sPanel);
		
		initiateTest();
	}
	
	private KeyAdapter spaceStroke = new KeyAdapter()
	{
		
		public void keyTyped(KeyEvent e)
		{	   		
	   		typingTest.keyboardTyped(e);
		}
	   	public void keyPressed(KeyEvent e)
	   	{
	   		if(!timer.isRunning())
	   		{
	   			timer.start();
	   			MainFrame.getGazeTracking().startDetecting();
	   			typingTest.updateTime(duration);
	   		}
	   		if(e.getKeyCode() == KeyEvent.VK_SPACE && typingTest.getPane().isEmptyWord())
	   		{
	   			timer.stop();
	   			typingTest.terminateTest();
	   		}
			typingTest.keyboardPressed(e);
	   	}
	};
	
	private ComponentListener focusReq = new ComponentListener()
	{

		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentResized(ComponentEvent arg0) {
			input.requestFocus();					
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	};
	
	public void initiateTest()
	{
		typingTest = new TestType(timeLbl, input);
		
		duration = typingTest.getElapsed();
		
		timer = new Timer(1000, timerFunc);
		nPanel.removeAll();
		cPanel.removeAll();
		sPanel.removeAll();
		cPanel.add(typingTest.getPane());
		cPanel.setLayout(null);
		cPanel.add(input);
		
		//JTextPane textPane = new JTextPane();
		typingTest.getPane().setBounds(102, 28, 595, 262);
		cPanel.add(typingTest.getPane());
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setBounds(0, 2, 800, 6);
		cPanel.add(separator);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		JLabel label = new JLabel();
		label.setBounds(18, 5, 100, 50);
		Image theImage = img.getScaledInstance(90,45, Image.SCALE_SMOOTH);
		nPanel.setLayout(null);
		label.setIcon(new ImageIcon(theImage));
		nPanel.add(label);

		nPanel.add(timeDisplay);
		typingTest.getResetBtn().addActionListener(reset);
	}

	private ActionListener timerFunc = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int sec = typingTest.getElapsed()-1;

			typingTest.updateTime(sec);
			if(sec <= 0)
			{
   				typingTest.terminateTest();
   				timer.stop();
			}
		}
	};
	
	public JPanel getFrame() {
		return frame;
	}
	
	private ActionListener reset = new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			initiateTest();
		}

	};
}