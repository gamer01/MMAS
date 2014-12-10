package model.functions;

import java.util.BitSet;

import model.Individual;

public class LeadingZeros extends FitnessFunction {

	@Override
	public String getName() {
		return "LeadingZeros";
	}

	@Override
	public double getFittness(Individual individual) {
		BitSet binVal = individual.getBitSet();
		int codeLength = individual.getLengh();

		int leadingZeros = 0;
		while (leadingZeros < codeLength && !binVal.get(leadingZeros)) {
			leadingZeros++;
		}
		return leadingZeros;
	}

	@Override
	public BitSet getOptimum(int graphSize) {
		return new BitSet(graphSize);
	}

	@Override
	public String getThisComplexity() {
		return "𝓞(n²+(n log n)/ρ)";
	}

	@Override
	public double getFittness(BitSet binval) {
		throw new UnsupportedOperationException();
	}
}
