package com.ga;

import java.util.ArrayList;	
import java.util.List;

public class Population {
	private int populationSize;
	public List<Chromosome> chromosomes = new ArrayList<>();
	
	public Population(int size) {
		populationSize = size;
		initPop();
	
	}
	public int getPopulationSize() {
		return populationSize;
	}
	public void initPop() {
		for(int i = 0; i < this.populationSize; i++) {
			this.chromosomes.add(i, new Chromosome());
		}
	}
	public Chromosome getFittest(){
		int maxFit = Integer.MIN_VALUE;
		int index = 0;
		for(int i = 0; i < this.chromosomes.size(); i++) {
			if(maxFit <= this.chromosomes.get(i).getFitness()) {
				maxFit = this.chromosomes.get(i).getFitness();
				index = i;
			}
		}
		return this.chromosomes.get(index);
	}
	public void addPopulation(Chromosome c) {
		this.chromosomes.add(c);
		this.populationSize = this.chromosomes.size();

	}
	
	
}
