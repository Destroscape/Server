package game.minigame.rfd;

import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class RFD {

	private final int[][] WAVES = {{3493},{3494},{3495},{3496},{3491}};

	private int[][] coordinates = {{1900,5354,2},{1900,5354,2},{1900,5354,2},{1900,5354,2},{1900,5354,2}};

	public void spawnNextWave(Player c) {
		if (c != null) {
			if (c.waveId >= WAVES.length) {
				c.waveId = 0;
				return;				
			}
			if (c.waveId < 0){
				return;
			}
			int npcAmount = WAVES[c.waveId].length;
			for (int j = 0; j < npcAmount; j++) {
				int npc = WAVES[c.waveId][j];
				int X = coordinates[j][0];
				int Y = coordinates[j][1];
				int H = c.heightLevel;
				int hp = getHp(npc);
				int max = getMax(npc);
				int atk = getAtk(npc);
				int def = getDef(npc);
				NPCHandler.spawnNpc(c, npc, X, Y, H, 0, hp, max, atk, def, true, true);				
			}
			c.RFDToKill = npcAmount;
			c.RFDKilled = 0;
		}
	}

	public int getHp(int npc) {
		switch (npc) {
		case 3493:
			return 254;
		case 3494:
			return 254;
		case 3495:
			return 254;
		case 3496:
			return 254;
		case 3491: 
			return 254;		
		}
		return 100;
	}

	public int getMax(int npc) {
		switch (npc) {
		case 3493:
			return 20;
		case 3494:
			return 25;
		case 3495:
			return 15;
		case 3496:
			return 25;
		case 3491:
			return 30;		
		}
		return 5;
	}

	public int getAtk(int npc) {
		switch (npc) {
		case 3493:
			return 225;
		case 3494:
			return 250;
		case 3495:
			return 300;
		case 3496:
			return 329;
		case 3491: 
			return 400;		
		}
		return 100;
	}

	public int getDef(int npc) {
		switch (npc) {
		case 3493:
			return 300;
		case 3494:
			return 350;
		case 3495:
			return 400;
		case 3496:
			return 520;
		case 3491:
			return 600;		
		}
		return 100;
	}

	public static String getGloves(Player c) {
		if(c.Agrith == true && c.Flambeed == false && c.Karamel == false && c.Dessourt == false && c.Culin == false) {
			return ("Bronze Gloves");
		} else if(c.Agrith == true && c.Flambeed == true && c.Karamel == false && c.Dessourt == false && c.Culin == false) {
			return ("Steel Gloves");
		} else if(c.Agrith == true && c.Flambeed == true && c.Karamel == true && c.Dessourt == false && c.Culin == false) {
			return ("Mithril Gloves");
		} else if(c.Agrith == true && c.Flambeed == true && c.Karamel == true && c.Dessourt == true && c.Culin == false) {
			return ("Rune Gloves");
		} else if(c.Agrith == true && c.Flambeed == true && c.Karamel == true && c.Dessourt == true && c.Culin == true) {
			return ("Barrows Gloves");
		}
		return ("Nothing");
	}
}