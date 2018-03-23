package com.hill;

import java.util.ArrayList;
import java.util.Arrays;

public class HillClimbingImp {
	private long processTime;
	private int replacement;
	private int randomRestart;
	private int iterationTime;
	public HillClimbingImp(int iterationTime) {
		this.iterationTime = iterationTime;
	}
	public long getProcessTime() {
		return processTime;
	}
	public int getRandomRestart() {
		return randomRestart;
	}
	public int getReplacement() {
		return replacement;
	}
	public int[] hillClimbing() {
		ArrayList<int[]> pastStates = new ArrayList<>();
		this.processTime = 0;
		this.replacement = 0;
		this.randomRestart = 1;
		int[] arr = Util.generateRandomState();
		pastStates.add(arr);
		int costToH = Util.heuristicCost(arr); 
		this.processTime = System.currentTimeMillis();
		
		for(int i = 0; i < iterationTime && costToH > 0; i++) {
			int tempCostH = costToH;
			boolean flag = true;
			for(int column = 0; column < arr.length && flag; column++) {
				for(int row = 0; row < arr.length; row++) {
					if(row == arr[column])
						continue;
					int[] copyArr = Arrays.copyOf(arr, arr.length);
					copyArr[column] = row;
					int currentH = Util.heuristicCost(copyArr);
					if(currentH < costToH) {
						replacement += (Math.abs(row - arr[column]));
						arr[column] = row;
						costToH = currentH;
						flag = false;
						break;
					}
				}
			}
			if(tempCostH == costToH) { 		
				arr = Util.generateRandomState();
				this.randomRestart++;
			}			
		}
		this.processTime = System.currentTimeMillis() - this.processTime;
		return costToH == 0 ? arr : null;
	}
	
	
	
	
}
