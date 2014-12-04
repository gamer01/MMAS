package model;

import java.util.BitSet;
import java.util.Random;

public class Individual {
	
	BitSet value;
	IFitnessFunction fitnessFunction;
	
	public Individual (int length,IFitnessFunction fitnessFunction,LinearDAG graph){
		value = new BitSet(length);
		this.fitnessFunction=fitnessFunction;
		
		double[] oneProbabilities = graph.getProbabilities();
		Random random = new Random();
		for (int i = 0; i < oneProbabilities.length; i++) {
			// the bit is set according to probability
			if(random.nextDouble()<oneProbabilities[i]){
				value.set(i);
			}
		}		
	}
	
	public String toString(){
		String valueString="";
		for (int i = 0; i < value.length(); i++) {
			valueString+=value.get(i)?"1":"0";			
		}
		return valueString;
	}

	protected BitSet getBitSet(){
		return value;
	}
	
	public int getFitness() {
		return fitnessFunction.getFittness(this);
	}
}
