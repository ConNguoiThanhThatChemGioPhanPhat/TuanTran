/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class test {
    public static void main(String[] args) {
//        wow a = new wow();
//        Thread t1 = new Thread(new wow());
//        Thread t2 = new Thread (new wow());
//        t1.start();
//        t2.start();
//          System.out.println(Math.min(1, - 1.0/0.0));
        Random rd = new Random();
        byte[] tmp = new byte[100];
        rd.nextBytes(tmp);
        for (byte e: tmp){
            System.out.println(e);
        }
    }
}
