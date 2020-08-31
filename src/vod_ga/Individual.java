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
    private int n = GA.genSize;
    private static double bonus = 0;
    byte[] gen = new byte[n];
    private double fitness;
    {
    	countBonus();
    }
    public double countBonus() {
    	bonus = 0;
    	bonus += GA.setServerCost[0];
    	for (int p = 0; p< GA.numberOfProgram; ++p) {
    		bonus+= GA.assignCost[p][0];
    	}
    	return bonus;
    }
    public void show() {
    	for (int i = 0; i < n; ++i) {
    		System.out.print(gen[i]+ " ");
    	}
    	System.out.println();
    }
    public void init(Random rd){
        gen[0] = 1;
        for (int i = 1; i < n; ++i){
           gen[i] = (byte) (rd.nextInt(2));           
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
        byte[] temp = new byte[n];
        temp[0] = 1;
        for (int i = 0; i < GA.numberOfProgram ; ++i){
            Eval e = new Eval(this, i);
            e.run();
            tongThietHai += e.value;
        }
        for (int i = 1; i <n; ++i) if (gen[i] == 1){
//        	System.out.println(i);
            tongThietHai += GA.setServerCost[i];
        }
        //Update
        this.fitness = tongThietHai + countBonus();
    }
    public void hotSetFitness(){
        double tongThietHai = 0;
        byte[] temp = new byte[n];
        temp[0] = 1;
        for (int i = 0; i < GA.numberOfProgram ; ++i){
            Eval e = new Eval(this, i);
            e.hotRun();
            tongThietHai += e.value;
            for (int v : e.ARes) {
            	temp[v] = 1; 
            }
        }
        for (int i = 1; i <n; ++i) if (gen[i] == 1){
//        	System.out.println(i);
            tongThietHai += GA.setServerCost[i];
        }
        this.gen = temp;
        //Update
        this.fitness = tongThietHai + countBonus();
    }
    
    public double getFitness(){
        return this.fitness;
    }
}
