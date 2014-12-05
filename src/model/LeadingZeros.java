package model;

import java.util.BitSet;

public class LeadingZeros implements IFitnessFunction {
	
	private static final String name="LeadingZeros";

	public int getFittness(Individual individual) {
		BitSet binVal = individual.getBitSet();
		
		int leadingZeros=0;
		while(leadingZeros<binVal.length() && !binVal.get(leadingZeros)){
			leadingZeros++;
		}
		return leadingZeros;
	}

	@Override
	public String getName() {
		return name;
	}

}
