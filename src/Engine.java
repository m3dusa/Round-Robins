import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;

/**
 * This is the main engine to compare two (or more) images. 
 *  It is a singleton and contains our algorithms 
 *  for digital image correlation.
 */
public class Engine {
	private static Engine eng = null;
	
	LinkedList<ImageFrame> imgList = new LinkedList<ImageFrame>();
	BufferedImage bi;
	
	/**
	 * Useless constructor
	 */
	public Engine() {
	}
	
	/**
	 * To access any of Engine's methods, one must first call getInstance
	 * @return a previously defined or otherwise new instance of Engine
	 */
	public static Engine getInstance() {
		if(eng == null) {
			eng = new Engine();
		}
		return eng;
	}
	
	/**
	 * Adds an ImageFrame object to a LinkedList to eventually be analyzed.
	 *  Once two or more images have been stored in this LinkedList, 
	 *  the two oldest images are removed and analyzed. 
	 * @param imgFrame An image to eventually be analyzed
	 */
	public void addImage(ImageFrame imgFrame) {
		imgList.add(imgFrame);
		
		if(imgList.size()>=2) {
			ImageFrame if1 = imgList.getFirst();
			imgList.removeFirst();
			ImageFrame if2 = imgList.getFirst();
			imgList.removeFirst();
			
			analyze(if1, if2);
		}
	}
	
	public void analyze(ImageFrame if1, ImageFrame if2) {
		
		
		deltaComparison(if1, if2);
	
		
		File output = new File("img_out.png");
		try {
			ImageIO.write(if1.getBum(), "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ----- ALGORITHM 1 -----
	 * Compares two images pixel by pixel and outputs 'img_out.png'
	 *  with a simple XOR algorithm
	 * @param if1 the first ImageFrame
	 * @param if2 the second ImageFrame
	 */
	public void deltaComparison(ImageFrame if1, ImageFrame if2) {
		for (int col = 0; col < if1.getHeight(); col++) {
			for (int row = 0; row < if1.getWidth(); row++) {
				int[] d1Array = new int[4];
				if1.getRar().getPixel(row, col, d1Array);
//				System.out.println("image 1: " + d1Array[0] + " " + d1Array[1]
//						+ " " + d1Array[2] + " " + d1Array[3]);

				int[] d2Array = new int[4];
				if2.getRar().getPixel(row, col, d2Array);
//				System.out.println("image 2: " + d2Array[0] + " " + d2Array[1]
//						+ " " + d2Array[2] + " " + d2Array[3]);

				if (d1Array[0] != d2Array[0] || d1Array[1] != d2Array[1]
						|| d1Array[2] != d2Array[2] || d1Array[3] != d2Array[3]) {
					if1.getBum().setRGB(row, col, 0xff00ff00);
				}
			}
		}
	}
	
	public void edgeDetection(ImageFrame if1, ImageFrame if2) {
		for (int col = 0; col < if1.getHeight(); col++) {
			for (int row = 0; row < if1.getWidth(); row++) {
				int[] d1Array = new int[4];
				if1.getRar().getPixel(row, col, d1Array);
				System.out.println("image 1: " + d1Array[0] + " " + d1Array[1]
						+ " " + d1Array[2] + " " + d1Array[3]);

				
				if1.getBum().setRGB(row, col, 0x00ff00);
				
			}
		}
	}
	
}
