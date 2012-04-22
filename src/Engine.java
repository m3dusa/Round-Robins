import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import javax.imageio.ImageIO;

import Models.DensityPix;
import Models.ImageFrame;

/**
 * This is the main engine to compare two (or more) images. It is a singleton
 * and contains our algorithms for digital image correlation.
 */
public class Engine {
	private static Engine eng = null;
	
	private int ALGORITHM = 4;

	LinkedList<ImageFrame> imgList = new LinkedList<ImageFrame>();
	BufferedImage biLast;
	BufferedImage biFin;
	
	int sumX = 0;
	int sumY = 0;
	
	static int ALG_EDGE = 1;
	static int ALG_AMPLIFY = 4;
	static int ALG_DELTA = 0;
	static int ALG_DELTA_AMPLIFY = 6;
	static int ALG_AVG = 9;
	static int ALG_AVG_SIMPLIFY_AMPLIFY = 10;
	static int ALG_SIMPLIFY_AMPLIFY = 11;
	static int ALG_BLACKWHITE = 2;
	
	// alg -> arguments
	HashMap<Integer, Integer> algorithmMap = new HashMap<Integer, Integer>();
	
	HashMap<String, Integer> strMap;
	
	HashMap<String, Integer> revMap;

	/**
	 * Constructor
	 */
	public Engine() {
		algorithmMap.put(Engine.ALG_EDGE, 1);
		algorithmMap.put(Engine.ALG_AMPLIFY, 1);
		algorithmMap.put(Engine.ALG_DELTA, 2);
		algorithmMap.put(Engine.ALG_DELTA_AMPLIFY, 2);
		algorithmMap.put(Engine.ALG_AVG, 2);
		algorithmMap.put(Engine.ALG_AVG_SIMPLIFY_AMPLIFY, 2);
		algorithmMap.put(Engine.ALG_SIMPLIFY_AMPLIFY, 1);
		algorithmMap.put(Engine.ALG_BLACKWHITE, 1);
	}
	
	public HashMap<Integer, Integer> getAlgoMap() {
		return algorithmMap;
	}
	
	public String[] getAlgoStrings() {
		String retStr[] = new String[algorithmMap.size()];
		
		strMap = new HashMap<String, Integer>();
		revMap = new HashMap<String, Integer>();
		
		int c = 0;
		for(Integer i : algorithmMap.keySet()) {
			if(i==Engine.ALG_AVG_SIMPLIFY_AMPLIFY) {
				String s = "Avg(Simplify(Amplify))";
				retStr[c] = s;
				strMap.put(s, algorithmMap.get(i));
				revMap.put(s, ALG_AVG_SIMPLIFY_AMPLIFY);
			}
			else if(i==Engine.ALG_AMPLIFY) {
				String s = "Amplify";
				retStr[c] = s;
				strMap.put(s, algorithmMap.get(i));
				revMap.put(s, ALG_AMPLIFY);
			}
			else if(i==Engine.ALG_AVG) {
				String s = "Average";
				retStr[c] = s;
				strMap.put(s, algorithmMap.get(i));
				revMap.put(s, ALG_AVG);
			}
			else if(i==Engine.ALG_BLACKWHITE) {
				String s = "Black and White";
				retStr[c] = s;
				strMap.put(s, algorithmMap.get(i));
				revMap.put(s, ALG_BLACKWHITE);
			}
			else if(i==Engine.ALG_DELTA) {
				String s = "Delta";
				retStr[c] = s;
				strMap.put(s, algorithmMap.get(i));
				revMap.put(s, ALG_DELTA);
			}
			else if(i==Engine.ALG_DELTA_AMPLIFY) {
				String s = "Delta(Amplify)";
				retStr[c] = s;
				strMap.put(s, algorithmMap.get(i));
				revMap.put(s, ALG_DELTA_AMPLIFY);
			}
			else if(i==Engine.ALG_EDGE) {
				String s = "Edge";
				retStr[c] = s;
				strMap.put(s, algorithmMap.get(i));
				revMap.put(s, ALG_EDGE);
			}
			else if(i==Engine.ALG_SIMPLIFY_AMPLIFY) {
				String s = "Simplify(Amplify)";
				retStr[c] = s;
				strMap.put(s, algorithmMap.get(i));
				revMap.put(s, ALG_SIMPLIFY_AMPLIFY);
			}
			
			c++;
		}
		

		return retStr;
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

	public BufferedImage getbiLast() {
		return biLast;
	}
	
	public BufferedImage getbiFin() {
		return biFin;
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

			findDiff(if1, if2);
		}
	}
	
	public void findDiff(ImageFrame if1, ImageFrame if2) {
		biFin = process(Engine.ALG_AVG_SIMPLIFY_AMPLIFY, if1, if2);
//		biFin = process(Engine.ALG_SIMPLIFY_AMPLIFY, if2);
//		biFin = process(Engine.ALG_AVG, if1, if2);
		saveImage();
	}
	
	public BufferedImage process(int algorithm, ImageFrame img) {
		if(algorithm==Engine.ALG_AMPLIFY) {
			biLast = amplifyColor(img).getBum();
			return amplifyColor(img).getBum();
		}
		if(algorithm==Engine.ALG_SIMPLIFY_AMPLIFY) {
			biLast = removeNoise(amplifyColor(img)).getBum();
			return removeNoise(amplifyColor(img)).getBum();
		}
		if(algorithm==Engine.ALG_EDGE) {
			biLast = edgeDetection(img).getBum();
			return edgeDetection(img).getBum();
		}
		if(algorithm==Engine.ALG_BLACKWHITE) {
			biLast = blackAndWhite(img).getBum();
			return blackAndWhite(img).getBum();
		}
		
		return null;
	}
	
	public BufferedImage process(int algorithm, ImageFrame img1, ImageFrame img2) {
		if(algorithm==Engine.ALG_DELTA) {
			biLast = deltaComparison(img1, img2).getBum();
			return deltaComparison(img1, img2).getBum();
		}
		else if(algorithm==Engine.ALG_DELTA_AMPLIFY) {
			biLast = deltaComparison(amplifyColor(img1), amplifyColor(img2)).getBum();
			return deltaComparison(amplifyColor(img1), amplifyColor(img2)).getBum();
		}
		else if(algorithm==Engine.ALG_AVG) {
			biLast = avg(img1, img2).getBum();
			return avg(img1, img2).getBum();
		}
		else if(algorithm==Engine.ALG_AVG_SIMPLIFY_AMPLIFY) {
			biLast = avg(removeNoise( amplifyColor(img1) ), removeNoise( amplifyColor(img2) )).getBum();
			return avg(removeNoise( amplifyColor(img1) ), removeNoise( amplifyColor(img2) )).getBum();
		}
		
		return null;
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

		switch (ALGORITHM) {
		case 0:
			biLast = deltaComparison(if1, if2).getBum();
			break;
		case 1:
			biLast = edgeDetection(if1).getBum();
			break;
		case 2:
			biLast = blackAndWhite(if1).getBum();
			break;
		case 3:
			biLast = blur(if1).getBum();
			break;
		case 4:
			biLast = amplifyColor(if1).getBum();
			break;
		case 5: 
			// Nesting algorithms together
			biLast = blackAndWhite( edgeDetection(if1) ).getBum();
			break;
		case 6: 
			// Nesting algorithms together
			biLast = deltaComparison( amplifyColor(if1), amplifyColor(if2) ).getBum();
			break;
		case 7:
			// Nesting algorithms together
			biLast = removeNoise( amplifyColor(if1) ).getBum();
			break;
		case 8:
			// Nesting algorithms together
			biLast = removeNoise( amplifyColor(if1) ).getBum();
			//ArrayList<DensityPix> densityPixList = removeNoise( amplifyColor(if1) ).getDensityPixList();
		case 9:
			biLast = avg(if1, if2).getBum();
			break;
		}
		
		
		saveImage();
	}
	
	public void saveImage() {
		File output = new File("img_out.png");
		try {
			ImageIO.write(biLast, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Amplifies the color at a point.
	 * TODO: FIX ME!
	 * @WARNING will break on points close to the edge
	 * @param if1 The ImageFrame to draw on
	 * @param p An x,y pair to amplify
	 * @param intensity changes magnitude of effect
	 */
	public void amplifyPix(ImageFrame if1, Point p, int intensity) {
		int pixArr[] = new int[4];
		if1.getBum().getData().getPixel(p.x, p.y, pixArr);
		Color col = new Color(pixArr[0], pixArr[1], pixArr[2]);
		for(int i=0; i<intensity; i++) {
			try {
				if1.getBum().setRGB(p.x+i, p.y, col.getRGB());
			} catch(ArrayIndexOutOfBoundsException e) {};
			try {
				if1.getBum().setRGB(p.x-i, p.y, col.getRGB());
			} catch(ArrayIndexOutOfBoundsException e) {};
			try {
				if1.getBum().setRGB(p.x+i, p.y+i, col.getRGB());
			} catch(ArrayIndexOutOfBoundsException e) {};
			try {
				if1.getBum().setRGB(p.x-i, p.y+i, col.getRGB());
			} catch(ArrayIndexOutOfBoundsException e) {};
			try {
				if1.getBum().setRGB(p.x+i, p.y-i, col.getRGB());
			} catch(ArrayIndexOutOfBoundsException e) {};
			try {
				if1.getBum().setRGB(p.x-i, p.y-i, col.getRGB());
			} catch(ArrayIndexOutOfBoundsException e) {};
			try {
				if1.getBum().setRGB(p.x, p.y+i, col.getRGB());
			} catch(ArrayIndexOutOfBoundsException e) {};
			try {
				if1.getBum().setRGB(p.x, p.y-i, col.getRGB());
			} catch(ArrayIndexOutOfBoundsException e) {};
			
		}
		
	}

	/**
	 * ----- ALGORITHM 0 ----- Compares two images pixel by pixel and outputs
	 * 'img_out.png' with a simple XOR algorithm
	 * 
	 * @param if1
	 *            the first ImageFrame
	 * @param if2
	 *            the second ImageFrame
	 */
	public ImageFrame deltaComparison(ImageFrame if1, ImageFrame if2) {
		for (int col = 0; col < if1.getHeight(); col++) {
			for (int row = 0; row < if1.getWidth(); row++) {
				int[] d1Array = new int[4];
				if1.getRar().getPixel(row, col, d1Array);
				// System.out.println("image 1: " + d1Array[0] + " " +
				// d1Array[1]
				// + " " + d1Array[2] + " " + d1Array[3]);

				int[] d2Array = new int[4];
				System.out.println("at "+row+", "+col);
				if2.getRar().getPixel(row, col, d2Array);
				// System.out.println("image 2: " + d2Array[0] + " " +
				// d2Array[1]
				// + " " + d2Array[2] + " " + d2Array[3]);

				if (d1Array[0] != d2Array[0] || d1Array[1] != d2Array[1]
						|| d1Array[2] != d2Array[2] || d1Array[3] != d2Array[3]) {
					if1.getBum().setRGB(row, col, 0xff00fff0); // cyan color
				}
			}
		}
		
		return new ImageFrame(if1.getBum());
	}

	/**
	 * ----- ALGORITHM 1 ----- Detects edges in an image by comparing
	 * neighboring pixels and marking at positions that differ above some
	 * threshold value
	 * 
	 * @param if1
	 *            the ImageFrame to analyze
	 */
	public ImageFrame edgeDetection(ImageFrame if1) {
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

				//System.out.println(diff);

				if (diff > 80) {
					
					if1.getBum().setRGB(row, col, 0xff00fff0);
				}

			}
		}
		
		return new ImageFrame(if1.getBum());
	}

	/**
	 * ----- ALGORITHM 2 ----- Converts image to black and white
	 * @param if1 the ImageFrame to analyze
	 */
	public ImageFrame blackAndWhite(ImageFrame if1n) {
		
		ImageFrame if1 = new ImageFrame(if1n);
		
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
					if1.getBum().setRGB(row, col, 0xff000000);
				}
				else if(allColor < 20){
					if1.getBum().setRGB(row, col, 0xff111111);
				}
				else if(allColor < 30){
					if1.getBum().setRGB(row, col, 0xff222222);
				}
				else if(allColor < 40){
					if1.getBum().setRGB(row, col, 0xff333333);
				}
				else if(allColor < 50){
					if1.getBum().setRGB(row, col, 0xff444444);
				}
				else if(allColor < 60){
					if1.getBum().setRGB(row, col, 0xff555555);
				}
				else if(allColor < 70){
					if1.getBum().setRGB(row, col, 0xff666666);
				}
				else if(allColor < 80){
					if1.getBum().setRGB(row, col, 0xff777777);
				}
				else if(allColor < 90){
					if1.getBum().setRGB(row, col, 0xff888888);
				}
				else if(allColor < 100){
					if1.getBum().setRGB(row, col, 0xff999999);
				}
				else if(allColor < 110){
					if1.getBum().setRGB(row, col, 0xffaaaaaa);
				}
				else if(allColor < 120){
					if1.getBum().setRGB(row, col, 0xffbbbbbb);
				}
				else if(allColor < 130){
					if1.getBum().setRGB(row, col, 0xffcccccc);
				}
				else if (allColor < 140){
					if1.getBum().setRGB(row, col, 0xffdddddd);
				}
				else if (allColor < 150){
					if1.getBum().setRGB(row, col, 0xffeeeeee);
				}
				else{
					if1.getBum().setRGB(row, col, 0xffffffff);
				}			
			}
		}
		
		return new ImageFrame(if1.getBum());
	}
	
	/**
	 * ----- ALGORITHM 3 ----- Blurs image by averaging neighboring pixels
	 * @param if1 the ImageFrame to analyze
	 */
	public ImageFrame blur(ImageFrame if1) {
		for (int col = 1; col < if1.getHeight()-1; col++) {
			for (int row = 1; row < if1.getWidth()-1; row++) {
				int[] color = new int[4];
				if1.getRar().getPixel(row-1, col-1, color);
				int r1 = (int)(color[0]);
				int g1 = (int)(color[1]);
				int b1 = (int)(color[2]);
				
				if1.getRar().getPixel(row-1, col, color);
				int r2 = (int)(color[0]);
				int g2 = (int)(color[1]);
				int b2 = (int)(color[2]);
				
				if1.getRar().getPixel(row-1, col+1, color);
				int r3 = (int)(color[0]);
				int g3 = (int)(color[1]);
				int b3 = (int)(color[2]);
				
				if1.getRar().getPixel(row, col-1, color);
				int r4 = (int)(color[0]);
				int g4 = (int)(color[1]);
				int b4 = (int)(color[2]);
				
				if1.getRar().getPixel(row, col, color);
				int r5 = (int)(color[0]);
				int g5 = (int)(color[1]);
				int b5 = (int)(color[2]);
				
				if1.getRar().getPixel(row, col+1, color);
				int r6 = (int)(color[0]);
				int g6 = (int)(color[1]);
				int b6 = (int)(color[2]);
				
				if1.getRar().getPixel(row+1, col-1, color);
				int r7 = (int)(color[0]);
				int g7 = (int)(color[1]);
				int b7 = (int)(color[2]);
				
				if1.getRar().getPixel(row+1, col, color);
				int r8 = (int)(color[0]);
				int g8 = (int)(color[1]);
				int b8 = (int)(color[2]);
				
				if1.getRar().getPixel(row+1, col+1, color);
				int r9 = (int)(color[0]);
				int g9 = (int)(color[1]);
				int b9 = (int)(color[2]);
				
				int avgRed = (r1+r2+r3+r4+r5+r6+r7+r8+r9)/9;
				int avgGreen = (g1+g2+g3+g4+g5+g6+g7+g8+g9)/9;
				int avgBlue = (b1+b2+b3+b4+b5+b6+b7+b8+b9)/9;
				
				Color avgCol = new Color(avgRed, avgGreen, avgBlue);
				
				
				if1.getBum().setRGB(row, col, avgCol.getRGB());
			}
		}
		
		return new ImageFrame(if1.getBum());
	}
	
	/**
	 * ----- ALGORITHM 4 ----- 
	 * This algorithm effectively amplifies the red, green, or blue color of a pixel.
	 * It is simply a three-coloring of a multi-colored image.
	 * <p>
	 * First it scans through all the pixels and gets the average 
	 * red, green, and blue values per pixel.
	 * Then it scans through the pixels again, normalizing their red, green, and blue values
	 * and amplifying the greatest of the three, while diminishing the other two. 
	 * <p><p>
	 * <b>Example:</b> A pixel of color (100,150,200) might be normalized to (125, 170, 160)
	 * and then amplified to the color (0, 255, 0), producing a green pixel. 
	 * <p><p>
	 * <b>Thoughts:</b> When comparing two different images that go though this 'amplifyColor'
	 * algorithm, we might be able to easily detect the location of differences. 
	 * If the difference from one image to another is monocromatic or at least unlike the average
	 * color density of the compared image, then the following postulates may help:
	 * <p><p>
	 * [1] In a local area around the difference, one image will have an abormally abundant
	 * amount of either red, green, or blue.
	 * <p>
	 * [2] In a local area around a non-difference, the two colors not the same as the 
	 * monochromatic difference will be slightly more abundant than the third color. 
	 * <p>
	 * [3] As long as the differences are totally monochromatic, all differences can be identified.
	 * @param if1 the ImageFrame to analyze
	 */
	public ImageFrame amplifyColor(ImageFrame if1n) {
		
		ImageFrame if1 = new ImageFrame(if1n);
		
		int rCount = 0;
		int gCount = 0;
		int bCount = 0;
		
		
		for (int col = 1; col < if1.getHeight()-1; col++) {
			for (int row = 1; row < if1.getWidth()-1; row++) {
				int[] color = new int[4];
				if1.getRar().getPixel(row, col, color);
				
				int r = (int)(color[0]);
				int g = (int)(color[1]);
				int b = (int)(color[2]);
				
				int alpha = color[3];
				//System.out.println("("+row+","+col+")="+alpha);
				
				//System.out.println();
				
				if(alpha==0) {
					//System.out.println("("+row+","+col+")="+alpha);
					continue;
				}
				
				rCount += r;
				gCount += g;
				bCount += b;
				//if1.getBum().setRGB(row, col, avgCol.getRGB());
			}
		}
		
		int totalPix = if1.getHeight()*if1.getWidth();
		int rAvg = rCount/totalPix;
		int gAvg = gCount/totalPix;
		int bAvg = bCount/totalPix;
		
		System.out.println("r: "+rAvg+", g: "+gAvg+", b: "+bAvg);
		
		int avgColVal = (rAvg+gAvg+bAvg)/3;
		int diffR = avgColVal - rAvg;
		int diffG = avgColVal - gAvg;
		int diffB = avgColVal - bAvg;
		
		//System.out.println("r: "+diffR+", g: "+diffG+", b: "+diffB);
		
		for (int col = 1; col < if1.getHeight()-1; col++) {
			for (int row = 1; row < if1.getWidth()-1; row++) {
				int[] color = new int[4];
				if1.getRar().getPixel(row, col, color);
				int r = (int)(color[0]);
				int g = (int)(color[1]);
				int b = (int)(color[2]);
				
				int alpha = color[3];
				if(alpha==0) {
					//System.out.println("alpha found, skipping");
					continue;
				}
				
				r = r+diffR;
				g = g+diffG;
				b = b+diffB;
				
				if(r >= g && r>=b) {
					if1.getBum().setRGB(row, col, 0xffff0000);
				}
				else if(g >= r && g>=b) {
					if1.getBum().setRGB(row, col, 0xff00ff00);
				}
				else if(b >= r && b>=g) {
					if1.getBum().setRGB(row, col, 0xff0000ff);
				}
				
				//if1.getBum().setRGB(row, col, 0xffff0000);
			}
		}
		
		return new ImageFrame(if1.getBum());
	}
	
	boolean [][] pixChecked;
	
	
	
	public ImageFrame removeNoise(ImageFrame if1) {
		// 2-D boolean array
		pixChecked = new boolean[if1.getWidth()][if1.getHeight()];
		for(int i=0; i<if1.getWidth(); i++) {
			for(int j=0; j<if1.getHeight(); j++) {
				pixChecked[i][j] = false;
			}
		}
		
		//int area = pixAreaItr(5, 5, if1.getRar());
		//System.out.println(area);
		
		ArrayList<DensityPix> densityPixList = new ArrayList<DensityPix>();
		for (int col = 1; col < if1.getHeight()-1; col++) {
			for (int row = 1; row < if1.getWidth()-1; row++) {
				
				int[] color = new int[4];
				if1.getRar().getPixel(row, col, color);
				int r = (int)(color[0]);
				int g = (int)(color[1]);
				int b = (int)(color[2]);
				
				int alpha = color[3];
				
				if(alpha==0) {
					continue;
				}
				
				if(pixChecked[row][col]==false) {
					sumX = 0;
					sumY = 0;
					int area = pixAreaItr(row, col, if1.getRar());
					//System.out.println("("+row+","+col+"): "+area);
					int avgx = sumX/area;
					int avgy = sumY/area;
					
					System.out.println("("+avgx+","+avgy+"): "+area);
					
					
					Color c = new Color(r, g, b);
					
					densityPixList.add(new DensityPix(avgx, avgy, area, c.getRGB()));
				}
			}
		}
		
		// clear image
		for (int col = 0; col < if1.getHeight(); col++) {
			for (int row = 0; row < if1.getWidth(); row++) {
				if1.getBum().setRGB(row, col, 0xffffffff);
			}
		}
		
		System.out.println("dens size: "+densityPixList.size());
		
		// filter out weaker densities
		Collections.sort(densityPixList, new Comparator<DensityPix>() {
			@Override
			public int compare(DensityPix o1, DensityPix o2) {
				if(o1.area > o2.area) 
					return -1;
				if(o1.area < o2.area) 
					return 1;
				else return 0;
			}
			
		});
		
		if(densityPixList.size()>8) {
			densityPixList = new ArrayList<DensityPix>(densityPixList.subList(0, 8));
		}
		
		
		for(DensityPix dp : densityPixList) {
			if1.getBum().setRGB(dp.x, dp.y, dp.col);
			
			// optionally amplify colors
			 amplifyPix(if1, dp, 3);
		}
		
		
		//ImageFrameEssence ife = new ImageFrameEssence(if1.getBum());
		//ife.setDensityPixList(densityPixList);
		
		return new ImageFrame(if1.getBum());
	}
	
	
	//TODO
	public ImageFrame avg(ImageFrame if1, ImageFrame if2) {
		
		int smallerWidth = (if1.getWidth() < if2.getWidth()) ? if1.getWidth() : if2.getWidth();
		int smallerHeight = (if1.getHeight() < if2.getHeight()) ? if1.getHeight() : if2.getHeight();
		
		for(int h=0; h<smallerHeight; h++) {
			for(int w=0; w<smallerWidth; w++) {
				int [] pixArr1 = new int[4];
				if1.getRar().getPixel(w, h, pixArr1);
				
				int [] pixArr2 = new int[4];
				if2.getRar().getPixel(w, h, pixArr2);
				
				if(pixArr1[3]==0 || pixArr2[3]==0) {
					continue;
				}
				
				int newr = (pixArr1[0]+pixArr2[0])/2;
				int newg = (pixArr1[1]+pixArr2[1])/2;
				int newb = (pixArr1[2]+pixArr2[2])/2;
				
				
				if1.getBum().setRGB(w, h, new Color(newr, newg, newb).getRGB());
			}
		}
		
		return new ImageFrame(if1.getBum());
	}
	
	
	/**
	 * @deprecated Use pixAreaItr(...) method instead!
	 * 
	 * Finds the area of a region containing the indicated pixel at (x,y)
	 * This recursive code words for small input but quickly errors: StackOverflow
	 * Need to use the iterative variation of this algorithm.
	 * Perhaps use tail recursion in this algorithm?
	 * 
	 * @param x
	 * @param y
	 * @param pixChecked
	 * @param r
	 * @return
	 */
	public int pixArea(int x, int y, boolean [][] pixChecked, Raster r) {
		
		int sumNorth = 0;
		int sumEast = 0;
		int sumSouth = 0;
		int sumWest = 0;
		
		
		System.out.println("checking "+x+", "+y);
		
		pixChecked[x][y] = true;
		
		
		// check north
		if(y-1 >= 0) {
			if(pixChecked[x][y-1] == false) {
				
				int[] colorThis = new int[4];
				r.getPixel(x, y, colorThis);
				
				int[] colorNorth = new int[4];
				r.getPixel(x, y-1, colorNorth);
				
				if(colorThis[0]==colorNorth[0] && colorThis[1]==colorNorth[1] && colorThis[2]==colorNorth[2]) {
					sumNorth = pixArea(x, y-1, pixChecked, r);
				}
			}
		}
		
		// check east
		if(x+1 < r.getWidth()) {
			if(pixChecked[x+1][y] == false) {
				
				int[] colorThis = new int[4];
				r.getPixel(x, y, colorThis);
				
				int[] colorEast = new int[4];
				r.getPixel(x+1, y, colorEast);
				
				if(colorThis[0]==colorEast[0] && colorThis[1]==colorEast[1] && colorThis[2]==colorEast[2]) {
					sumEast = pixArea(x+1, y, pixChecked, r);
				}
			}
		}
		
		// check south
		if(y+1 < r.getHeight()) {
			if(pixChecked[x][y+1] == false) {
				
				int[] colorThis = new int[4];
				r.getPixel(x, y, colorThis);
				
				int[] colorSouth = new int[4];
				r.getPixel(x, y+1, colorSouth);
				
				if(colorThis[0]==colorSouth[0] && colorThis[1]==colorSouth[1] && colorThis[2]==colorSouth[2]) {
					sumEast = pixArea(x, y+1, pixChecked, r);
				}
			}
		}
		
		// check west
		if(x-1 >= 0) {
			if(pixChecked[x-1][y] == false) {
				
				int[] colorThis = new int[4];
				r.getPixel(x, y, colorThis);
				
				int[] colorWest = new int[4];
				r.getPixel(x-1, y, colorWest);
				
				if(colorThis[0]==colorWest[0] && colorThis[1]==colorWest[1] && colorThis[2]==colorWest[2]) {
					sumEast = pixArea(x-1, y, pixChecked, r);
				}
			}
		}
		
		
		
		return sumNorth + sumEast + sumSouth + sumWest + 1;
		
	}
	
	public class PixBox {
		public int x;
		public int y;
	}
	
	
	/**
	 * Iterative algorithm to find the area of a region with indicated pixel
	 * @param x
	 * @param y
	 * @param pixChecked
	 * @param r
	 * @return
	 */
	public int pixAreaItr(int x, int y, Raster r) {
		
		boolean end = false;
		int area = 1;
		
		Stack<PixBox> prevPixStack = new Stack<Engine.PixBox>();
		
		while(!end) {
			
			boolean nDone = false;
			boolean eDone = false;
			boolean sDone = false;
			boolean wDone = false;
			
			//System.out.println("checking pixel ("+x+","+y+")");
			pixChecked[x][y] = true;

			
			// check north
			if(y-1 >= 0) {
				if(pixChecked[x][y-1] == false) {
					
					int[] colorThis = new int[4];
					r.getPixel(x, y, colorThis);
					
					int[] colorNorth = new int[4];
					r.getPixel(x, y-1, colorNorth);
					
					if(colorThis[0]==colorNorth[0] && colorThis[1]==colorNorth[1] && colorThis[2]==colorNorth[2]) {
						PixBox pb = new PixBox();
						pb.x = x;
						pb.y = y;
						prevPixStack.push(pb);
						
						x = x;
						y = y-1;
						area++;
						
						continue;
					}
					else {
						nDone = true;
						//System.out.println("north done, different color");
					}
				}
				else {
					//System.out.println("north done, checked");
					nDone = true;
				}
			}
			else {
				//System.out.println("north done, out of bounds");
				nDone = true;
			}
			
			// check east
			if(x+1 < r.getWidth()) {
				if(pixChecked[x+1][y] == false) {
					
					int[] colorThis = new int[4];
					r.getPixel(x, y, colorThis);
					
					int[] colorEast = new int[4];
					r.getPixel(x+1, y, colorEast);
					
					if(colorThis[0]==colorEast[0] && colorThis[1]==colorEast[1] && colorThis[2]==colorEast[2]) {
						PixBox pb = new PixBox();
						pb.x = x;
						pb.y = y;
						prevPixStack.push(pb);
						
						x = x+1;
						y = y;
						area++;
						
						continue;
					}
					else {
						eDone = true;
						//System.out.println("east done, different color");
					}
				}
				else {
					eDone = true;
					//System.out.println("east done, checked");
				}
			}
			else {
				eDone = true;
				//System.out.println("east done, out of bounds");
			}
			
			
			// check south
			if(y+1 < r.getHeight()) {
				if(pixChecked[x][y+1] == false) {
					
					int[] colorThis = new int[4];
					r.getPixel(x, y, colorThis);
					
					int[] colorSouth = new int[4];
					r.getPixel(x, y+1, colorSouth);
					
					if(colorThis[0]==colorSouth[0] && colorThis[1]==colorSouth[1] && colorThis[2]==colorSouth[2]) {
						PixBox pb = new PixBox();
						pb.x = x;
						pb.y = y;
						prevPixStack.push(pb);
						
						x = x;
						y = y+1;
						area++;
						
						continue;
					}
					else {
						sDone = true;
						//System.out.println("south done, different color");
					}
				}
				else {
					sDone = true;
					//System.out.println("south done, checked");
				}
			}
			else {
				sDone = true;
				//System.out.println("south done, out of bounds");
			}
			
			// check west
			if(x-1 >= 0) {
				if(pixChecked[x-1][y] == false) {
					
					int[] colorThis = new int[4];
					r.getPixel(x, y, colorThis);
					
					int[] colorWest = new int[4];
					r.getPixel(x-1, y, colorWest);
					
					if(colorThis[0]==colorWest[0] && colorThis[1]==colorWest[1] && colorThis[2]==colorWest[2]) {
						PixBox pb = new PixBox();
						pb.x = x;
						pb.y = y;
						prevPixStack.push(pb);
						
						x = x-1;
						y = y;
						area++;
						
						continue;
					}
					else {
						wDone = true;
						//System.out.println("west done, different color");
					}
				}
				else {
					wDone = true;
					//System.out.println("west done, checked");
				}
			}
			else {
				wDone = true;
				//System.out.println("west done, out of bounds");
			}

			
			if(nDone && eDone && sDone && wDone) {
				try {
					//System.out.println("stack size: " + prevPixStack.size());
					sumX += x;
					sumY += y;
					
					PixBox pb = prevPixStack.pop();
					x = pb.x;
					y = pb.y;
				}
				catch (EmptyStackException e) {
					end = true;
				}
			}
			
		}
		
		return area;
	}

	
}
