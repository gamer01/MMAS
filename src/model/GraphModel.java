package model;

public class GraphModel {

	private IFitnessFunction fitnessFunction;
	private double evaporationRate;

	private LinearDAG graph;

	public GraphModel(int size, IFitnessFunction fitnessFunction,
			double evaporationRate) {
		graph = new LinearDAG(size);
		this.fitnessFunction = fitnessFunction;
		this.evaporationRate = evaporationRate;
	}

	public void updatePheromones(Individual fittest) {
		int size=graph.getSize();
		double bound = (1.0D/size);
		double[] onePheromones = graph.getProbabilities();
		for (int i = 0; i < size; i++) {
//			TODO Double check this
			if(fittest.getBitSet().get(i)){ //if 1-edge was used
				double limit=1-bound;
				double newPheromonRate = (1-evaporationRate)*onePheromones[i]+evaporationRate;
				onePheromones[i] = limit < newPheromonRate ? limit : newPheromonRate;
			} else {
				double limit=bound;
				double newPheromonRate = (1-evaporationRate)*onePheromones[i];
				onePheromones[i] = limit > newPheromonRate ? limit : newPheromonRate;
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

}
