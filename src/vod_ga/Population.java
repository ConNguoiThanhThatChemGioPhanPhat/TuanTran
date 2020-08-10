/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vod_ga;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Adminzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
 */
public class Population {
    ArrayList<Individual> pop = new ArrayList<>(GA.popSize);
    int n = GA.popSize;
    public void init(){
        for (int i = 0; i< n; ++i){
            Individual temp = new Individual();
            temp.init();
            temp.setFitness();
            pop.add(temp);
        }
    }
    public void run(){       
        for (int generation = 0; generation < GA.converge; ++ generation){
            ArrayList<Individual> temp = new ArrayList<>(pop);
            for (int i = 0; i < n; ++i){
                Individual kap = pop.get(GA.rd.nextInt(n));
                temp.add(pop.get(i).mate(kap));
            }
            Collections.sort(temp);
            pop = new ArrayList<> (temp.subList(0, n));
            System.out.println(generation+ " "+  pop.get(0).getFitness());
        }
        
        //////////////////////////////////
        //////
        ////// Print cau hinh
        //////
        //////////////////////////////////
//        Individual best = pop.get(0);
//        byte[] temp = new byte[n];
//        temp[0] = 1;
//        for (int i = 0; i < GA.numberOfProgram ; ++i){
//            Eval e = new Eval(best, i);
//            e.run();
//            String res = "Program " + (i + 1) + " :";
//            Collections.sort(e.ARes);
//            for (int v : e.ARes) {
//            	temp[v] = 1; 
//            	res = res + " " + v;
//            }
//            System.out.println(res);
//        }
//        System.out.println("Best : " + pop.get(0).getFitness());
        
        /////////////////////////
        /////    End of print
    }
}
