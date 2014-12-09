package model.functions;

import java.math.BigDecimal;
import java.util.BitSet;

import model.Individual;

public abstract class FitnessFunction {

	public abstract int getFittness(Individual i);

	public String getComplexityInfo(int graphSize, double evaporationFactor) {
		BigDecimal iterations = new BigDecimal(2).pow(graphSize);

		return " Iterations by trying out: " + String.format("%1.3e",iterations) + "\n MMAS needs 𝓞("
				+ getThisComplexity() + ") for "
				+ this.getName() + "\n\n";
	}

	public abstract String getThisComplexity();

	public abstract BitSet getOptimum(int graphSize);

	public abstract String getName();
}