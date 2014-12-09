package model;

import java.util.Scanner;

import view.ConsoleViewer;
import model.functions.FitnessFunction;
import model.functions.FunctionList;

public class UserInput {

	private boolean noGreetingSoFar = true;
	private Scanner in = new Scanner(System.in);
	
	private static UserInput instance = null;
	
	private UserInput (String[] cliArg){
		setFitnessFunction(cliArg,0);
		setEvaporation(cliArg, 1);
		setSize(cliArg, 2);
	}
	
	public static UserInput getInstance(String[] cliArg){
		if (instance == null){
			new UserInput (cliArg);
		}
		return instance;
	}
	
	private static FitnessFunction func = null;
	private static double evap = -1;
	private static int size = -1;

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
	
	protected FitnessFunction getFitnessFunction (){
		return func;
	}
	
	private void setFitnessFunction(String[] cliArg, int index) {
		func = FunctionList.getInstance().get(0);
		int fnumber = 0;

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

	protected double getEvaporation(){
		return evap;
	}
	
	private void setEvaporation(String[] cliArg, int index) {
		final double DEFAULT_EVAPORATION = 0.5D;

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

	protected int getSize (){
		return size;
	}
	
	private void setSize(String[] cliArg, int index) {
		final int DEFAULT_SIZE = 16;
		size = DEFAULT_SIZE;

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

	private void printGreeting() {
		// only print heading if we need to userInput anything
		if (noGreetingSoFar) {
			ConsoleViewer.clear();
			System.out.println("MMAS Simulator\n");
			noGreetingSoFar = false;
		}
	}
	


}
