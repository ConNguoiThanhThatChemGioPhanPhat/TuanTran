/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vod_ga;

import java.util.LinkedList;

/**
 *
 * @author Admin
 */
public class Eval {
    private Individual ind;
    private int program;
    private int n = GA.numberOfNodes;
    Set<Integer> ARes = new Set<Integer>();
    double value;
    double bonus = 0;
    
    Set<Integer> AStar[] = new Set[n];
    Set<Integer> BStar[] = new Set[n];
    double G[] = new double[n];
    double GN[] = new double[n];
    public Eval(Individual ind, int program) {
        this.ind = ind;
        this.program = program;
    }
    public void run(){
        AStar[0] = new Set<>();
        BStar[0] = new Set<>();
        for (int i = n-1; i >0; --i){
            AStar[i] = new Set<>();
            BStar[i] = new Set<>();
            double A, B, C;
            A = (ind.gen[i] == 1 ? GA.assignCost[program][i] : Double.POSITIVE_INFINITY) + G[i];
            B = (GA.request[program][i] ? Double.POSITIVE_INFINITY : 0) + GN[i];
            C = GA.bandwidthCost[program][i] + G[i];
            
            //A and B are both finite and infinit
            if (A <= B){
                for (int c : GA.child[i]){
                    BStar[i].addSet(AStar[c]);
                }
                BStar[i].add(i);
                GN[i] = A;
            } else {
                for (int c : GA.child[i]){
                    BStar[i].addSet(BStar[c]);
                }
                GN[i] = B;
            }
            GN[GA.parrent[i]] += GN[i];
            if (GN[i] <= C){
                AStar[i] = BStar[i];
                G[i] = GN[i];
            } else {
                for (int c : GA.child[i]){
                    AStar[i].addSet(AStar[c]);
                }
                G[i] = C;
            }
            G[GA.parrent[i]] += G[i];
            
//            System.out.println("Node "+ i+" : ");
//            AStar[i].show();
//            BStar[i].show();

        }
        for (int i: GA.child[0]) {
        	ARes.addSet(AStar[i]);
        }
        
        // debug
//        String sv = "";
//        for (int i = 1; i < GA.numberOfNodes; ++i) sv=sv + this.ind.gen[i]+  "  ";
//        System.out.println(sv);
//        ARes.show();
        this.value = G[0];
        
    }
}
