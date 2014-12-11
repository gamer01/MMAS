package model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.BitSet;

import controller.ImageToolbox;

public class MutableImage {
	private BufferedImage image;

	public MutableImage(BufferedImage img) {
		image = ImageToolbox.getPlainImg(img);
	}

	public BufferedImage getImage() {
		return image;
	}

	public BitSet getBitSet() {
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
						index = (x * image.getWidth() + y) * image.getHeight()
								+ valueOffset;
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
		int[] value = {0};
		int index;

		// for each pixel take 8 pixels from binary and set the pixel according
		// to it
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {

				for (int valueOffset = 0; valueOffset < 8; valueOffset++) {
					index = (x * image.getWidth() + y) * image.getHeight()
							+ valueOffset;
					value[0] += (int) (Math.pow(2, 7 - valueOffset) * (binval
							.get(index) ? 1 : 0));

				}

				raster.setPixel(x, y, value);
			}
		}
		
		image.setData(raster);
	}
}
