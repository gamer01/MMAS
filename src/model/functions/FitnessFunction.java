package model.functions;

import java.math.BigDecimal;
import java.util.BitSet;

import model.Individual;

public abstract class FitnessFunction {

	public abstract String getName();

	public String getComplexityInfo(int graphSize, double evaporationFactor) {
		BigDecimal iterations = new BigDecimal(2).pow(graphSize);

		return String.format(" Expected iterations when trying out: %1.3e\n",
				iterations)
				+ String.format(" MMAS needs %s for %s\n\n",
						getThisComplexity(), getName());
	}

	public abstract String getThisComplexity();

	public abstract double getFittness(Individual i);

	public abstract BitSet getOptimum(int graphSize);
}
