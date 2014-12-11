package model;

import java.util.BitSet;
import java.util.Random;

import model.functions.FitnessFunction;

public class Individual {

	private int length; // because BitSet has no fixed size
	private BitSet value;
	private FitnessFunction fitnessFunction;

	public Individual(FitnessFunction fitnessFunction, LinearDAG graph) {
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

	public BitSet getBitSet() {
		return value;
	}

	public int getLengh() {
		return length;
	}

	public double getFitness() {
		return fitnessFunction.getFittness(this);
	}
}