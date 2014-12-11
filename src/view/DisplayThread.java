package view;

import model.GraphModel;

public class DisplayThread implements Runnable {

	private Display display;
	private GraphModel model;

	public DisplayThread(GraphModel model) {
		this.model = model;
	}

	@Override
	public void run() {
		display = new Display(model);
	}

	public Display getDisplay() {
		return display;
	}
}
