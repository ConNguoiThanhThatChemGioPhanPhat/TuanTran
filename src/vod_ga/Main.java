package vod_ga;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.xml.internal.ws.policy.spi.PolicyAssertionValidator.Fitness;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary type = new Hashtable();
		type.put(1, "Type1");
		type.put(2, "Type2");
		type.put(3, "Type1_Large");
		type.put(4, "Type2_Large");
		
//		int k = 1;
		for (int k = 1; k <= 4; ++k) {
			String i = (String) type.get(k);
			File folder = new File("ModData\\"+i);
			File[] listOfFiles = folder.listFiles();
			for (File ff : listOfFiles) {
				String fileName = (ff.getName().replaceFirst("[.][^.]+$", ""));
				for (int it = 0; it < 30; ++it) {
					String fileOut = "NewResult\\"+ i+ "\\" + fileName + "\\" + fileName +"_seed_" + it+ ".txt";
					File f = new File("NewResult\\"+i+"\\" + fileName);
					f.mkdirs();
					PrintStream fo;
					try {
						fo = new PrintStream(fileOut);
						System.setOut(fo);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Generations "+ fileName);
				// Run GA here
					GA.filePath = "ModData\\"+i+"\\" + fileName + ".txt";
					GA ga = new GA();
					ga.run();
					fileOut = "NewResult\\"+ i+ "\\" + fileName + "\\" + fileName +"_seed_" + it+ ".opt";
					try {
						fo = new PrintStream(fileOut);
						System.setOut(fo);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Filename: " + fileName);
					System.out.println("Seed: " + it);
					System.out.println("Fitness: "+ GA.fitness);
					System.out.println("Time: " + GA.runtime);

					System.err.println("Type " + k + " : " +  fileName+ " " + it);
					
					
//					Modify data
//					String src = "NewData\\"+i+"\\" + fileName + ".txt";
//					String fileOut = "ModData\\"+i+"\\" + fileName + ".txt";
//					Modify md = new Modify(src, fileOut, new GA());
				}
			}
		}
		String st = "Vod OK";
		JOptionPane.showMessageDialog(null, st);
	}
}	
