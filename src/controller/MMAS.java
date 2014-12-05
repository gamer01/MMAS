package controller;

import java.util.Scanner;

import view.ConsoleViewer;
import model.*;

public class MMAS {

	private static GraphModel model;
	private static ConsoleViewer view;
	private static int iteration = 0;
	private static Individual fittest;

	public static void main(String[] args) {
		
		System.out.println("MMAS Simulator\n");

		double evap = askEvaporation();

		model = new GraphModel(askSize(), askFitnessfunction(), evap);
		view = new ConsoleViewer(model);

		fittest = model.createNewIndividual();
		update(fittest);

		while (fittestIndividualNotOptimum()) {
			iteration++;
			try {
				Thread.sleep(200L);
			} catch (InterruptedException e) {
			}
			Individual newSolution = model.createNewIndividual();
			if (newSolution.getFitness() > fittest.getFitness()) {
				fittest = newSolution;
			}
			update(newSolution);
		}
	}

	private static double askEvaporation() {
		// default value
		final double defaul = 0.5D;
		double evaporation = defaul;

		System.out.print(" Set ρ (from interval ]0,1[, default " + defaul
				+ "): ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		// Reads a single line from the console
		try {
			evaporation = Double.parseDouble(in.nextLine());
			if (0 < evaporation && evaporation < 1) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			evaporation = defaul;
		}
		return evaporation;
	}

	private static IFitnessFunction askFitnessfunction() {
		// default value
		IFitnessFunction function = new MaxOnes();
		return function;
	}

	private static int askSize() {
		final int defaul = 16;
		int size = defaul;

		System.out.print(" Set n (from interval [1,∞[, default " + defaul
				+ "):  ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		// Reads a single line from the console
		try {
			size = Integer.parseInt(in.nextLine());
			if (size < 1) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			size = defaul;
		}

		return size;
	}

	private static boolean fittestIndividualNotOptimum() {
		// TODO Auto-generated method stub
		return iteration < 1000;
	}

	private static void update(Individual current) {
		model.updatePheromones(fittest);
		view.redraw(iteration, fittest, current);
	}

}
