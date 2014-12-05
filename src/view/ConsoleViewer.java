package view;

import model.GraphModel;
import model.Individual;

public class ConsoleViewer {

	private static final int LINEWITH = 10;
	private static final int PRECITION = 4;

	private GraphModel model;

	public ConsoleViewer(GraphModel model) {
		this.model = model;
	}

	public void redraw(int iteration, Individual fittest) {
		clear();
		printHead(iteration, fittest);
		printPheromones();
	}

	private static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private void printHead(int iteration, Individual fittest) {
		System.out.println("MMAS Simulator - current fitness function: "
				+ model.getFunctionName());
		System.out.println();
		System.out.printf(" Fitness value: %-12s      \tIteration: %05d\n",
				fittest.getFitness(), iteration);
		System.out.printf(" Binary value:  %s\n\n", fittest.toString());
	}

	private void printPheromones() {
		double[] pheromones = model.getProbabilities();

		for (int i = 0; i < pheromones.length;) {
			String oneProbabilities = " ";
			String zeroProbabilities = " ";
			String whichX = " ";
			for (int elemsOnLine = 0; elemsOnLine < LINEWITH
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
			System.out.println(whichX + "\n\n");
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
