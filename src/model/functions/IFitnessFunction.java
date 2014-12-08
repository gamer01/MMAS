package model.functions;

import model.Individual;

public interface IFitnessFunction {

	public int getFittness(Individual i);

	public String getName();
}
