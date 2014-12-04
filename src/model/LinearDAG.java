package model;

public class LinearDAG {
	
	private double[] oneProbabilities;
	
	public LinearDAG(int size){
		oneProbabilities = new double[size];
		for (double elem : oneProbabilities){
			elem=.5D;
		}
	}
	
	protected double[] getProbabilities(){
		return oneProbabilities;
	}

	protected int getSize() {
		return oneProbabilities.length;
	}

}
