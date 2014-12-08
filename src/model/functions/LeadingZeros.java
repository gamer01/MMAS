package model.functions;

import java.util.BitSet;

import model.Individual;

public class LeadingZeros extends FitnessFunction {

	private static final String name = "LeadingZeros";

	public int getFittness(Individual individual) {
		BitSet binVal = individual.getBitSet();

		int leadingZeros = 0;
		while (leadingZeros < binVal.length() && !binVal.get(leadingZeros)) {
			leadingZeros++;
		}
		return leadingZeros;
	}

	public String getName() {
		return name;
	}

	public BitSet getOptimum(int graphSize) {
		return new BitSet(graphSize);
	}

	public String getThisComplexity() {
		return "n²+(n log n)/ρ";
	}
}
