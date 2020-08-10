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
		String fileName = "vod_20x10";
		Dictionary type = new Hashtable();
		type.put(1, "20");
		type.put(2, "50");
		type.put(3, "100");
		type.put(4, "150");
		type.put(5, "200");
		
//		int k = 1;
		for (int k = 2; k <= 2; ++k) {
			String i = (String) type.get(k);
	//		GA.filePath = "DuLieuNgon\\" + i + "\\" + fileName +".txt";
			File folder = new File("DuLieu\\DATA\\"+i);
			File[] listOfFiles = folder.listFiles();
			for (File ff : listOfFiles) {
				fileName = (ff.getName().replaceFirst("[.][^.]+$", ""));
				for (int it = 0; it < 30; ++it) {
					String fileOut = "Result\\Type"+k+"\\" + fileName + "\\" + fileName +"_seed_" + it+ ".txt";
					File f = new File("Result\\Type"+k+"\\" + fileName);
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
					GA.filePath = "DuLieuNgon\\" + i + "\\" + fileName + ".txt";
					GA ga = new GA();
					ga.run();
					fileOut = "Result\\Type"+k+"\\" + fileName + "\\" + fileName +"_seed_" + it+ ".opt";
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
				}
			}
			String st = "OK";
			JOptionPane.showMessageDialog(null, st);
		}
	}
}	
