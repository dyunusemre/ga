package com.hill;

public class Element {
	int[] solvedQueens;
	long processTime;
	int randomRestart;
	int replacement;
	
	public Element(int[] arr, long process, int restart, int replacement) {
		// TODO Auto-generated constructor stub
		this.solvedQueens = arr;
		this.processTime = process;
		this.randomRestart = restart;
		this.replacement = replacement;
	}
	
	public void tostring() {
		System.out.println("================");
		System.out.println("BOARD");
		Util.printQueens(solvedQueens);
		System.out.println("Process Time => " + processTime + "ms");
		System.out.println("Random Restart => " + randomRestart);
		System.out.println("Replacement => " + replacement);
	}
}
