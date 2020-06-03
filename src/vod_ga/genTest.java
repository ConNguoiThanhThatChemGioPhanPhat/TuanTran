/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vod_ga;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class genTest {

    int n, p;
    double w;

    
    FileOutputStream fos;
    DataOutputStream out;
    {
        try {
            fos = new FileOutputStream("");
            out = new DataOutputStream(fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(genTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void genTree() {
        Random rd = new Random();
        for (int i = 0; i < n; ++i) {
            out.wr
        }

    }
}
