package game.net.packets;

import engine.util.Misc;
import engine.event.Event;
import engine.event.EventContainer;
import engine.event.EventManager;
import game.Config;
import game.content.AncientEffigies;
import game.content.DonatorKits;
import game.content.News;
import game.content.TryYourLuckEASY;
import game.content.TryYourLuckMEDIUM;
import game.content.TryYourLuckELITE;
import game.content.RewardBook;
import game.content.gambling.DiceHandler;
import game.content.travel.TeleTabs;
import game.content.MithrilFlower;
import game.content.clues.CluescrollRewards;
import game.entity.player.Player;
import game.skill.farming.Farming;
import game.skill.herblore.Herblore;
import game.skill.prayer.Bury;
import game.skill.prayer.Constants;
import game.skill.hunter.TrapHunting;
import game.skill.runecrafting.Pouches;
import game.skill.woodcutting.Woodcutting;

public class ClickItem implements PacketType {

	@Override
	public void processPacket(final Player c, final int packetType,
			final int packetSize) {
		c.getInStream().readSignedWordBigEndianA();
		final int itemSlot = c.getInStream().readUnsignedWordA();
		final int itemId = c.getInStream().readUnsignedWordBigEndian();
		c.trainingPrayer = false;
		if (c.isDead) {
			return;
		}
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
		}
		if(itemId == DiceHandler.DICE_BAG) {
			DiceHandler.selectDice(c, itemId);
		}
		if (itemId > DiceHandler.DICE_BAG && itemId <= 15100) {
			DiceHandler.rollDice(c, itemId, false);
		}
		if(CluescrollRewards.isClue(itemId)) {
			CluescrollRewards.ShowHint(itemId, c);
		}
		if (itemId >= 10006 && itemId <= 10008)
			TrapHunting.layTrap(c, itemId);
		if (itemId >= 5070 && itemId <= 5074)
			Woodcutting.handleNest(c, itemId);
		if (itemId >= 18778 && itemId <= 18781)
			AncientEffigies.investigateEffigy(c);
		Farming.openSeedBox(c, itemId);
		TeleTabs.useTeleportingTab(c, itemId);
		TeleTabs.useBoneTab(c, itemId);
		/*for (int i = 0; i < Pouches.pouchData.length; i++) {
			if (itemId == Pouches.pouchData[i][0]) {
				Pouches.fillPouch(c, itemId);
			}
		}*/
		if(!Pouches.pouchSize(itemId).equals("")){
			Pouches.addToPouch(c, itemId);
			return;
		}
		if (Herblore.isHerb(c, itemId)) {
			Herblore.cleanTheHerb(c, itemId);
		}
		if (Constants.playerBones(c, itemId)) {
			Bury.buryBones(c, itemId, itemSlot);
		}
		/*if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			final int a = itemId;
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
			c.getPA().fillPouch(pouch);
			return;
		}*/
		if (itemId == 2528) {
			c.getPA().antiqueLamp();
		}
		if (itemId == 11949) {
			c.makesnow();
			c.stopMovement();
		}
		if (itemId == 10025) {
			if (c.getItems().playerHasItem(10025, 1)) {
				c.getItems().deleteItem(10025, 1);
				c.getItems().addItem(12158, Misc.random(100));
				c.getItems().addItem(12159, Misc.random(100));
				c.getItems().addItem(12163, Misc.random(100));
				c.getItems().addItem(12160, Misc.random(100));
			} else {
				c.sendMessage("You need a Charm Box to do this.");
			}
		}
		if (itemId == 6099) {
			if (!c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
				c.sendMessage("You're only able to use this crystal above level 20 wilderness.");
				return;
			}
			c.getItems().deleteItem(6099, 1);
			c.getItems().addItem(6100, 1);
			c.usingTeleCrystal = true;
			c.getPA().spellTeleport(Config.HOME_X, Config.HOME_Y, 0);
		} else if (itemId == 6100) {
			if (!c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
				c.sendMessage("You're only able to use this crystal above level 20 wilderness.");
				return;
			} 
			c.getItems().deleteItem(6100, 1);
			c.getItems().addItem(6101, 1);
			c.usingTeleCrystal = true;
			c.getPA().spellTeleport(Config.HOME_X, Config.HOME_Y, 0);
		} else if (itemId == 6101) {
			if (!c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
				c.sendMessage("You're only able to use this crystal above level 20 wilderness.");
				return;
			}
			c.getItems().deleteItem(6101, 1);
			c.getItems().addItem(6102, 1);
			c.usingTeleCrystal = true;
			c.getPA().spellTeleport(Config.HOME_X, Config.HOME_Y, 0);
		} else if (itemId == 6102) {
			if (!c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
				c.sendMessage("You're only able to use this crystal above level 20 wilderness.");
				return;
			}
			c.getItems().deleteItem(6102, 1);
			c.usingTeleCrystal = true;
			c.getPA().spellTeleport(Config.HOME_X, Config.HOME_Y, 0);
		}
		if (itemId == 6199) {
			c.getItems().deleteItem(6199, 1);
			int[] items ={ 22207, 22209, 22211, 22213, 15441, 15442, 15443, 15444, 21258, 21260, 21261, 21263 };
			c.getItems().addItem(items[Misc.random(items.length - 1)], 1);
		}
		if (itemId == 9477) {
			if (!c.inWild() || !c.inMinigame()) {
				c.getPA().openUpBank(0);
				c.getItems().deleteItem(9477, 1);
			} else {
				c.sendMessage("You can't use this in the area you're in.");
			}
		}
		if (c.getQuest().questItem(itemId)) {
			c.getQuest().handleItem(c, itemId);
		}
		if (c.getFood().isFood(itemId)) {
			c.getFood().eat(itemId, itemSlot);
		}
		if (c.getPotions().isPotion(itemId)) {
			c.getPotions().handlePotion(itemId, itemSlot);
		}
	if (itemId == 1961) {
		if (c.inWild()) {
			c.sendMessage("<col=255>These seem to not like the idea of being eaten in the wilderness.");
			return;
		}
	}
		switch (itemId) {
		case 20667:
			c.getPA().vecnaSkull();
			break;

		case MithrilFlower.FLOWER_ID:
			MithrilFlower.plantFlower(c);
			break;

		case RewardBook.BOOK:
			RewardBook.searchChest(c);
			break;

		case 6:
			c.getCannon().setUpCannon();
			break;

		case 18782:
			c.getPA().showInterface(52000);
			break;

		case DonatorKits.KIT_ID:
			DonatorKits.openKit(c, itemId);
			break;

		case TryYourLuckEASY.BOX:
			TryYourLuckEASY.searchChest(c);
			break;
		case TryYourLuckMEDIUM.BOX:
			TryYourLuckMEDIUM.searchChest(c);
			break;
		case TryYourLuckELITE.BOX:
			TryYourLuckELITE.searchChest(c);
			break;
		case 15707:
			if (!c.inLobby()) {
				c.sendMessage("You must be in the dungeoneering lobby to do this.");
				return;
			}
			if (c.floor == 0) {
				c.setSidebarInterface(13, 27124);
			}
				c.sendMessage("* <col=255>Open your last tab (bottom right corner)");
				c.sendMessage("* <col=255>To open the dungeoneering configure interface!");
			//c.getPA().sendFrame106(13);
			break;

		case News.NEWSPAPER:
			News.displayNews(c);
			break;

		case 15674:
			if (c.getItems().playerHasItem(15674, 1)) {
				c.getItems().deleteItem(15674, 1);
				c.getItems().addItemToBank(23243, 1000);
				c.getItems().addItemToBank(23255, 1000);
				c.getItems().addItemToBank(23279, 1000);
				c.getItems().addItemToBank(23291, 1000);
				c.getItems().addItemToBank(23303, 1000);
				c.getItems().addItemToBank(23327, 1000);
				c.getItems().addItemToBank(23351, 1000);
				c.getItems().addItemToBank(23399, 1000);
				c.getItems().addItemToBank(23423, 1000);
				c.getItems().addItemToBank(23483, 1000);
				c.getItems().addItemToBank(23495, 1000);
				c.getItems().addItemToBank(23501, 1000);
				c.getItems().addItemToBank(23507, 1000);
				c.getItems().addItemToBank(23513, 1000);
				c.getItems().addItemToBank(23519, 1000);
				c.getItems().addItemToBank(23525, 1000);
				c.getItems().addItemToBank(23531, 1000);
				c.getItems().addItemToBank(23609, 1000);
				c.sendMessage("1000 of each potion flasks have been distributed to your bank.");
			} else {
				c.sendMessage("You need to have a "+ c.getItems().getItemName(itemId) +" in your inventory.");
			}
			break;

		case 18336:
			c.getDH().sendStatement("Use this ancient scroll with many rings, to make them more powerful.");
			break;

		/*case 5070:
		case 5071:
		case 5072:
		case 5073:
		case 5074:
			//c.getItems().deleteItem(5073, 1);
			//c.getItems().addItem(randomSeeds(), Misc.random(5));
			Woodcutting.handleNest(c);
			break;*/

		case 15262:
			if (c.getItems().playerHasItem(15262, 1)) {
				c.getItems().deleteItem2(15262, 1);
				c.getItems().addItem(12183, 25000);
				return;
			}
			break;

		case 9952:
			c.getDH().sendDialogues(5300, 708);
			break;

		case 4155:
			c.getDH().sendDialogues(25, 1597);
			break;

		case 9477:
			c.getPA().openUpBank(0);
			c.getItems().deleteItem(9477, 1);
			break;
		}

		if (itemId == 952) {
			c.startAnimation(830);
		EventManager.getSingleton().addEvent(new Event() {
			@Override
			public void execute(final EventContainer p) {
				c.getPA().resetAnimation();
				CluescrollRewards.FinalCheck(c);
						p.stop();
					}
				}, 800);
			if (c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if (c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if (c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if (c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if (c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if (c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if (c.absX == 3294 && c.absY == 4513) {
				if (c.santasHelp == 0) {
					c.startAnimation(830);
					c.getItems().addItem(15420, 1);
					c.santasHelp = 1;
					c.getDH().sendStatement("You've found the first present! Bring it to Santa!");
				} else {
					c.startAnimation(830);
					c.sendMessage("You find nothing special here.");
			}
		}
	}
}

	static int randomSeeds() {
		return seeds[(int) (Math.random() * seeds.length)];
	}

	private static int seeds[] = { 5291, 5292, 5293, 5294, 5295, 5296, 5297,
		5298, 5299, 5300, 5301, 5302, 5303, 5304 };

}
