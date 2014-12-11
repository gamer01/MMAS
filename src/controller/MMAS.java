package controller;

import java.util.BitSet;

import model.GraphModel;
import model.Individual;
import model.UserInput;
import view.ConsoleViewer;
import view.Display;
import view.DisplayThread;

public class MMAS {

	public static GraphModel model;
	private static ConsoleViewer view;
	private static Display display = null;

	private static int iteration = 1;
	private static Individual fittest;
	private static long iterationTime;
	private static BitSet optimum;

	public static void main(String[] args) {

		setIterationTime(args);

		model = new GraphModel(UserInput.getInstance(args));
		optimum = model.getFunction().getOptimum(model.getGraphSize());
		view = new ConsoleViewer(model);

		if (model.isFunctionImage()) {

			DisplayThread thread = new DisplayThread(model);
			thread.run();
			display = thread.getDisplay();
		}

		fittest = model.createNewIndividual();
		update(fittest);

		while (fittestIndividualNotOptimum()) {
			iteration++;
			try {
				Thread.sleep(iterationTime);
			} catch (InterruptedException e) {
			}
			Individual newSolution = model.createNewIndividual();
			if (newSolution.getFitness() > fittest.getFitness()) {
				fittest = newSolution;
			}
			update(newSolution);
		}
		view.goodBye();
	}

	private static void setIterationTime(String[] args) {
		final long DEFAULT_ITERATIONTIME = 100L;
		iterationTime = DEFAULT_ITERATIONTIME;

		// set the iteration Time
		try {
			iterationTime = Long.parseLong(args[3]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			iterationTime = DEFAULT_ITERATIONTIME;
		}

		ConsoleViewer.clear();
	}

	private static boolean fittestIndividualNotOptimum() {
		return iteration < 99999 && !fittest.getBitSet().equals(optimum);
	}

	private static void update(Individual current) {
		model.updatePheromones(fittest);
		view.redraw(iteration, fittest, current);
		if (model.isFunctionImage()) {
			model.getMutableImg().update(fittest);
			display.update();
		}
	}

}
