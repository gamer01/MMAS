package model.functions;

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
		return getFittness(i.getBitSet());
	}

	@Override
	public double getFittness(BitSet binval) {
		int[][] source = ImageToolbox.pixelMatrix(MMAS.model.getInputImg());
		int[][] actual = new int[source.length][source[0].length];

		// for all elements in bitcode, take 8 elements ant put it in a pixle
		for (int x = 0; x < actual.length; x++) {
			for (int y = 0; y < actual[0].length; y++) {
				for (int bits = 0; bits < 8; bits++) {
					int index = (x * actual.length + y) * actual[0].length
							+ bits;
					actual[x][y] += (int) (Math.pow(2, 7 - bits) * (binval
							.get(index) ? 1 : 0));

				}
			}
		}

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
		MutableImage mutImg = new MutableImage(ImageToolbox.getImage());
		BitSet val = mutImg.getBitSet();
		return val;
	}

}
