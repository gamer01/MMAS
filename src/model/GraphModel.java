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

	public void updatePheromones(Individual fittest) {
		// TODO Auto-generated method stub
	}
	
	public int getGraphSize(){
		return graph.getSize();
	}
	
	public double[] getProbabilities(){
		return graph.getProbabilities();
	}
 
	public Individual createNewIndividual() {
		return new Individual(fitnessFunction, graph);
	}
	
	public String getFunctionName(){
		return fitnessFunction.getName();
	}

}
