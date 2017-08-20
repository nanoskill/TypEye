
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

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
import java.awt.Font;
import java.awt.Graphics;

public class FaceTrackingPage {

	private JPanel pane;
	private JPanel panelFace;	
	private JLabel countedFace;
    private static Integer faceDetected, ok = 0, notok = 0;
	
	private static Timer cd, updateDetect;
	private int cdint = 3;
	
	private FacetrackThread facetrackThread = null;
    private static VideoCapture webSource = null;
    private Mat frame = new Mat();
    private MatOfByte mem = new MatOfByte();
    private String path = "haarcascades/haarcascade_frontalface_alt2.xml";
    private CascadeClassifier faceDetector = new CascadeClassifier(FaceTrackingPage.class.getResource(path).getPath().substring(1).replace("%20", " "));
    private MatOfRect faceDetections = new MatOfRect();
    private static Thread facethread;

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
		countedFace.setBounds(680, 0, 50, 120);
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

		initTracking();
	}
	
	class FacetrackThread implements Runnable {

        protected volatile boolean runnable = false;
        protected volatile boolean drawing = true;

        @Override
        public void run()
        {
            synchronized (this)
            {
                while (runnable)
                {
                    if (getWebSource().grab())
                    {
                        try 
                        {
                            getWebSource().retrieve(frame);
                            Graphics g = panelFace.getGraphics();
                            faceDetector.detectMultiScale(frame, faceDetections);
                            faceDetected = faceDetections.toArray().length;
                            if(drawing)
                            {
                            	for (Rect rect : faceDetections.toArray())
                                {
                                	Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                                            new Scalar(0, 255,0));
                                }
                            	if(faceDetected == 1)
                                {
                                	if(!cd.isRunning()) cd.start();
                                	countedFace.setText("OK " + cdint);
                                	countedFace.setForeground(Color.GREEN);
                                }
                                else
                                {
                                	cd.stop();
                                	cdint = 3;
                                	countedFace.setText("NOT OK");
                                	countedFace.setForeground(Color.RED);
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
                            }
                            else
                            {
                            	/*
                            	if(!getUpdateDetect().isRunning())
                            		getUpdateDetect().start();
                            	*/
                            	if(faceDetected != 1)
                    				notok += 1;
                    			else
                    				ok += 1;
                            	
                            }
                        } catch (Exception ex)
                        {
                            System.out.println("Error");
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        
    	public int getFaceDetected() {
    		return faceDetected;
    	}
    }
	
	private ActionListener countdown = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(cdint == 0)
			{
				MainFrame mf = MainFrame.getMainFrame();
				//FaceTrack window = new FaceTrack();
	
				TypeTestPage window = new TypeTestPage();
				mf.setSize(window.getFrame().getSize());
				mf.setContentPane(window.getFrame());
				mf.setTitle("TypEye - Test type");
				mf.refresh();
				facetrackThread.drawing = false;
				cd.stop();
				//facethread.interrupt();
				return;
			}	
			cdint--;
			countedFace.setText("OK "+ cdint);
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
*/	private void initTracking()
	{
        webSource = new VideoCapture(0); // video capture from default cam
        facetrackThread = new FacetrackThread(); //create object of threat class
        facethread = new Thread(facetrackThread);
        facethread.setDaemon(true);
        facetrackThread.runnable = true;
        facethread.start();
    }

	
	public JPanel getFrame() {
		return pane;
	}
	
	
	public static Thread getFacethread()
	{
		return facethread;
	}

	public static Integer getOk() {
		return ok;
	}

	public static Integer getNotok() {
		return notok;
	}

	public static Timer getUpdateDetect() {
		return updateDetect;
	}

	public static VideoCapture getWebSource() {
		return webSource;
	}
}
