
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

public class FaceTrackingPage {

	private JPanel pane;
	private JPanel panelFace;
	private JLabel countedFace;
	
	private DaemonThread myThread = null;
    private VideoCapture webSource = null;
    private Mat frame = new Mat();
    private MatOfByte mem = new MatOfByte();
    private String path = "haarcascades/haarcascade_frontalface_alt.xml";
    private CascadeClassifier faceDetector = new CascadeClassifier(FaceTrackingPage.class.getResource(path).getPath().substring(1).replace("%20", " "));
    private MatOfRect faceDetections = new MatOfRect();
    private Integer faceDetected;

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
		countedFace.setBounds(700, 0, 50, 50);
		panel.add(countedFace);
		
		panelFace = new JPanel();
		panelFace.setBorder(new LineBorder(Color.GRAY));
		panelFace.setBounds(154, 88, 492, 447);
		getFrame().add(panelFace);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 60, 800, 6);
		getFrame().add(separator);

		initTracking();
	}
	
	class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run()
        {
            synchronized (this)
            {
                while (runnable)
                {
                    if (webSource.grab())
                    {
                        try 
                        {
                            webSource.retrieve(frame);
                            Graphics g = panelFace.getGraphics();
                            faceDetector.detectMultiScale(frame, faceDetections);
                            faceDetected = faceDetections.toArray().length;
                            countedFace.setText(faceDetected.toString());
                            for (Rect rect : faceDetections.toArray())
                            {
                            	Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                        new Scalar(0, 255,0));
                            }
	                        Imgcodecs.imencode(".bmp", frame, mem);
	                        Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
	                        BufferedImage buff = (BufferedImage) im;
	                        if (g.drawImage(buff, 0, 0, pane.getWidth(), pane.getHeight() , (buff.getWidth()/4), 0, buff.getWidth(), buff.getHeight(), null))
	                        {
	                            if (runnable == false) {
	                                System.out.println("Paused ..... ");
	                                this.wait();
	                            }
	                        }
	                        im.flush();	                        
                        } catch (Exception ex)
                        {
                            System.out.println("Error");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

	
	private void initTracking()
	{
        webSource = new VideoCapture(0); // video capture from default cam
        myThread = new DaemonThread(); //create object of threat class
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }

	
	public JPanel getFrame() {
		return pane;
	}

	public int getFaceDetected() {
		return faceDetected;
	}
}
