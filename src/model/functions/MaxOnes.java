package model.functions;

import java.util.BitSet;

import model.Individual;

public class MaxOnes extends FitnessFunction {

	private static final String name = "MaxOnes";

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getThisComplexity() {
		return "(n log n)/ρ";
	}

	@Override
	public int getFittness(Individual individual) {
		return individual.getBitSet().cardinality();
	}

	@Override
	public BitSet getOptimum(int graphSize) {
		BitSet optimum = new BitSet(graphSize);
		for (int i = 0; i < graphSize; i++) {
			optimum.set(i);
		}
		return optimum;
	}
}
