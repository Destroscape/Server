package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.item.ItemDef;

public class ModelGrabber {

	public static void dumpItems(int totalAmount) {
		try {
			String fileName = "../Dump/items.txt";
			String txt = null;
			FileWriter fw = new FileWriter(fileName);
			for (int index = 0; index < totalAmount; index++) {
					ItemDef itemDef = ItemDef.forID(index);
					int model[] = { itemDef.modelID, itemDef.maleEquip1, itemDef.femaleEquip1 };
				try {
						for (int i = 0; i < model.length; i++)
							if (model[i] != -1 && model[i] != 0)
								fw.write("" + model[i] + "\r\n");
				} catch (NullPointerException ex) {
					ex.printStackTrace();
				}
			}
			fw.close();
			stripDuplicatesFromFile(fileName);
			System.out.println("Succesfully dumped "+totalAmount+ " item models.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void stripDuplicatesFromFile(String filename) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
	    String line;
	    while ((line = reader.readLine()) != null) {
	        lines.add(line);
	    }
	    reader.close();
	    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
	    for (String unique : lines) {
	        writer.write(unique);
	        writer.newLine();
	    }
	    writer.close();
	}

}
