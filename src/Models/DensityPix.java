package Models;

import java.awt.Point;

public class DensityPix extends Point {
	public int area, col;
	public DensityPix(int x, int y, int area, int col) {
		super(x, y);
		this.area = area;
		this.col = col;
	}
	public DensityPix(DensityPix densityPix) {
		this.area = densityPix.area;
		this.col = densityPix.col;
		this.x = densityPix.x;
		this.y = densityPix.y;
	}
}