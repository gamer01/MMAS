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
		int[][] actual = ImageToolbox.pixelMatrix(MMAS.model.getMutableImg()
				.getImage());

		double fitness = 0;
		
		for (int x = 0; x < source.length; x++) {
			for (int y = 0; y < source[0].length; y++) {
				// sum over all pixels 1-absolute error
				fitness+=1.0-(Math.abs(source[x][y]-actual[x][y])/Math.pow(2, 8));

			}
		}

		return fitness;
	}

	@Override
	public BitSet getOptimum(int graphSize) {
		return new MutableImage(ImageToolbox.getImage()).getBitSet();
	}

}
