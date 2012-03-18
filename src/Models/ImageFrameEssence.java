package Models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageFrameEssence extends ImageFrame {

	ArrayList<DensityPix> densityPixList;
	
	public ImageFrameEssence(BufferedImage bum) {
		super(bum);
	}
	
	public void setDensityPixList(ArrayList<DensityPix> densityPixList) {
		this.densityPixList = densityPixList;
	}
	
	public ArrayList<DensityPix> getDensityPixList() {
		return densityPixList;
	}
	
}
