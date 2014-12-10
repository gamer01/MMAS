package model;

import java.awt.image.BufferedImage;

import model.functions.FitnessFunction;
import model.functions.ImageDifference;
import controller.ImageToolbox;

public class GraphModel {

	private FitnessFunction fitnessFunction;
	private double evaporationRate;

	private LinearDAG graph;

	private BufferedImage inputImg = null;
	private MutableImage outputImg = null;

	public GraphModel(UserInput input) {
		fitnessFunction = input.getFitnessFunction();
		evaporationRate = input.getEvaporation();
		graph = new LinearDAG(input.getSize());

		if (isFunctionImage()) {
			inputImg = ImageToolbox.getImage();
			outputImg = new MutableImage(inputImg);
		}
	}

	public void updatePheromones(Individual fittest) {
		int size = graph.getSize();
		double bound = (1.0D / size);
		double[] onePheromones = graph.getProbabilities();
		for (int i = 0; i < size; i++) {
			if (fittest.getBitSet().get(i)) { // if 1-edge was used
				double limit = 1 - bound;
				double newPheromonRate = (1 - evaporationRate)
						* onePheromones[i] + evaporationRate;
				onePheromones[i] = limit < newPheromonRate ? limit
						: newPheromonRate;
			} else {
				double limit = bound;
				double newPheromonRate = (1 - evaporationRate)
						* onePheromones[i];
				onePheromones[i] = limit > newPheromonRate ? limit
						: newPheromonRate;
			}
		}
	}

	public int getGraphSize() {
		return graph.getSize();
	}

	public double[] getProbabilities() {
		return graph.getProbabilities();
	}

	public Individual createNewIndividual() {
		return new Individual(fitnessFunction, graph);
	}

	public String getFunctionName() {
		return fitnessFunction.getName();
	}

	public FitnessFunction getFunction() {
		return fitnessFunction;
	}

	public double getEvaporationRate() {
		return evaporationRate;
	}

	public boolean isFunctionImage() {
		return fitnessFunction instanceof ImageDifference;
	}

	public BufferedImage getInputImg() {
		return inputImg;
	}

	public MutableImage getMutableImg() {
		return outputImg;
	}
}
