package com.ga;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.lang.ArrayUtils;

import com.hill.Util;

public class GeneticAlgorithm {
	public Population population;
	public int selectionFactor;
	public int mutationRate = 1; //%60 mutation rate 
	public int generationCount = 0;
	public GeneticAlgorithm(int popSize) {
		this.population = new Population(popSize);
		this.selectionFactor = 5; // decreasing the value will increase the chance of selecting more survival parent
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
		/**	selection 2 parent PopulationSize*x^5
			http://prntscr.com/ivb65w as you can see the propability of more fitness parent to 
			chose has higher chance in a sorted array 
		 	according to fitness factor
		 **/
		for(int i = 0; i < parents.length; i++) {
			parents[i] = this.population.chromosomes.get((int)(50*Math.pow(Math.random(), selectionFactor))); 
		}
		return parents; 
	}
	public Chromosome[] crossOver(Chromosome[] parents) {
		
		Chromosome[] children = new Chromosome[2];
		children[0] = new Chromosome();
		children[1] = new Chromosome();
		int crossoverPoint = (int)(Math.random() * 8);
		int[] firstPositionsfromFirstParent = Arrays.copyOfRange(parents[0].getGenes(), 0, crossoverPoint);
		int[] secondPositionsfromFirstParent = Arrays.copyOfRange(parents[0].getGenes(), crossoverPoint, 8);
		int[] tempArr = ArrayUtils.addAll(firstPositionsfromFirstParent, secondPositionsfromFirstParent);
		children[0].setGenes(tempArr);
		children[0].calculateFitness();
		int[] firstPositionsfromSecondParent = Arrays.copyOfRange(parents[1].getGenes(), crossoverPoint, 8);
		int[] secondPositionsfromSecondParent = Arrays.copyOfRange(parents[0].getGenes(), 0, crossoverPoint);
		tempArr = ArrayUtils.addAll(firstPositionsfromSecondParent, secondPositionsfromSecondParent);
		children[1].setGenes(tempArr);
		children[1].calculateFitness();
		this.population.addPopulation(children[0]);
		this.population.addPopulation(children[1]);
		return children;
	}
	public void generatePopulation() {
		Chromosome[] selectedParent = this.selectionParent();
		Chromosome[] newGeneration = this.crossOver(selectedParent);
		Chromosome mutatedGeneration = new Chromosome(this.mutations(newGeneration[0].getGenes()));
		Chromosome mutatedGeneration2 = new Chromosome(this.mutations(newGeneration[1].getGenes()));	
	}
	public int[] mutations(int[] genes) {
		double rand = Math.random() * 100;
		if(this.mutationRate > rand) {
			genes[(int)(Math.random()*7)] = (int)(Math.random()*7); // 0-7 for 8 queens 
		}
		return genes;
	}
	public void stopEvaulate() {
		
	}
	public void runGA() {
		System.out.println("Generation: " + this.generationCount + " Fittest: " + this.population.getFittest().getFitness());
		for (int i = 0; i < 10000; i++) {
			this.generatePopulation();
			if(this.population.getFittest().getFitness() > 27) {
				System.out.println("FOUND");
				break;
			}
		}
        

	}
}
