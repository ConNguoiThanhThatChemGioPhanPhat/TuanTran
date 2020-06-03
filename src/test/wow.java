/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Admin
 */
public class wow implements Runnable{
    static int i = 0;
    public static Integer j = new Integer(1);
    public void hi(){
        synchronized(j){System.out.println(i);
        ++i;
    } 
    }
    @Override
    public void run() {
        for (int j = 0; j < 100000; ++j){
            hi();
        }
    }
}
