package server.model.players.packets;

import server.util.Misc;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.AdvancedMysteryBox;
import server.Server;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
			
		}
		if (itemId == 6199)
                	if (c.getItems().playerHasItem(6199)) {
                	AdvancedMysteryBox.Open(itemId, c);
                	return;
}

                /*if(itemId == 8007) {
				if(!c.InDung);
					c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
				if (itemId == 15098 && System.currentTimeMillis() - c.diceDelay >= 1200) { //Dice Bag ID
				if (c.clanId >= 0);
				Server.clanChat.playerMessageToClan(c.playerId, "I Have Rolled A "+ Misc.random(100) +" On The Percentile Dice ", c.clanId);
				c.startAnimation(11900);
				c.gfx0(2075);
				c.diceDelay = System.currentTimeMillis();
			} else {
			if (c.clanId != -1)
			return;

			}*/
		if (itemId == 7509) {
			c.startAnimation(829);
			c.dealDamage(1);
			c.handleHitMask(1);
			c.forcedChat("Ow! I nearly broke a tooth!");
			c.getPA()
			.refreshSkill(3);
		}
		if (itemId == 2677) {
		c.getItems().deleteItem(2677, 1);
		c.sendMessage("Your loyalty title is now: Scout.");
c.getDH().sendDialogues(1338, 0);
		c.loyaltyTitle = 1;
	}
		if (itemId == 2678) {
		c.getItems().deleteItem(2678, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: Corporal.");
		c.loyaltyTitle = 2;
	}
		if (itemId == 2679) {
		c.getItems().deleteItem(2679, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: Kolonel.");
		c.loyaltyTitle = 3;
	}
		if (itemId == 2680) {
		c.getItems().deleteItem(2680, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: General.");
		c.loyaltyTitle = 4;
	}
		if (itemId == 2681) {
		c.getItems().deleteItem(2681, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: Skiller.");
		c.loyaltyTitle = 5;
	}
		if (itemId == 2682) {
		c.getItems().deleteItem(2682, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: Lord.");
		c.loyaltyTitle = 6;
	}
		if (itemId == 2683) {
		c.getItems().deleteItem(2683, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: Lady.");
		c.loyaltyTitle = 7;
	}
		if (itemId == 2684) {
		c.getItems().deleteItem(2684, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: King.");
		c.loyaltyTitle = 8;
	}
		if (itemId == 2685) {
		c.getItems().deleteItem(2685, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: Queen.");
		c.loyaltyTitle = 9;
	}
		if (itemId == 2686) {
		c.getItems().deleteItem(2686, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: God.");
		c.loyaltyTitle = 10;
	}
		if (itemId == 2687) {
		c.getItems().deleteItem(2687, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: Godess.");
		c.loyaltyTitle = 11;
	}
		if (itemId == 2688) {
		c.getItems().deleteItem(2688, 1);
		c.getDH().sendDialogues(1338, 0);
		c.sendMessage("Your loyalty title is now: The Fallen.");
		c.loyaltyTitle = 12;
	}
                if(itemId == 8008) {
				if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
				if (itemId > 15085 && itemId < 15102){
			c.useDice(itemId, false);
		}
		if (itemId == 15084){
			c.getDH().sendDialogues(106, 4289);
		}
				if(itemId == 15707) {
                   c.getPA().startTeleport(2417, 3526, 0, "dungtele");
				   c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
                }
              if(itemId == 8009) {
			  if(!c.InDung);
					c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
            if(itemId == 8010) {
			if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
          if(itemId == 8011) {
		  if(!c.InDung);
				c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
				}
          if(itemId == 8012) {
		  if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
          if(itemId == 8013) {
		  if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
		if(itemId == 4447) {	
			c.getPA().addSkillXP(3000, 24);
			c.sendMessage("You rub the lamp and feel yourself further in the arts of dungeoneering.");
			c.getItems().deleteItem(4447, 1);	
}
if(itemId == 3062) {
c.getItems().deleteItem(3062, 1);
int[] arrayOfItems ={1149, 1187, 1215, 1231, 1249, 1305, 1377, 1434, 1540, 11283, 11235, 10828, 4151, 1333, 1079, 1093, 1113, 1127, 1147, 1163, 1185, 1289, 1303, 1319, 1347, 1359, 1432, 11728, 11726, 11724, 11694, 11698, 11700, 11696, 4716, 4718, 4720, 4722, 4753, 4755, 4757, 4759, 4732, 4734, 4736, 4738, 4708, 4710, 4712, 4714,
4745, 4747, 4749, 4751, 4724, 4726, 4728, 4730, 1065, 1099, 1135, 2487, 2489, 2491, 2493, 2495, 2497, 2499, 2501, 2503, 10499, 11720, 11722, 15126, 13738, 13740, 13742, 13744, 19780, 15486, 15220, 6570, 3204, 4153, 6528, 15333, 5574, 5575, 5576, 3749, 3751, 3753, 3755, 7462, 1038, 1040, 1042, 1044, 1046, 1048, 1050, 1053, 1055, 1057, 2661, 2663, 2665, 2667,
861, 1438, 1440, 1442, 1444, 1446, 1448, 1275, 1276, 6107, 6108, 6110, 6106, 6109, 1725, 1731, 6568, 656, 658, 660, 662, 664, 579, 4405, 4391, 4365, 4345, 4325, 4675, 3842, 3844, 3840, 9185, 15243, 1381, 1383, 1385, 1387, 4170, 4089, 4091, 4093, 4095, 4097, 2415, 2416, 2417, 4109, 4111, 4113, 4115, 4117, 6916, 6924, 6918, 6922, 6920, 6914, 20135, 20139, 20143,
13884, 13890, 13896, 13902, 13887, 13893, 13899, 13905, 20147, 20151, 20155, 20159, 20163, 20167, 2583, 2587, 2589, 2591, 2593, 2595, 2957, 2607, 2609, 2611, 2613, 2615, 2617, 2599, 2601, 2605, 2615, 2617, 2619, 2621, 2623, 2625, 2627, 2629, 6585, 6581, 18349, 18351, 18353, 18355, 18357, 18359, 2653, 2655, 2657, 2659, 3578, 4039, 14642, 14641, 9748, 9751, 9754, 9757,
9760, 9763, 9766, 9769, 9772, 9775, 9778, 9781, 9784, 9787, 9790, 9793, 9796, 9799, 9802, 9805, 9808, 9811, 9813, 4585, 14479};
c.getItems().addItem(arrayOfItems[Misc.random(arrayOfItems.length)], 1);

}
if(itemId == 962) {
c.getItems().deleteItem(962, 1);
int[] arrayOfItems ={1048, 1046, 1044, 1042, 1040, 1038};
c.getItems().addItem(arrayOfItems[Misc.random(arrayOfItems.length)], 1);
}
if(itemId == 3511) {
c.getItems().addItem(995, 100000000);
c.getItems().deleteItem(3511, 1);
}
if(itemId == 13052) {
c.getItems().addItem(995, 500000000);
c.getItems().deleteItem(13052, 1);
}
if(itemId == 7786) {
c.getItems().addItem(17774, 1);
c.getItems().deleteItem(7786, 1);
c.sendMessage("You bind the whip vine in a whip-like shape, you now have a vine whip!");
}
if(itemId == 13047) {
c.getItems().addItem(995, 1000000000);
c.getItems().deleteItem(13047, 1);
}
if(itemId == 19039) {
c.getItems().addItem(995, 2000000000);
c.getItems().deleteItem(19039, 1);
}
if(itemId == 15262) {
c.getItems().addItem(18016, 100000);
c.getItems().deleteItem(15262, 1);
}
if(itemId == 290) {
c.getItems().addItem(12158, 20);
c.getItems().addItem(12160, 20);
c.getItems().addItem(12159, 20);
c.getItems().addItem(12163, 20);
c.getItems().deleteItem(290, 1);
}
if (itemId == 7498) {
c.playerRights = 1;
c.getItems().deleteItem(7498, 1);
c.logout();
}
		if(itemId == 5545) {
c.gfx0(54);
			}
			
		if(itemId == 6796) {
			c.playerLevel[0] = 99;
			c.playerLevel[2] = 99;
			c.playerLevel[3] = 99;
			c.playerLevel[4] = 99;
			c.playerLevel[6] = 99;
			c.playerXP[0] = c.getPA().getXPForLevel(100);
			c.playerXP[2] = c.getPA().getXPForLevel(100);
			c.playerXP[3] = c.getPA().getXPForLevel(100);
			c.playerXP[4] = c.getPA().getXPForLevel(100);
			c.playerXP[6] = c.getPA().getXPForLevel(100);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);
			c.getItems().deleteItem(6796, 1);
			c.logout();
			}
			
		if (itemId == 15272) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(15272, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 23;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Rocktail.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}
		if (itemId == 2528) {
		c.getItems().deleteItem(2528,1);
		c.getPA().showInterface(2808);
		}
		if (itemId == 11850) {
		c.getItems().deleteItem(11850,1);
		c.getItems().addItem(4724,1);
		c.getItems().addItem(4726,1);
		c.getItems().addItem(4728,1);
		c.getItems().addItem(4730,1);
		}
		if (itemId == 11852) {
		c.getItems().deleteItem(11852,1);
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		}
		if (itemId == 11854) {
		c.getItems().deleteItem(11854,1);
		c.getItems().addItem(4745,1);
		c.getItems().addItem(4747,1);
		c.getItems().addItem(4749,1);
		c.getItems().addItem(4751,1);
		}
		if (itemId == 11856) {
		c.getItems().deleteItem(11856,1);
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		}
		if (itemId == 11848) {
		c.getItems().deleteItem(11848,1);
		c.getItems().addItem(4716,1);
		c.getItems().addItem(4718,1);
		c.getItems().addItem(4720,1);
		c.getItems().addItem(4722,1);
		}
		if (itemId == 11846) {
		c.getItems().deleteItem(11846,1);
		c.getItems().addItem(4708,1);
		c.getItems().addItem(4710,1);
		c.getItems().addItem(4712,1);
		c.getItems().addItem(4714,1);
		}
		//Begin artifacts by Hirukos
		if (itemId >= 14876 && itemId <= 14892) {
			int a = itemId;
			String YEYAF = "<col=1532693>You Exchanged Your Artifact For</col> ";
			if (a == 14876){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000000);
			c.sendMessage(YEYAF + "<col=1532693>10 million Coins!</col>");
			}
			if (a == 14877){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,2000000);
			c.sendMessage(YEYAF + "<col=1532693>2 million Coins!</col>");
			}
			if (a == 14878){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1500000);
			c.sendMessage(YEYAF + "<col=1532693>1.5 million Coins!</col>");
			}
			if (a == 14879){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1000000);
			c.sendMessage(YEYAF + "<col=1532693>1 million Coins!</col>");
			}
			if (a == 14880){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,800000);
			c.sendMessage(YEYAF + "<col=1532693>800,000 Coins!</col>");
			}
			if (a == 14881){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,600000);
			c.sendMessage(YEYAF + "<col=1532693>600,000 Coins!</col>");
			}
			if (a == 14882){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,540000);
			c.sendMessage(YEYAF + "<col=1532693>540,000 Coins!</col>");
			}
			if (a == 14883){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,400000);
			c.sendMessage(YEYAF + "<col=1532693>400,000 Coins!</col>");
			}
			if (a == 14884){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,300000);
			c.sendMessage(YEYAF + "<col=1532693>300,000 Coins!</col>");
			}
			if (a == 14885){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,200000);
			c.sendMessage(YEYAF + "<col=1532693>200,000 Coins!</col>");
			}
			if (a == 14886){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,150000);
			c.sendMessage(YEYAF + "<col=1532693>150,000 Coins!</col>");
			}
			if (a == 14887){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,100000);
			c.sendMessage(YEYAF + "<col=1532693>100,000 Coins!</col>");
			}
			if (a == 14888){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,80000);
			c.sendMessage(YEYAF + "<col=1532693>80,000 Coins!</col>");
			}
			if (a == 14889){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,60000);
			c.sendMessage(YEYAF + "<col=1532693>60,000 Coins!</col>");
			}
			if (a == 14890){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,40000);
			c.sendMessage(YEYAF + "<col=1532693>40,000 Coins!</col>");
			}
			if (a == 14891){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,20000);
			c.sendMessage(YEYAF + "<col=1532693>20,000 Coins!</col>");
			} 
			if (a == 14892){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000);
			c.sendMessage(YEYAF + "<col=1532693>10,000 Coins!</col>");
			}
			
		}
		//End of artifacts By Hirukos
		
		
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (c.getHerblore().isUnidHerb(itemId))
			c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (c.getPrayer().isBone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if(c.inArea(2986, 3370, 3013, 3388)) {
				c.teleTimer = 3;
				c.newLocation = 7;
			}
		}
	}

}
