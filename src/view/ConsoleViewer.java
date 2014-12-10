package view;

import model.GraphModel;
import model.Individual;

public class ConsoleViewer {

	private int linewidth = 8;
	private static final int PRECITION = 5;

	private GraphModel model;
	private String complexity;

	public ConsoleViewer(GraphModel model) {
		this.model = model;
		linewidth = model.getGraphSize() > 16 ? (int) Math.ceil(Math.sqrt(model
				.getGraphSize() * 2.0D)) : linewidth;
		complexity = model.getFunction().getComplexityInfo(
				model.getGraphSize(), model.getEvaporationRate());
	}

	public void redraw(int iteration, Individual fittest, Individual current) {
		System.out.print("\033[H");
		System.out.flush();
		printHead(iteration, fittest, current);
		if (model.getGraphSize() <= 128 && !model.isFunctionImage()) {
			printPheromones();
		}
	}

	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private void printHead(int iteration, Individual fittest, Individual current) {
		System.out.println("MMAS Simulator - fitness-function="
				+ model.getFunctionName() + ", n=" + model.getGraphSize()
				+ ", ρ=" + model.getEvaporationRate());
		System.out.println();
		System.out.print(complexity);
		System.out.printf(" Best Fitness: %-12s      \tIteration: %05d\n",
				fittest.getFitness(), iteration);
		if (!model.isFunctionImage()) {
			System.out.printf(" Best Path:    %s\n", fittest.toString());
			System.out.printf(" Current Path: %s\n\n", current.toString());
		}
	}

	private void printPheromones() {
		double[] pheromones = model.getProbabilities();

		for (int i = 0; i < pheromones.length;) {
			if (i != 0) {
				System.out.print("\n\n");
			}
			String oneProbabilities = " ";
			String zeroProbabilities = " ";
			String whichX = " ";
			for (int elemsOnLine = 0; elemsOnLine < linewidth
					&& i < pheromones.length; elemsOnLine++) {
				oneProbabilities += String.format("%." + PRECITION + "f  ",
						pheromones[i]);
				zeroProbabilities += String.format("%." + PRECITION + "f  ",
						1 - pheromones[i]);
				whichX += String.format(" %" + ((PRECITION + 1) / 2)
						+ "s%-2s %" + PRECITION / 2 + "s", "x",
						subscript(i + 1), "");
				i++;
			}
			System.out.println(oneProbabilities);
			System.out.println(zeroProbabilities);
			System.out.println(whichX);
		}
	}

	private String subscript(int number) {
		String str = "" + number;
		str = str.replaceAll("0", "₀");
		str = str.replaceAll("1", "₁");
		str = str.replaceAll("2", "₂");
		str = str.replaceAll("3", "₃");
		str = str.replaceAll("4", "₄");
		str = str.replaceAll("5", "₅");
		str = str.replaceAll("6", "₆");
		str = str.replaceAll("7", "₇");
		str = str.replaceAll("8", "₈");
		str = str.replaceAll("9", "₉");
		return str;
	}

}
