package model.functions;

import java.util.BitSet;

import model.Individual;

public class MaxOnes extends FitnessFunction {

	private static final String name = "MaxOnes";

	public int getFittness(Individual individual) {
		return individual.getBitSet().cardinality();
	}

	public String getName() {
		return name;
	}

	public String getComplexityInfo(int graphSize) {
		
		return null;
	}

	public BitSet getOptimum(int graphSize) {
		BitSet optimum = new BitSet(graphSize);
		for (int i = 0; i < graphSize; i++) {
			optimum.set(i);
		}
		return optimum;
	}

	public String getThisComplexity() {
		return "(n log n)/ρ";
	}
}
