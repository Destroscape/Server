package game.net.packets;

import engine.util.Misc;
import game.Config;
import game.content.gambling.DiceHandler;
import game.entity.player.Player;
import game.item.ItemDegradation;
import game.skill.dungeoneering.Binding;
import game.skill.dungeoneering.Dungeoneering;
import game.skill.hunter.Implings;
import game.skill.runecrafting.Pouches;
import game.skill.summoning.Summoning;

/*
 * Project Insanity - Evolved v.3
 * ItemClick2.java
 */

public class ItemClick2 implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		final int itemId = c.getInStream().readSignedWordA();
		if (!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		
		if (itemId > DiceHandler.DICE_BAG && itemId <= 15100) {
			DiceHandler.selectDice(c, itemId);
		}

		if (Binding.isBindable(c, itemId)) {
			Binding.bindItem(c, itemId);
		}
		/*for (int i = 0; i < Pouches.pouchData.length; i++) {
			if (itemId == Pouches.pouchData[i][0]) {
				Pouches.checkPouch(c, itemId);
			}
		}*/

		switch (itemId) {
			
		/**
		 * Tool Belt Start
		 */
		case 6739:
		case 1359:
		case 1357:
		case 1361:
		case 1355:
		case 1353:
		case 1349:
		case 1351:
			/*if (c.hatchet == 0) {
				c.hatchet(c, itemId);
			} else if (c.hatchet > 0) {
				c.sendMessage("You already have a hatchet in your tool belt");
			}*/
			break;


		case 19748: // Ardougne Cloak
			if (c.effectRestore > 0) {
				Summoning.rechargeAtObelisk(c);
				c.effectRestore -= 1;
			} else {
				c.sendMessage("You have already done this twice today.");
				return;
			}
			break;

		case 9952:
			c.getDH().sendDialogues(5300, 708);
			break;
			/**
			 * Tool Belt End
			 */

		case 14001:
		case 14002:
		case 14003:
		case 14004:
		case 14005:
		case 14006:
		case 14007:
		case 14008:
		case 14009:
		case 14010:
		case 20769:
		case 20771:
			//c.getPA().showInterface(29000);
			c.getPA().showInterface(14000);
			break;

		case 22494:
			c.sendMessage("You have " + c.ppsLeft + " charges left!");
			break;

		default:
			if (Config.SERVER_DEBUG = true) {
				if (c.playerRights >= 4) {
					Misc.println(c.playerName + " - Item3rdOption: " + itemId);
				}
			}
			break;

		case 3842:
		case 3840:
		case 3844:
			c.getPA().handleGodBooks(itemId);
			break;

			/**
			 * Imp Looting
			 */

		case 11238:
		case 11240:
		case 11242:
		case 11244:
		case 11246:
		case 11248:
		case 11250:
		case 11252:
		case 11254:
		case 11256:
			Implings.lootJar(c, itemId);
			break;
			
		case 18355:
		case 18349:
		case 18351:;	
		case 18353:	
		case 18357:
		case 18359:
		case 18363:
		case 18361:
			ItemDegradation.chaoticCheckCharges(c, itemId);
			break;
			
		case 11694://ags
		case 11696://bgs
		case 11698://sgs
		case 11700://zgs
			if(c.getItems().freeSlots() < 1) {
				c.sendMessage("You need atleast 2 free slots to dismantle your godsword.");
				return;
			}
				if (c.getItems().playerHasItem(itemId, 1)) {
					c.getItems().deleteItem(itemId,1);
					c.getItems().addItem(itemId+8,1);
					c.getItems().addItem(11690,1);
			}
		break;

		case 21371:
			if(c.getItems().freeSlots() < 1) {
				c.sendMessage("You need atleast 2 free slots to dismantle your godsword.");
				return;
			}
				if (c.getItems().playerHasItem(itemId, 1)) {
					c.getItems().deleteItem(itemId,1);
					c.getItems().addItem(4151,1);
					c.getItems().addItem(21369,1);
			}
		break;

		case 15707:
			Dungeoneering.kinshipTele(c);
			break;
		}
	}
}
