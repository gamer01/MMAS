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
	private static long iterationTime;
	private static BitSet optimum;

	private static final long DEFAULT_ITERATIONTIME = 100L;
	private static final double DEFAULT_EVAPORATION = 0.5D;
	private static final int DEFAULT_SIZE = 16;

	private static FitnessFunction func;
	private static int size = DEFAULT_SIZE;
	private static double evap = DEFAULT_EVAPORATION;
	private static boolean noGreetingSoFar = true;

	public static void main(String[] args) {

		initialize(args);

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

	/**
	 * The application will use the first up to 4 command line arguments to
	 * initialize variables:
	 * 
	 * func evap size iterationTime
	 * 
	 * in that order. If arguments are missing or can not be parsed it will use
	 * either defaults or user inputs
	 */
	private static void initialize(String[] args) {
		iterationTime = DEFAULT_ITERATIONTIME;
		func = FunctionList.getInstance().get(0);

		setFitnessfunction(args, 0);
		setEvaporation(args, 1);
		setSize(args, 2);

		// set the iteration Time
		try {
			iterationTime = Long.parseLong(args[3]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			iterationTime = DEFAULT_ITERATIONTIME;
		}

		optimum = func.getOptimum(size);

		ConsoleViewer.clear();
	}

	private static void setFitnessfunction(String[] cliArg, int index) {
		int fnumber = 0;

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		try {
			fnumber = Integer.parseInt(cliArg[index]) - 1;
			if (!(fnumber >= 0 && fnumber < FunctionList.getInstance().size())) {
				throw new NumberFormatException();
			}
			func = FunctionList.getInstance().get(fnumber);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			try {
				printGreeting();
				// Reads a single line from the console
				listFunctions();

				System.out
						.print(" Select one of the fitness functions by number: ");
				fnumber = Integer.parseInt(in.nextLine()) - 1;
				if (!(fnumber >= 0 && fnumber < FunctionList.getInstance()
						.size())) {
					throw new NumberFormatException();
				}
				func = FunctionList.getInstance().get(fnumber);
			} catch (NumberFormatException e2) {
				System.out
						.println(" Input could not be read, default is used: func="
								+ func.getName());
			}
		}
	}

	private static void setEvaporation(String[] cliArg, int index) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		try {
			evap = Double.parseDouble(cliArg[index]);
			if (!(0 < evap && evap < 1)) {
				throw new NumberFormatException();
			}
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			try {
				printGreeting();
				// Reads a single line from the console
				System.out.print(" Set ρ (from interval ]0,1[, default "
						+ DEFAULT_EVAPORATION + "): ");
				String next = in.nextLine();
				evap = Double.parseDouble(next);
				if (!(0 < evap && evap < 1)) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e2) {
				evap = DEFAULT_EVAPORATION;
				System.out
						.println(" Input could not be read, default is used: ρ="
								+ evap);
			}
		}
	}

	private static void setSize(String[] cliArg, int index) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		try {
			size = Integer.parseInt(cliArg[index]);
			if (size < 1) {
				throw new NumberFormatException();
			}
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			try {
				printGreeting();
				// Reads a single line from the console
				System.out.print(" Set n (from interval [1,∞[, default "
						+ DEFAULT_SIZE + "):  ");
				size = Integer.parseInt(in.nextLine());
				if (size < 1) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e2) {
				size = DEFAULT_SIZE;
				System.out
						.println(" Input could not be read, default is used: n="
								+ size);
			}
		}
	}

	private static void printGreeting() {
		// only print heading if we need to userInput anything
		if (noGreetingSoFar) {
			ConsoleViewer.clear();
			System.out.println("MMAS Simulator\n");
			noGreetingSoFar = false;
		}
	}

	private static boolean fittestIndividualNotOptimum() {
		return iteration < 99999 && !fittest.getBitSet().equals(optimum);
	}

	private static void listFunctions() {
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
	}

	private static void update(Individual current) {
		model.updatePheromones(fittest);
		view.redraw(iteration, fittest, current);
	}

}
