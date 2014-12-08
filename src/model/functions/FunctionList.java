package model.functions;

import java.util.ArrayList;
import java.util.List;

public class FunctionList {
	
	private static FunctionList instance = null;
	private List<FitnessFunction> funcs;
	
	private FunctionList(){
		funcs = new ArrayList<FitnessFunction>();
		funcs.add(new MaxOnes());
		funcs.add(new LeadingZeros());
	}
	
	public static FunctionList getInstance(){
		return instance;
	}

}
