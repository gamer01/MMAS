package model;

public class GraphModel {
	
	private IFitnessFunction fitnessFunction;
	private double evaporationRate;
	
	private LinearDAG graph;
	private long iterations=0L;
	private Individual fittestSoFar;
	
	public GraphModel(int size, IFitnessFunction fitnessFunction, double evaporationRate){
		graph=new LinearDAG(size);
		this.fitnessFunction=fitnessFunction;
		this.evaporationRate=evaporationRate;
	}

	public void updatePheromones() {
		// TODO Auto-generated method stub
	}
	
	public int getGraphSize(){
		return graph.getSize();
	}

	public Individual createNewIndividual() {
		return new Individual(getGraphSize(), fitnessFunction, graph);
	}

}
