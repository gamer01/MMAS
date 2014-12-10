package controller;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
	
	public static BufferedImage getImage(String name) {
		File imageFile = new File(name);
		BufferedImage img = null;

		try {
			img = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.out.println("File \"" + name + "\" was not found");
		}

		return img;
	}

	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	public static BufferedImage getPlainImg(BufferedImage bi){
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		raster.setPixels(0, 0, bi.getWidth(), bi.getHeight(), new int[bi.getWidth()*bi.getHeight()]);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public static int[][] pixelMatrix(BufferedImage img) {
		int[][] pixelMatrix= new int[img.getWidth()][img.getHeight()];
		
		WritableRaster raster = img.getRaster();
		
		int[] pixelList = raster.getPixels(0, 0, img.getWidth(), img.getHeight(), (int[]) null);
		
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				pixelMatrix[x][y] = pixelList[x*(img.getWidth()-1)+y] ;
			}
		}
		
		return pixelMatrix;
	}
	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	
}
