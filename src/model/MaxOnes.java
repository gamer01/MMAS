package model;

public class MaxOnes implements IFitnessFunction {

	private static final String name = "MaxOnes";

	@Override
	public int getFittness(Individual individual) {
		return individual.getBitSet().cardinality();
	}

	@Override
	public String getName() {
		return name;
	}
}
