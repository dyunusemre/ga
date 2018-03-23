package com.ga;

import com.hill.Util;

public class Chromosome {
	private int fitness;
	private int[] genes;
	
	public Chromosome() {
		this.genes = Util.generateRandomState();
		this.calculateFitness();
	}
	public Chromosome(int[] genes) {
		this.genes = genes;
		this.calculateFitness();
	}
	public int calculateFitness() {
		this.fitness = 28 - Util.heuristicCost(this.genes); // total attacks 28 - attacks of queens each others
		return fitness; 
	}
	public int getFitness() {
		return fitness;
	}
	public int[] getGenes() {
		return genes;
	}
	public void setGenes(int[] genes) {
		this.genes = genes;
	}
}
