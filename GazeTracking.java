import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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

public class GazeTracking
{
	private Integer faceDetected, ok = 0, notok = 0;
	private Integer validateCd = 3;
	
	private GazeThread gazeThread;
	
	private JPanel panelFace;

    private VideoCapture webSource = null;
    private Mat frame = new Mat();
    private MatOfByte mem = new MatOfByte();
    private String path = "haarcascades/haarcascade_frontalface_alt2.xml";
    private CascadeClassifier faceDetector = new CascadeClassifier(FaceTrackingPage.class.getResource(path).getPath().substring(1).replace("%20", " "));
    private MatOfRect faceDetections = new MatOfRect();
    
    public GazeTracking(JPanel panelFace)
    {
    	this.panelFace = panelFace;
    	
        webSource = new VideoCapture(0); // video capture from default cam
        gazeThread = new GazeThread(); //create object of threat class
        Thread facethread = new Thread(gazeThread);
        facethread.setDaemon(true);
        gazeThread.runnable = true;
        facethread.start();
    }
    
    public Integer getOk()
    {
    	return ok;
    }
    
    public Integer getNotOk()
    {
    	return notok;
    }
    
    public void stop()
    {
    	gazeThread.runnable = false;
    	webSource.release();
    }

	public Integer getValidateCd() {
		return validateCd;
	}

	public void setValidateCd(Integer validateCd) {
		this.validateCd = validateCd;
	}
	
	public Integer getFaceDetected()
	{
		return faceDetected;
	}
	
	public void setDrawing(boolean inp)
	{
		gazeThread.drawing = inp;
	}
    
	class GazeThread implements Runnable {	
	    protected volatile boolean runnable = false;
	    protected volatile boolean drawing = true;

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
	                        if(drawing)
	                        {
	                        	for (Rect rect : faceDetections.toArray())
	                            {
	                            	Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
	                                        new Scalar(0, 255,0));
	                            }
	                        	
		                        Imgcodecs.imencode(".bmp", frame, mem);
		                        Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
		                        BufferedImage buff = (BufferedImage) im;
		                        if (g.drawImage(buff, 0, 0, 800, 600 , (buff.getWidth()/4), 0, buff.getWidth(), buff.getHeight(), null))
		                        {
		                            if (runnable == false) {
		                                this.wait();
		                            }
		                        }
		                        im.flush();	       
	                        }
	                        else
	                        {
	                        	if(faceDetected != 1)
	                				notok += 1;
	                			else
	                				ok += 1;
	                        	//System.out.println(ok + " " + notok);
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
    }
}
