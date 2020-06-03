/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vod_ga;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *  Node 0 is usually set server and program
 * @author Admin
 */
public class Individual implements Comparable<Individual>{
    private static int n = GA.genSize;
    private static Random rd = new Random();
    private static double p1 = GA.anpha/(GA.anpha + 1)*GA.crossoverRate;
   
    byte[] gen = new byte[n];
    private double fitness;
    public LinkedList<Integer> serverSet[];
    
    public void init(){
        rd.nextBytes(gen);
        gen[0] = 1;
        for (int i = 1; i < n; ++i){
           gen[i] = (byte) (gen[i] <= GA.split ? 0 : 1);           
        }
        
        this.setFitness();
    }
    @Override
    public int compareTo(Individual o) {
        double res = this.fitness - o.fitness;
        if (res > 0) return 1;
        if (res < 0) return -1;
        return 0;
    }
    
    //
    public void setFitness(){
        double tongThietHai = 0;
        for (int i = 1; i <n; ++i) if (gen[i] == 1){
            tongThietHai += GA.setServerCost[i];
        }
        for (int i = 0; i < GA.numberOfProgram ; ++i){
            Eval e = new Eval(this, i);
            e.run();
            tongThietHai += e.value;
        }
        this.fitness = tongThietHai;
    }
    
    public double getFitness(){
        return this.fitness;
    }
    
    public Individual mate(Individual ind){
        Individual child = new Individual();
        byte[] temp = new byte[n];
        Individual dad, mom;
        if (this.compareTo(ind) >= 0){
            dad = this;
            mom = ind;
        }  else {
            dad = ind;
            mom = this;
        }
        for(int i = 1; i < n; ++i){
            double p = rd.nextDouble();
            if (p < p1){
                child.gen[i] = dad.gen[i];
            } else if (p < GA.crossoverRate){
                child.gen[i] = mom.gen[i];
            } else {
                child.gen[i] = (byte)rd.nextInt(2);
            } 
        }
        child.setFitness();
        return child;
    }
}