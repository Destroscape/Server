package game.minigame.barbarianassault;

import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class BarbarianAssault {

	private final int[][] WAVES = {{5219,5237},{5219,5237,5219,5237},{5219,5237,5219,5237,5219},{5219,5237,5219,5237,5219},{5219,5237,5219,5237,5219},{5237,5219,5237,5219,5247}};
	private int[][] coordinates = {{3161,9756},{3161,9760},{3163,9756},{3163,9760},{3161,9758}};
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
				NPCHandler.spawnNpc(c, npc, X, Y, H, 0, hp, max, atk, def, true, false);				
			}
			c.barbarianToKill = npcAmount;
			c.barbarianKilled = 0;
		}
	}

	public int getHp(int npc) {
		switch (npc) {
		case 5219:
			return 200;
		case 5237:
			return 300;
		case 5247:
			return 800;
		}
		return 100;
	}

	public int getMax(int npc) {
		switch (npc) {	
		case 5247:
			return 450;
		}
		return 350;
	}

	public int getAtk(int npc) {
		switch (npc) {	
		case 5247:
			return 750;
		}
		return 600;
	}

	public int getDef(int npc) {
		switch (npc) {		
		case 5247:
			return 350;
		}
		return 200;
	}
}