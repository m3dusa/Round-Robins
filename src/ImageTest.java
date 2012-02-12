import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTest {

	public static void main(String[] args) {

		ImageFrame if1 = new ImageFrame("img1.png");
		ImageFrame if2 = new ImageFrame("img2.png");
		
		Engine.getInstant().addImage(if1);
		Engine.getInstant().addImage(if2);
		
	}

}
