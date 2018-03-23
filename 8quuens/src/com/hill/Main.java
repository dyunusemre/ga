package com.hill;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HillClimbingImp hill = new HillClimbingImp(20000);
		ArrayList<Element> solutions = new ArrayList<>();
		Element e;
		int[] solution;
		for(int i = 0; i < 25; i++) {
			if((solution = hill.hillClimbing()) != null) {
				e = new Element(solution,hill.getProcessTime(),hill.getRandomRestart(),hill.getReplacement());
				solutions.add(e);
			}
		}
		for (Element x : solutions) {
			x.tostring();
		}
		System.out.println(solutions.size());
	}

}
