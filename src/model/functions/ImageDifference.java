package model.functions;

import java.awt.image.BufferedImage;
import java.util.BitSet;

import model.Individual;
import model.MutableImage;
import controller.ImageToolbox;
import controller.MMAS;

public class ImageDifference extends FitnessFunction {

	@Override
	public String getName() {
		return "ImageDifference";
	}

	@Override
	public String getComplexityInfo(int graphSize, double evaporationFactor) {
		return "";
	}

	@Override
	public String getThisComplexity() {
		return "";
	}

	@Override
	public double getFittness(Individual i) {
		BitSet binval = i.getBitSet();

		BufferedImage sourceImg = MMAS.model.getInputImg();
		int[][] source = ImageToolbox.imgToMatrix(sourceImg);
		int[][] actual = ImageToolbox.bitsetToMatrix(binval,
				sourceImg.getWidth(), sourceImg.getHeight());

		double fitness = 0;

		for (int x = 0; x < source.length; x++) {
			for (int y = 0; y < source[0].length; y++) {
				// sum over all pixels 1-absolute error
				fitness += 1 - (Math.abs(source[x][y] - actual[x][y]) / Math
						.pow(2, 8));
			}
		}
		return fitness;
	}

	@Override
	public BitSet getOptimum(int graphSize) {
		MutableImage mutImg = new MutableImage(ImageToolbox.loadImage());
		BitSet val = mutImg.getBitset();
		return val;
	}
}