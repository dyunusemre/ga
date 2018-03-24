package com.ga;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.lang.ArrayUtils;

import com.hill.Util;

public class GeneticAlgorithm {
	public final static int FIT_VALUE = 27;
	public final static int SELECTION_FACTOR = 5;
	public final static double MUTATION_RATE = 80; //%60 mutation rate 
	public Population population;
	public int generationCount = 0;
	private long processTime;
	
	public GeneticAlgorithm(int popSize) {
		this.population = new Population(popSize);
	}
	public long getProcessTime() {
		return processTime;
	}
	public Chromosome[] selectionParent() {
		Chromosome[] parents = new Chromosome[2];
		Collections.sort(this.population.chromosomes, new Comparator<Chromosome>() {
			@Override
			public int compare(Chromosome c1, Chromosome c2) {
				int fitnessValue1 = c1.getFitness();
				int fitnessValue2 = c2.getFitness();
				
				if(fitnessValue1 < fitnessValue2)
					return 1;
				else if (fitnessValue1 > fitnessValue2)
					return -1;
				else 
					return 0;
			}	
		});
		for(int i = 0; i < parents.length; i++) {
			parents[i] = this.population.chromosomes.get((int)(this.population.getPopulationSize()*Math.pow(Math.random()
															   ,SELECTION_FACTOR))); 
		}
		
		return parents; 
	}
	public Chromosome[] crossOver(Chromosome[] parents) {
		
		Chromosome[] children = new Chromosome[2];		
		int crossoverPoint = 5;
		int[] tempArr = ArrayUtils.addAll(Arrays.copyOfRange(parents[0].getGenes(), 0, crossoverPoint),
									      Arrays.copyOfRange(parents[1].getGenes(), crossoverPoint, 8));
		children[0] = new Chromosome(tempArr);
		tempArr = ArrayUtils.addAll(Arrays.copyOfRange(parents[1].getGenes(), crossoverPoint, 8), 
									Arrays.copyOfRange(parents[0].getGenes(), 0, crossoverPoint));
		children[1] = new Chromosome(tempArr);
		
		return children;
	}
	public void generatePopulation() {
		Chromosome[] newGeneration = this.crossOver(this.selectionParent());
		Chromosome mutatedGeneration = new Chromosome(this.mutations(newGeneration[0].getGenes()));
		Chromosome mutatedGeneration2 = new Chromosome(this.mutations(newGeneration[1].getGenes()));			
		this.population.addPopulation(mutatedGeneration);
		this.population.addPopulation(mutatedGeneration2);
		this.generationCount++;
		
	}
	public int[] mutations(int[] genes) {
		double rand = (double)(Math.random() * 100);
		if(MUTATION_RATE > rand) {
			genes[(int)(Math.random()*7)] = (int)(Math.random()*7); // 0-7 for 8 queens 
		}
		return genes;
	}
	public void runGA() {
		System.out.println("Generation: " + this.generationCount + " Fittest: " + this.population.getFittest().getFitness());
		for (int i = 0; i < 20000; i++) {
			this.generatePopulation();	
			if(this.population.getFittest().getFitness() > FIT_VALUE) {
				System.out.println("Generation: " + this.generationCount + " Fittest: " + this.population.getFittest().getFitness());
				Util.printQueens(this.population.getFittest().getGenes());
				break;
			}
				
				
		}
	}
}
