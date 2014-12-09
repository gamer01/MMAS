package controller;

import java.util.BitSet;
import java.util.Scanner;

import view.ConsoleViewer;
import model.*;
import model.functions.FitnessFunction;
import model.functions.FunctionList;

public class MMAS {

	private static GraphModel model;
	private static ConsoleViewer view;
	private static int iteration = 1;
	private static Individual fittest;
	private static final long DEFAULT_ITERATIONTIME = 100L;
	private static long iterationTime;
	private static BitSet optimum;

	public static void main(String[] args) {

		if (args.length > 0) {
			try {
				iterationTime = Long.parseLong(args[0]);
			} catch (NumberFormatException e) {
				iterationTime = DEFAULT_ITERATIONTIME;
			}
		} else {
			iterationTime = DEFAULT_ITERATIONTIME;
		}

		ConsoleViewer.clear();
		System.out.println("MMAS Simulator\n");

		FitnessFunction func = askFitnessfunction();
		double evap = askEvaporation();
		int size = askSize();
		optimum = func.getOptimum(size);

		ConsoleViewer.clear();

		model = new GraphModel(size, func, evap);
		view = new ConsoleViewer(model);

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
	}

	private static double askEvaporation() {
		// default value
		final double defaul = 0.5D;
		double evaporation = defaul;

		System.out.print(" Set ρ (from interval ]0,1[, default " + defaul
				+ "): ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		String next = null;
		// Reads a single line from the console
		try {
			next = in.nextLine();
			evaporation = Double.parseDouble(next);
			if (!(0 < evaporation && evaporation < 1)) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			evaporation = defaul;
			System.out.println(" Input could not be read, default is used: ρ="
					+ evaporation);
		}
		return evaporation;
	}

	private static FitnessFunction askFitnessfunction() {
		FitnessFunction func = FunctionList.getInstance().get(0);
		int fnumber;

		// find longest functionname
		int maxFuncnameLength = 0;
		for (int i = 0; i < FunctionList.getInstance().size(); i++) {
			maxFuncnameLength = FunctionList.getInstance().get(i).getName()
					.length() > maxFuncnameLength ? FunctionList.getInstance()
					.get(i).getName().length() : maxFuncnameLength;
		}

		// draw upper border
		final int CHARS_BEFORE_AND_AFTER_FUNCTIONNAME = 8;
		System.out.println(" ┌"
				+ new String(new char[CHARS_BEFORE_AND_AFTER_FUNCTIONNAME
						+ maxFuncnameLength]).replace("\0", "═") + "┐");
		// draw functions enumeration
		for (int i = 0; i < FunctionList.getInstance().size(); i++) {
			System.out.printf(" │ %2d - %-" + maxFuncnameLength + "s  │\n",
					i + 1, FunctionList.getInstance().get(i).getName());
		}
		// draw lower border
		System.out.println(" └"
				+ new String(new char[CHARS_BEFORE_AND_AFTER_FUNCTIONNAME
						+ maxFuncnameLength]).replace("\0", "═") + "┘");

		System.out.print(" Select one of the fitness functions by number: ");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		// Reads a single line from the console
		try {
			fnumber = Integer.parseInt(in.nextLine()) - 1;
			if (!(fnumber >= 0 && fnumber < FunctionList.getInstance().size())) {
				throw new NumberFormatException();
			}
			func = FunctionList.getInstance().get(fnumber);
		} catch (NumberFormatException e) {
			System.out
					.println(" Input could not be read, default is used: func="
							+ func.getName());
		}

		// TODO default value
		return func;
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
			System.out.println(" Input could not be read, default is used: n="
					+ size);
		}
		return size;
	}

	private static boolean fittestIndividualNotOptimum() {
		return iteration < 99999 && !fittest.getBitSet().equals(optimum);
	}

	private static void update(Individual current) {
		model.updatePheromones(fittest);
		view.redraw(iteration, fittest, current);
	}

}
