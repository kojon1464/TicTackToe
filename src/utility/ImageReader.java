package utility;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageReader {

	public static BufferedImage readImage(String path) {
		
		BufferedImage image;
		
		try {
			image = ImageIO.read(new File(path));
		} catch (Exception e) {
			image = null;
		}
		return image;
	}
	
	public static Dimension getDimension(String path) {
		
		BufferedImage image = readImage(path);
		
		return new Dimension(image.getWidth(), image.getHeight());
	}
	
	public static Dimension getDimension(String path, int multiplyer) {
		
		BufferedImage image = readImage(path);
		
		return new Dimension(image.getWidth() * multiplyer, image.getHeight() * multiplyer);
	}
}
