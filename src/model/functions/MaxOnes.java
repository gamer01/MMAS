package model.functions;

import java.util.BitSet;

import model.Individual;

public class MaxOnes extends FitnessFunction {

	@Override
	public String getName() {
		return "MaxOnes";
	}

	@Override
	public String getThisComplexity() {
		return "𝓞((n log n)/ρ)";
	}

	@Override
	public double getFittness(Individual individual) {
		return getFittness(individual.getBitSet());
	}

	@Override
	public double getFittness(BitSet binval) {
		return binval.cardinality();
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
