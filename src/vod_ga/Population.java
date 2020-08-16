/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vod_ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 *
 * @author Adminzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
 */
public class Population {
    ArrayList<Individual> pop = new ArrayList<>(GA.popSize);
    int n = GA.popSize;
    Random rd = new Random(GA.SEED);
    int genSize = GA.genSize;
    public void init(){
        for (int i = 0; i< n; ++i){
            Individual temp = new Individual();
            temp.init(rd);
            temp.setFitness();
            pop.add(temp);
        }
    }
    public void show() {
        Individual best = pop.get(0);
        byte[] temp = new byte[n];
        temp[0] = 1;
        for (int i = 0; i < GA.numberOfProgram ; ++i){
            Eval e = new Eval(best, i);
            e.run();
            String res = "Program " + (i + 1) + " :";
            Collections.sort(e.ARes);
            for (int v : e.ARes) {
            	temp[v] = 1; 
            	res = res + " " + v;
            }
            System.out.println(res);
        }
        System.out.println("Best : " + pop.get(0).getFitness());
    }
    public ArrayList<Individual>  crossover(Individual id1, Individual id2){
        Individual  child1 = new Individual();
        Individual child2 = new Individual();
        int p1 = rd.nextInt(genSize);
        int p2 = rd.nextInt(genSize - p1);
        for(int i = 1; i < genSize; ++i){
        	if (i < p1 || i >= p2) {
        		child1.gen[i] = id1.gen[i];
        		child2.gen[i] = id2.gen[i];
        	}
        	else {
        		child1.gen[i] = id2.gen[i];
        		child2.gen[i] = id1.gen[1];
        	}
        }
        child1.gen[0] = 1;
        child2.gen[0] = 1;
        child1.setFitness();
        child2.setFitness();
        ArrayList<Individual> off_spring = new ArrayList<Individual>();
        off_spring.add(child1);
        off_spring.add(child2);
  		return off_spring;
    }
    public Individual  mutation(Individual id) {
       Individual  child = new Individual();
//     Copy gen
       for (int i=0; i<genSize; i++) {   
           child.gen[i] = id.gen[i];
       }
       // Dot bien 4 gen trong gen bo
       for (int i= 0; i< 4; ++i) {
    	   int p = rd.nextInt(genSize);
    	   child.gen[i] =(byte) (1 - child.gen[i]);
       }
       child.setFitness();
       return child;
    }
    public void run(){       
        for (int generation = 0; generation < GA.converge; ++ generation){
            ArrayList<Individual> temp = new ArrayList<>(pop);
            int k = n<<1;
            while (temp.size() < k) {
            	int id1 = 0; 
            	int id2 = 0;
            	while(id1 == id2) {
            		id1 = rd.nextInt(n);
            		id2 = rd.nextInt(n);
            	}
            	double r = rd.nextDouble();
            	if (r < GA.crossoverRate) {
            		Individual par1 = pop.get(id1);         
            		Individual par2 = pop.get(id2);
            		temp.addAll(crossover(par1, par2));
            	}
            	if (r < GA.mutationRate) {
            		Individual child = mutation(pop.get(id1));
            		temp.add(child);
            	}
            }
            Collections.sort(temp);
            pop = new ArrayList<Individual>(temp.subList(0, n));
            System.out.println(generation+ " "+  pop.get(0).getFitness());
        }
    }
}
