package controller;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageToolbox {

	public static int numberOfBits(BufferedImage img) {
		return 8 * img.getWidth() * img.getHeight();
	}

	public static BufferedImage getImage() {
		File imageFile = new File("image.png");
		BufferedImage img = null;

		try {
			img = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.out.println("could not read img");
		}

		return img;
	}

	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public static int[][] pixelMatrix(BufferedImage img) {
		int[][] pixelMatrix= new int[img.getWidth()][img.getHeight()];
		
		WritableRaster raster = img.getRaster();
		
		int[] pixelList = raster.getPixels(0, 0, img.getWidth(), img.getHeight(), (int[]) null);
		
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				pixelMatrix[x][y] = pixelList[x*img.getWidth()-1+img.getHeight()-1] ;
			}
		}
		
		return null;
	}
}
