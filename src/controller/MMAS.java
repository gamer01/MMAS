package controller;

import view.ConsoleViewer;
import model.*;

public class MMAS {
	
	private static GraphModel model;
	private static ConsoleViewer view;
	private static int iteration =0;
	private static Individual fittest;

	public static void main(String[] args) {
		
		model=new GraphModel(10,new MaxOnes(),0.5D);
		view=new ConsoleViewer(model);
		
		fittest = model.createNewIndividual();
		update();
		
		while(fitestIndividualNotOptimum()){
			Individual newSolution = model.createNewIndividual();
			if(newSolution.getFitness()>fittest.getFitness()){
				fittest=newSolution;
			}
			update();
		}
	}

	private static boolean fitestIndividualNotOptimum() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static void update(){
		model.updatePheromones(fittest);
		view.redraw(iteration,fittest);
	}

}
