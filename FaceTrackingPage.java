
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class FaceTrackingPage {

	private JPanel pane;
	private JPanel panelFace;	
	private JLabel countedFace;
	
	private GazeTracking gazeTracking;
	
	private static Timer cd;
	
	public FaceTrackingPage() {
		initialize();
	}

	private void initialize() {	
		pane = new JPanel();
		pane.setBounds(100, 100, 800, 600);
		pane.setSize(new Dimension(800, 600));
		pane.setLayout(null);
	
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 60);
		getFrame().add(panel);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/pictures/logo.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(18, 5, 100, 50);
		Image theImage = img.getScaledInstance(90,45, Image.SCALE_SMOOTH);
		panel.setLayout(null);
		lblNewLabel.setIcon(new ImageIcon(theImage));
		panel.add(lblNewLabel);
		
		countedFace = new JLabel("");
		countedFace.setBounds(680, 0, 50, 130);
		countedFace.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		panel.add(countedFace);
		
		panelFace = new JPanel();
		panelFace.setBorder(new LineBorder(Color.GRAY));
		panelFace.setBounds(154, 88, 492, 447);
		getFrame().add(panelFace);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 60, 800, 6);
		getFrame().add(separator);
		
		cd = new Timer(1000, countdown);
		gazeTracking = new GazeTracking(panelFace);
		cd.start();
	}
	
	
	
	private ActionListener countdown = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(gazeTracking.getFaceDetected() == 1)
            {
				gazeTracking.setValidateCd(gazeTracking.getValidateCd()-1);
				countedFace.setText("OK "+ gazeTracking.getValidateCd());
            	countedFace.setForeground(Color.GREEN);
            }
            else
            {
            	gazeTracking.setValidateCd(3);
            	countedFace.setText("NOT OK");
            	countedFace.setForeground(Color.RED);
            }
			if(gazeTracking.getValidateCd() == 0)
			{
				MainFrame mf = MainFrame.getMainFrame();
				MainFrame.setGazeTracking(gazeTracking);
				gazeTracking.setDrawing(false);
				//FaceTrack window = new FaceTrack();
	
				TypeTestPage window = new TypeTestPage();
				mf.setSize(window.getFrame().getSize());
				mf.setContentPane(window.getFrame());
				mf.setTitle("TypEye - Test type");
				mf.refresh();
				cd.stop();
				//facethread.interrupt();
				return;
			}
		}
	
	};
/*
	private ActionListener detect = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(faceDetected != 1)
			{
				notok += 1;
			}
			else
			{
				ok += 1;
			}
		}
	
	};
*/	

	
	public JPanel getFrame() {
		return pane;
	}
}
