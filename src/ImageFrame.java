import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to store image pixel data
 */
public class ImageFrame {
	private String location;
	private Raster rar;
	private BufferedImage bum;
	
	/**
	 * Constructor that extracts image pixel data
	 *  such as width, height, and raster.
	 * @param location The path-address to the image (ex: "img1.png")
	 */
	public ImageFrame(String location) {
		
		this.location = location;
		File f1 = new File(location);
		bum = null;
		try {
			bum = ImageIO.read(f1);
		} catch (IOException e1) { 	
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		rar = bum.getData();
		
	}
	
	public ImageFrame(BufferedImage bum) {
		this.bum = bum;
		rar = bum.getData();
	}

	public String getLocation() {
		return location;
	}

	public Raster getRar() {
		return rar;
	}

	public int getHeight() {
		return rar.getHeight();
	}

	public int getWidth() {
		return rar.getWidth();
	}
	
	public BufferedImage getBum() {
		return bum;
	}
	
}
