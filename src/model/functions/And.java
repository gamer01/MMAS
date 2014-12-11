package model.functions;

import java.util.BitSet;

import model.Individual;

public class And extends FitnessFunction {

	@Override
	public String getName() {
		return "And";
	}

	@Override
	public String getThisComplexity() {
		return "𝓞(nⁿ⸍²)";
	}

	@Override
	public double getFittness(Individual individual) {
		return individual.getLengh() == individual.getBitSet().cardinality() ? 1
				: 0;
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
