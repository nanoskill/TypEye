

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypeTestPage
{
	public static final int TIME = 15;
	
	private JPanel frame;
	private JPanel nPanel;
	private JPanel cPanel;
	private JPanel sPanel;
	
	private JPanel timeDisplay;
	private JLabel timeLbl;
	private JLabel statusBar;
	private JTextField input;
	
	private Timer timer;
	
	private TestType typingTest;
	
	public TypeTestPage() {
		initialize();
	}

	private void initialize() {	
		frame = new JPanel(new BorderLayout());
		getFrame().setBounds(100, 100, 800, 600);
		getFrame().setSize(new Dimension(800,600));
		
		frame.addComponentListener(focusReq);
		
		//North Panel
		nPanel = new JPanel();
		nPanel.setLayout(new BorderLayout());
		nPanel.setPreferredSize(new Dimension(800, 40));
		//nPanel.add(new JLabel("Logo"), BorderLayout.WEST);

		timeDisplay = new JPanel();
		timeDisplay.setLayout(new BorderLayout());
		timeDisplay.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		timeLbl = new JLabel();
		timeLbl.setFont(new Font("SansSerif", Font.BOLD, 18));
		
		timeLbl.setText("");
		
		timeLbl.setHorizontalAlignment(JLabel.RIGHT);
		timeDisplay.add(timeLbl, BorderLayout.EAST);
		
		//nPanel.add(timeDisplay, BorderLayout.EAST);
		//end of North Panel
		
		//Center Panel
		cPanel = new JPanel();
		input = new JTextField();
		input.setColumns(20);
		input.setFont(new Font("Serif", Font.PLAIN, 22));
		input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		input.addKeyListener(spaceStroke);
		//end of center panel
		
		//South Panel
		sPanel = new JPanel();
		statusBar = new JLabel();
		//sPanel.add(statusBar);
		//end of SouthPanel
		
		getFrame().add(nPanel, BorderLayout.NORTH);
		getFrame().add(cPanel, BorderLayout.CENTER);
		getFrame().add(sPanel, BorderLayout.SOUTH);
		
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
	   			typingTest.updateTime(TIME);
	   		}
			typingTest.keyBoardPressed(e);
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
		typingTest = new TestType(timeLbl, statusBar, input);
		
		timer = new Timer(1000, timerFunc);
		nPanel.removeAll();
		cPanel.removeAll();
		sPanel.removeAll();
		cPanel.add(typingTest.getPane());
		cPanel.add(input);
		nPanel.add(new JLabel("Logo"), BorderLayout.WEST);

		nPanel.add(timeDisplay, BorderLayout.EAST);
		sPanel.add(statusBar);
		//nPanel.setTimer(typingTest.getTimeDisplay());
		//cPanel.setPane(typingTest.getPane());
		//cPanel.setInput(typingTest.getInput());
		//cPanel.add(typingTest.getResetBtn());
		//cPanel.getInput().requestFocus();
		//sPanel.setStatusBar(typingTest.getStatusBar());
		typingTest.getResetBtn().addActionListener(reset);
	}

	private ActionListener timerFunc = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			int sec = typingTest.getElapsed()-1;
			//counter.setSeconds(sec);

			System.out.println(sec);
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