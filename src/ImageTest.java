import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTest {

	public static void main(String[] args) {

	

		
		ImageFrame if1 = new ImageFrame("img1.png");
		ImageFrame if2 = new ImageFrame("img2.png");
		

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
