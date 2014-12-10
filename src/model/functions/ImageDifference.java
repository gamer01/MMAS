package model.functions;

import java.util.BitSet;

import controller.ImageToolbox;
import controller.MMAS;
import model.Individual;
import model.MutableImage;

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
		int[][] source = ImageToolbox.pixelMatrix(MMAS.model.getInputImg());
		int[][] actual = new int[source.length][source[0].length];

		BitSet binCode = i.getBitSet();

		// for all elements in bitcode, take 8 elements ant put it in a pixle
		for (int x = 0; x < actual.length; x++) {
			for (int y = 0; y < actual[0].length; y++) {
				for (int bits = 0; bits < 8; bits++) {
					int index = (x * actual.length + y) * actual[0].length
							+ bits;
					actual[x][y] = (int) (Math.pow(2, 8 - bits) * (binCode
							.get(index) ? 1 : 0));

				}
			}

		}
		// TODO use individual i, otherwise it the result is proved to be wrong

		double fitness = 0;

		for (int x = 0; x < source.length; x++) {
			for (int y = 0; y < source[0].length; y++) {
				// sum over all pixels 1-absolute error
				fitness += 1.0 - (Math.abs(source[x][y] - actual[x][y]) / Math
						.pow(2, 8));

			}
		}

		return fitness;
	}

	@Override
	public BitSet getOptimum(int graphSize) {
		return new MutableImage(ImageToolbox.getImage()).getBitSet();
	}

}
