package model;

import java.util.BitSet;
import java.util.Random;

import model.functions.IFitnessFunction;

public class Individual {

	private int length; // because BitSet sometimes apears to be smaller
	private BitSet value;
	private IFitnessFunction fitnessFunction;

	public Individual(IFitnessFunction fitnessFunction, LinearDAG graph) {
		length = graph.getSize();
		value = new BitSet(length);
		this.fitnessFunction = fitnessFunction;

		double[] oneProbabilities = graph.getProbabilities();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// the bit is set according to probability
			if (random.nextDouble() < oneProbabilities[i]) {
				value.set(i);
			}
		}
	}

	@Override
	public String toString() {
		String valueString = "";
		for (int i = 0; i < length; i++) {
			valueString += value.get(i) ? "1" : "0";
		}
		return valueString;
	}

	protected BitSet getBitSet() {
		return value;
	}

	public int getFitness() {
		return fitnessFunction.getFittness(this);
	}
}
