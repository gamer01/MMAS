package model;

public class MaxOnes implements IFitnessFunction {

	@Override
	public int getFittness(Individual individual) {
		return individual.getBitSet().cardinality();
	}

}
