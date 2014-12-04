package view;

import model.GraphModel;
import model.Individual;

public class ConsoleViewer {
	
	private GraphModel model;
	
	public ConsoleViewer(GraphModel model){
		this.model=model;
	}

	public static void redraw(int iteration, Individual fittest){
		//TODO create the whole drawing and redrawing part
		clear();
	}
	
	private static void clear(){
		//TODO clear screen
	}

}
