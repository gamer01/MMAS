package model;

public class LinearDAG {

	private double[] oneProbabilities;

	public LinearDAG(int size) {
		oneProbabilities = new double[size];
		for (int i = 0; i < oneProbabilities.length; i++) {
			oneProbabilities[i] = .5d;
		}
	}

	protected double[] getProbabilities() {
		return oneProbabilities;
	}

	protected int getSize() {
		return oneProbabilities.length;
	}
}
