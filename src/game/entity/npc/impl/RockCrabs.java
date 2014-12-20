package game.entity.npc.impl;

import game.entity.npc.NPC;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;

public class RockCrabs {
	
	public final static void transform(final Player c) {
		for (NPC n : NPCHandler.npcs) {
			if (n != null && (n.npcType == 1266 || n.npcType == 1268) && NPCHandler.goodDistance(n.getX(), n.getY(), c.absX, c.absY, 1)) {
				n.requestTransform(1265);
				n.npcType = 1265;
				n.underAttack = true;
				n.walkingType = 1;
				n.facePlayer(c.playerId);
				n.killerId = c.playerId;
				n.untransformTimer = 100;
				return;
			}
		}
	}
	
}
