package controller;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;

import javax.imageio.ImageIO;

public class ImageToolbox {

	public static int numberOfBits(BufferedImage img) {
		return 8 * img.getWidth() * img.getHeight();
	}

	public static BufferedImage loadImage() {
		File imageFile = new File("image.png");
		BufferedImage img = null;

		try {
			img = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.out.println("could not read img");
		}

		return img;
	}

	public static BufferedImage loadImage(String name) {
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

	public static BufferedImage plainCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		raster.setPixels(0, 0, bi.getWidth(), bi.getHeight(),
				new int[bi.getWidth() * bi.getHeight()]);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public static int[][] imgToMatrix(BufferedImage img) {
		int[][] pixelMatrix = new int[img.getWidth()][img.getHeight()];

		WritableRaster raster = img.getRaster();

		int[] pixelList = raster.getPixels(0, 0, img.getWidth(),
				img.getHeight(), (int[]) null);

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int index = y * img.getWidth() + x;
				pixelMatrix[x][y] = pixelList[index];
			}
		}

		return pixelMatrix;
	}

	public static int[][] bitsetToMatrix(BitSet binval, int width, int height) {
		int[][] matrix = new int[width][height];

		// for all elements in bitcode, take 8 elements ant put it in a pixel
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int bits = 0; bits < 8; bits++) {
					int index = ((y * width) + x) * 8 + bits;
					matrix[x][y] += (int) (Math.pow(2, 7 - bits) * (binval
							.get(index) ? 1 : 0));
				}
			}
		}
		return matrix;
	}
}
