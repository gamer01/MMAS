package model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.BitSet;

import controller.ImageToolbox;

public class MutableImage {
	private BufferedImage image;

	public MutableImage(BufferedImage img) {
		image = img;
	}

	public BufferedImage getImage() {
		return image;
	}

	public BitSet getBitset() {
		return ImageToolbox.imgToBitSet(image);
	}

	public void update(Individual fittest) {
		BitSet binval = fittest.getBitSet();

		WritableRaster raster = image.getRaster();
		int[] pixels = ImageToolbox.bitsetToArray(binval, image.getWidth(),
				image.getHeight());

		raster.setPixels(0, 0, image.getWidth(), image.getHeight(), pixels);

		image.setData(raster);
	}
}