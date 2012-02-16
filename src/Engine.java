import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 * This is the main engine to compare two (or more) images. It is a singleton
 * and contains our algorithms for digital image correlation.
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
	 * 
	 * @return a previously defined or otherwise new instance of Engine
	 */
	public static Engine getInstance() {
		if (eng == null) {
			eng = new Engine();
		}
		return eng;
	}

	/**
	 * Adds an ImageFrame object to a LinkedList to eventually be analyzed. Once
	 * two or more images have been stored in this LinkedList, the two oldest
	 * images are removed and analyzed.
	 * 
	 * @param imgFrame
	 *            An image to eventually be analyzed
	 */
	public void addImage(ImageFrame imgFrame) {
		imgList.add(imgFrame);

		if (imgList.size() >= 2) {
			ImageFrame if1 = imgList.getFirst();
			imgList.removeFirst();
			ImageFrame if2 = imgList.getFirst();
			imgList.removeFirst();

			analyze(if1, if2);
		}
	}

	/**
	 * Analyze the images using an algorithm chosen below. Each algorithm is a
	 * method (ex: deltaComparison, edgeDetection) Use this area to test new
	 * algorithms.
	 * 
	 * @param if1
	 * @param if2
	 */
	public void analyze(ImageFrame if1, ImageFrame if2) {

		int ALGORITHM = 2;

		switch (ALGORITHM) {
		case 0:
			deltaComparison(if1, if2);
			break;
		case 1:
			edgeDetection(if1);
			break;
		case 2:
			blackAndWhite(if1);
			break;
		}

		File output = new File("img_out.png");
		try {
			ImageIO.write(if1.getBum(), "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ----- ALGORITHM 1 ----- Compares two images pixel by pixel and outputs
	 * 'img_out.png' with a simple XOR algorithm
	 * 
	 * @param if1
	 *            the first ImageFrame
	 * @param if2
	 *            the second ImageFrame
	 */
	public void deltaComparison(ImageFrame if1, ImageFrame if2) {
		for (int col = 0; col < if1.getHeight(); col++) {
			for (int row = 0; row < if1.getWidth(); row++) {
				int[] d1Array = new int[4];
				if1.getRar().getPixel(row, col, d1Array);
				// System.out.println("image 1: " + d1Array[0] + " " +
				// d1Array[1]
				// + " " + d1Array[2] + " " + d1Array[3]);

				int[] d2Array = new int[4];
				if2.getRar().getPixel(row, col, d2Array);
				// System.out.println("image 2: " + d2Array[0] + " " +
				// d2Array[1]
				// + " " + d2Array[2] + " " + d2Array[3]);

				if (d1Array[0] != d2Array[0] || d1Array[1] != d2Array[1]
						|| d1Array[2] != d2Array[2] || d1Array[3] != d2Array[3]) {
					if1.getBum().setRGB(row, col, 0xff00ff00);
				}
			}
		}
	}

	/**
	 * ----- ALGORITHM 2 ----- Detects edges in an image by comparing
	 * neighboring pixels and marking at positions that differ above some
	 * threshold value
	 * 
	 * @param if1
	 *            the ImageFrame to analyze
	 */
	public void edgeDetection(ImageFrame if1) {
		for (int col = 1; col < if1.getHeight() - 1; col++) {
			for (int row = 1; row < if1.getWidth() - 1; row++) {

				int[] dCENT = new int[4];
				if1.getRar().getPixel(row, col, dCENT);
				int vCENT = dCENT[0] + dCENT[1] + dCENT[2];

				int[] dNW = new int[4];
				if1.getRar().getPixel(row - 1, col - 1, dNW);
				int vNW = dNW[0] + dNW[1] + dNW[2];

				int[] dN = new int[4];
				if1.getRar().getPixel(row, col - 1, dN);
				int vN = dN[0] + dN[1] + dN[2];

				int[] dNE = new int[4];
				if1.getRar().getPixel(row + 1, col - 1, dNE);
				int vNE = dNE[0] + dNE[1] + dNE[2];

				int[] dW = new int[4];
				if1.getRar().getPixel(row - 1, col, dW);
				int vW = dW[0] + dW[1] + dW[2];

				int[] dE = new int[4];
				if1.getRar().getPixel(row + 1, col, dE);
				int vE = dE[0] + dE[1] + dE[2];

				int[] dSW = new int[4];
				if1.getRar().getPixel(row - 1, col + 1, dSW);
				int vSW = dSW[0] + dSW[1] + dSW[2];

				int[] dS = new int[4];
				if1.getRar().getPixel(row, col + 1, dS);
				int vS = dS[0] + dS[1] + dS[2];

				int[] dSE = new int[4];
				if1.getRar().getPixel(row + 1, col + 1, dSE);
				int vSE = dSE[0] + dSE[1] + dSE[2];

				ArrayList<Integer> colorVals = new ArrayList<Integer>();

				colorVals.add(vNW);
				colorVals.add(vN);
				colorVals.add(vNE);
				colorVals.add(vW);
				colorVals.add(vE);
				colorVals.add(vSW);
				colorVals.add(vS);
				colorVals.add(vSE);

				Collections.sort(colorVals);

				int extreme1 = colorVals.get(0);
				int extreme2 = colorVals.get(colorVals.size() - 1);

				int diff = Math.abs(extreme1 - extreme2);

				System.out.println(diff);

				if (diff > 80) {
					if1.getBum().setRGB(row, col, 0xff00ff00);
				}

			}
		}
	}

	/**
	 * ----- ALGORITHM 3 ----- Converts image to black and white
	 */
	public void blackAndWhite(ImageFrame if1) {
		for (int col = 0; col < if1.getHeight(); col++) {
			for (int row = 0; row < if1.getWidth(); row++) {
				int[] color = new int[4];
				if1.getRar().getPixel(row, col, color);
				int r = (int)(color[0]);
				int g = (int)(color[1]);
				int b = (int)(color[2]);
				int allColor = (r+g+b)/3;
				
				if1.getBum().setRGB(row, col, allColor);
				
				if(allColor < 10){
					if1.getBum().setRGB(row, col, 0xff666666);
				}
				else if(allColor < 20){
					if1.getBum().setRGB(row, col, 0xff777777);
				}
				else if(allColor < 30){
					if1.getBum().setRGB(row, col, 0xff888888);
				}
				else if(allColor < 40){
					if1.getBum().setRGB(row, col, 0xff999999);
				}
				else if(allColor < 50){
					if1.getBum().setRGB(row, col, 0xffaaaaaa);
				}
				else if(allColor < 60){
					if1.getBum().setRGB(row, col, 0xffbbbbbb);
				}
				else if(allColor < 70){
					if1.getBum().setRGB(row, col, 0xffcccccc);
				}
				else if(allColor < 80){
					if1.getBum().setRGB(row, col, 0xffdddddd);
				}
				else if (allColor < 90){
					if1.getBum().setRGB(row, col, 0xffeeeeee);
				}
				else{
					if1.getBum().setRGB(row, col, 0xffffffff);
				}			
			}
		}
	}

}
