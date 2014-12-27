package game.content.clues;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import game.content.clues.CluescrollDefinition;

import static java.lang.System.out;


public class Cluescrolls {
	
	static String PATH = "./Data/ClueScrolls.txt";
	public static String line = "";
	public static ArrayList<Integer> ItemIDS = new ArrayList<Integer>();

	
	public static void Clues() throws IOException {
		
		
		try {
		BufferedReader reader = new BufferedReader(new FileReader(PATH));
		line = reader.readLine();
		
		LoadClues(reader);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		
	}

	public static void LoadClues(BufferedReader r1) throws IOException {
		

		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[7];
		boolean EndOfFile = false;
		int Loaded = -1;
		
		while (EndOfFile == false && line != null) {
			Loaded++;
			line = line.trim();
			final int spot = line.indexOf("=");
					
			if (spot > -1)
			{
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("clue"))
				{
					CluescrollDefinition newCS = new CluescrollDefinition(
							Integer.parseInt(token3[0]), 
							Integer.parseInt(token3[1]), 
							Integer.parseInt(token3[2]),
							token3[3], token3[4], token3[5], token3[6]);
					
					CluescrollDefinition.cs[Integer.parseInt(token3[0])] = newCS;
					
					ItemIDS.add(Integer.parseInt(token3[0]));
					

					
				}
					
			} else {
				
				
				if (line.equals("[ENDOFCSLIST]"))
				{
					try
					{
						r1.close();
					}
					catch (final IOException ioexception)
					{
					}

				}
			}
			try
			{
				line = r1.readLine();
			}
			catch (final IOException ioexception1)
			{
				EndOfFile = true;
			}
		}
		try
		{
			r1.close();
		}
		catch (final IOException ioexception)
		{
		}
		out.println("[Stage - 4] Finished Loading "+Loaded+" Clue Scrolls.");
		r1.close();
	}
	
}