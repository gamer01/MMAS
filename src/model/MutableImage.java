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
		return imgToBitSet(image);
	}

	private static BitSet imgToBitSet(BufferedImage image) {
		BitSet binary = new BitSet(ImageToolbox.numberOfBits(image));

		int value;
		int index;

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				value = image.getRaster().getPixel(x, y, (int[]) null)[0];
				char[] binValue = Integer.toBinaryString(0x100 | value)
						.substring(1).toCharArray();

				for (int valueOffset = 0; valueOffset < binValue.length; valueOffset++) {
					if (binValue[valueOffset] == '1') {
						index = (x * (image.getWidth() - 1) + y)
								* (image.getHeight() - 1) * 8 + valueOffset;
						binary.set(index);
					}
				}
			}
		}

		return binary;
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