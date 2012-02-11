import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageFrame {
	private String location;
	private Raster rar;
	private int height;
	private int width;
	private BufferedImage bum;
	
	public ImageFrame(String location){
		this.location = location;
		File f1 = new File(location);
		bum = null;
		try {
			bum = ImageIO.read(f1 );
		} catch (IOException e1) { 	
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		rar = bum.getData();
		
		height = rar.getHeight();
		width = rar.getWidth();
		
	}

	public String getLocation() {
		return location;
	}

	public Raster getRar() {
		return rar;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public BufferedImage getBum(){
		return bum;
	}
	
}
