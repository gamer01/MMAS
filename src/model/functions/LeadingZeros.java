package model.functions;

import java.util.BitSet;

import model.Individual;

public class LeadingZeros implements IFitnessFunction {

	private static final String name = "LeadingZeros";

	@Override
	public int getFittness(Individual individual) {
		BitSet binVal = individual.getBitSet();

		int leadingZeros = 0;
		while (leadingZeros < binVal.length() && !binVal.get(leadingZeros)) {
			leadingZeros++;
		}
		return leadingZeros;
	}

	@Override
	public String getName() {
		return name;
	}

}
