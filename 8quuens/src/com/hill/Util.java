package com.hill;

public class Util {
	public static int[] generateRandomState() {
		int[] arr = new int[8];
		for (int i = 0; i < arr.length; i++)
			arr[i] = (int) (Math.random() * arr.length);
		return arr;
	}
	public static int heuristicCost(int[] arr) {
		int h = 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] == arr[j] || Math.abs(arr[i] - arr[j]) == j - i) //h attacks of queens each other
                    h += 1;
        return h;
	}
	public static void printQueens(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				if( j == arr[i]) {
				System.out.print("Q ");
				}else {
					System.out.print(". ");
				}	
			}
			System.out.println();
		}
		
	}
}
