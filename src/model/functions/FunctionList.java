package model.functions;

import java.util.ArrayList;
import java.util.List;

public class FunctionList {

	private static FunctionList instance = null;
	private List<FitnessFunction> funcs;

	private FunctionList() {
		funcs = new ArrayList<FitnessFunction>();
		funcs.add(new MaxOnes());
		funcs.add(new LeadingZeros());
		funcs.add(new And());
	}

	public static FunctionList getInstance() {
		if (instance == null) {
			instance = new FunctionList();
		}
		return instance;
	}

	public FitnessFunction get(int index) {
		return funcs.get(index);
	}
}
