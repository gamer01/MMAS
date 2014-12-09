package model.functions;

import java.util.BitSet;

import model.Individual;
import model.functions.FitnessFunction;

public class And extends FitnessFunction {

	private static final String name = "And";

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getThisComplexity() {
		return "𝓞(nⁿ⸍²)";
	}

	@Override
	public int getFittness(Individual individual) {
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
