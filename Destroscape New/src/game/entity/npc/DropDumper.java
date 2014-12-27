package game.entity.npc;

import game.item.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DropDumper {
	
	public static void dump() {
		try {
			final FileWriter fstream = new FileWriter(
					"data/cfg/dropdump.cfg", true);
			final BufferedWriter out = new BufferedWriter(fstream);
			for(int[] n: NPCDrops.NPC_DROPS)
			{
				out.write("NPC name: "+NPCHandler.getNpcName(n[0])+" ");
				out.newLine();
				out.write("Drops item: "+Item.getItemName(n[1])+" ");
				out.newLine();
				out.write("Amount of item above: "+n[2]+" ");
				out.newLine();
				out.write("Drops this item: 1/"+n[3]+" kills");
				out.newLine();
				out.newLine();
			}
			out.close();
			System.out.println("Drops successfully dumped to data/cfg/dropdump.cfg");
		} catch (final IOException e) {
			System.out.println("Failed to write to file.");
		}
		
	/*for(int[] n: NPCDrops.NPC_DROPS)
	{
	    System.out.println("NPC name: "+NPCHandler.getNpcName(n[0]));
	    System.out.println("Drops item: "+Item.getItemName(n[1]));
	    System.out.println("Amount of item above: "+n[2]);
	    System.out.println("Drops this item: 1/"+n[3]+" kills");
	}*/
	}
}
