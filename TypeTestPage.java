

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

public class TypeTestPage
{
	private JPanel frame;
	private NorthPanel nPanel;
	private CenterPanel cPanel;
	private SouthPanel sPanel;
	
	private TestType typingTest;
	
	public TypeTestPage() {
		initialize();
	}

	private void initialize() {	
		frame = new JPanel(new BorderLayout());
		getFrame().setBounds(100, 100, 800, 600);
		getFrame().setSize(new Dimension(800,600));
		
		frame.addComponentListener(focusReq);
	
		typingTest = new TestType();
		nPanel = new NorthPanel();
		cPanel = new CenterPanel();
		sPanel = new SouthPanel();
		getFrame().add(nPanel, BorderLayout.NORTH);
		getFrame().add(cPanel, BorderLayout.CENTER);
		getFrame().add(sPanel, BorderLayout.SOUTH);
		
		initiateTest();
	}
	
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
					cPanel.getInput().requestFocus();					
				}

				@Override
				public void componentShown(ComponentEvent arg0) {
					// TODO Auto-generated method stub
					
				}
		
			};
	
	public void initiateTest()
	{
		typingTest = new TestType();
		nPanel.removeAll();
		cPanel.removeAll();
		sPanel.removeAll();
		nPanel.setTimer(typingTest.getTimeDisplay());
		cPanel.setPane(typingTest.getPane());
		cPanel.setInput(typingTest.getInput());
		cPanel.add(typingTest.getResetBtn());
		cPanel.getInput().requestFocus();
		sPanel.setStatusBar(typingTest.getStatusBar());
		typingTest.getResetBtn().addActionListener(reset);
	}
	
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