
public class ImageTest {

	public static void main(String[] args) {

		ImageFrame if1 = new ImageFrame("img1.png");
		ImageFrame if2 = new ImageFrame("img2.png");
		
		Engine.getInstance().addImage(if1);
		Engine.getInstance().addImage(if2);
		
	}

}
