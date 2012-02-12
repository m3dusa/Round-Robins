import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;


public class Engine {
	private static Engine eng = null;
	
	LinkedList<ImageFrame> imgList = new LinkedList<ImageFrame>();
	
	BufferedImage bi;
	
	
	public Engine() {
		
	}
	
	public static Engine getInstant() {
		if(eng == null) {
			eng = new Engine();
		}
		return eng;
	}
	
	
	public void addImage(ImageFrame imgFrame) {
		imgList.add(imgFrame);
		
		if(imgList.size()>=2) {
			ImageFrame if1 = imgList.getFirst();
			imgList.removeFirst();
			ImageFrame if2 = imgList.getFirst();
			imgList.removeFirst();
			
			deltaComparison(if1, if2);
		}
	}
	
	public void deltaComparison(ImageFrame if1, ImageFrame if2) {
		for (int col = 0; col < if1.getHeight(); col++) {
			for (int row = 0; row < if1.getWidth(); row++) {
				int[] d1Array = new int[4];
				if1.getRar().getPixel(row, col, d1Array);
				System.out.println("image 1: " + d1Array[0] + " " + d1Array[1]
						+ " " + d1Array[2] + " " + d1Array[3]);

				int[] d2Array = new int[4];
				if2.getRar().getPixel(row, col, d2Array);
				System.out.println("image 2: " + d2Array[0] + " " + d2Array[1]
						+ " " + d2Array[2] + " " + d2Array[3]);

				if (d1Array[0] != d2Array[0] || d1Array[1] != d2Array[1]
						|| d1Array[2] != d2Array[2] || d1Array[3] != d2Array[3]) {
					if1.getBum().setRGB(row, col, 0);
				}
			}
		}

		File output = new File("img_out.png");
		try {
			ImageIO.write(if1.getBum(), "png", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
