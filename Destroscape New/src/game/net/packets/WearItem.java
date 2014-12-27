package game.net.packets;

import engine.util.Misc;
import game.content.gambling.DiceHandler;
import game.entity.npc.NPCHandler;
import game.entity.player.Player;
import game.skill.runecrafting.Pouches;

public class WearItem implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		c.wearId = c.getInStream().readUnsignedWord();
		c.wearSlot = c.getInStream().readUnsignedWordA();
		c.interfaceId = c.getInStream().readUnsignedWordA();
		boolean torvaChanged = false;
		if(!c.getItems().playerHasItem(c.wearId, 1, c.wearSlot)) {
			return;
		}
		if (c.wearSlot == 0 || c.wearSlot == 4 || c.wearSlot == 7) {
			if (c.playerEquipment[c.wearSlot] == 20135 || c.playerEquipment[c.wearSlot] == 20139 || c.playerEquipment[c.wearSlot] == 20143 || c.playerEquipment[c.wearSlot] == 20159 || c.playerEquipment[c.wearSlot] == 20163 || c.playerEquipment[c.wearSlot] == 20167 || c.playerEquipment[c.wearSlot] == 20147 || c.playerEquipment[c.wearSlot] == 20151 || c.playerEquipment[c.wearSlot] == 20155)
				torvaChanged = true;
		}
		c.getItems().wearItem(c.wearId, c.wearSlot);
		if (torvaChanged && c.playerLevel[3] > c.calculateMaxLifePoints()) {
			c.playerLevel[3] = c.calculateMaxLifePoints();
			c.getPA().refreshSkill(3);
		}
		c.getPA().resetAutocast();
		/*for (int i = 0; i < Pouches.pouchData.length; i++) {
			if (c.wearId == Pouches.pouchData[i][0]) {
				Pouches.emptyPouch(c, c.wearId);
			}
		}*/
		if(!Pouches.pouchSize(c.wearId).equals("")){
			Pouches.removeFromPouch(c, c.wearId);
			return;
		}
		if (c.playerIndex > 0 || c.npcIndex > 0) {
			c.getCombat().resetPlayerAttack();
		}
		if (c.wearId > DiceHandler.DICE_BAG && c.wearId <= 15100) {
			DiceHandler.rollDice(c, c.wearId, true);
			return;
		}
		if (c.wearId == 4155) {
			if (c.slayerTask == 0) {
				c.sendMessage("You currently don't have a slayer task");
			} else {
				c.sendMessage("My task is: <col=255>" + c.taskAmount + " " + NPCHandler.getNpcListName(c.slayerTask) + ".");
			}
			return;
		}
		
		if(c.wearId == 6583) {
			c.setSidebarInterface(1, 6014);
			c.setSidebarInterface(2, 6014);
			c.setSidebarInterface(3, 6014);
			c.setSidebarInterface(4, 6014);
			//c.setSidebarInterface(5, 6014);
			c.setSidebarInterface(6, 6014);
			c.setSidebarInterface(7, 6014);
			c.setSidebarInterface(8, 6014);
			c.setSidebarInterface(9, 6014); 
			c.setSidebarInterface(10, 6014);
			c.setSidebarInterface(11, 6014);
			c.setSidebarInterface(12, 6014);
			c.setSidebarInterface(13, 6014);
			c.setSidebarInterface(0, 6014);
			c.usedRingOfStone = true;
			c.sendMessage("You've turned yourself into stone!");
			c.npcId2 = 7817;
			c.isNpc = true;
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
		}

		if(c.wearId == 7927) {
			c.setSidebarInterface(1, 6014);
			c.setSidebarInterface(2, 6014);
			c.setSidebarInterface(3, 6014);
			c.setSidebarInterface(4, 6014);
			//c.setSidebarInterface(5, 6014);
			c.setSidebarInterface(6, 6014);
			c.setSidebarInterface(7, 6014);
			c.setSidebarInterface(8, 6014);
			c.setSidebarInterface(9, 6014); 
			c.setSidebarInterface(10, 6014);
			c.setSidebarInterface(11, 6014);
			c.setSidebarInterface(12, 6014);
			c.setSidebarInterface(13, 6014);
			c.setSidebarInterface(0, 6014);
			c.usedEasterRing = true;
			c.sendMessage("As you put on the ring you turn into an egg!");
			c.npcId2 = 3689 + Misc.random(5);
			c.isNpc = true;
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
		}
		if (c.wearId >= 5509 && c.wearId <= 5515) {
			int pouch = -1;
			final int a = c.wearId;
			if (a == 5509) {
				pouch = 0;
			}
			if (a == 5510) {
				pouch = 1;
			}
			if (a == 5512) {
				pouch = 2;
			}
			if (a == 5514) {
				pouch = 3;
			}
			c.getPA().emptyPouch(pouch);
			return;
		}
		// c.attackTimer = oldCombatTimer;
		if (c.tradeStatus > 0) {
			c.getTradeAndDuel().declineTrade();
		}
		if (c.duelStatus > 0 && !c.inDuelArena()) {
			c.getTradeAndDuel().declineDuel();
		}
		c.getItems().wearItem(c.wearId, c.wearSlot);
	}

}
